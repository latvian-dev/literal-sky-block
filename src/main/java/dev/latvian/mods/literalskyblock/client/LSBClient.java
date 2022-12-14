package dev.latvian.mods.literalskyblock.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import dev.latvian.mods.literalskyblock.LSBBlockEntities;
import dev.latvian.mods.literalskyblock.LSBCommon;
import dev.latvian.mods.literalskyblock.LiteralSkyBlock;
import dev.latvian.mods.literalskyblock.integration.IrisCompat;
import net.minecraft.client.Camera;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = LiteralSkyBlock.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LSBClient extends LSBCommon {
	private static ShaderInstance skyShader;
	private static int skyWidth = -1;
	private static int skyHeight = -1;
	private static TextureTarget skyRenderTarget;
	public static boolean updateSky = false;
	private static boolean isRenderingSky = false;
	private static boolean irisLoaded = false;

	public static ShaderInstance getSkyShader() {
		return skyShader;
	}

	public static void setSkyShader(ShaderInstance shader) {
		skyShader = shader;
	}

	public static final RenderType SKY_RENDER_TYPE = RenderType.create(LiteralSkyBlock.MOD_ID + "_sky", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder()
			.setShaderState(new RenderStateShard.ShaderStateShard(LSBClient::getSkyShader))
			.setTextureState(new RenderStateShard.EmptyTextureStateShard(LSBClient::setSkyTexture, LSBClient::noop))
			.createCompositeState(false)
	);

	private static void setSkyTexture() {
		if (skyRenderTarget != null) {
			RenderSystem.setShaderTexture(0, skyRenderTarget.getColorTextureId());
		} else {
			RenderSystem.setShaderTexture(0, 0);
		}
	}

	private static void noop() {
	}

	public static void renderSky(RenderLevelLastEvent event) {
		if (isRenderingSky) {
			return;
		}

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		int ww = window.getWidth();
		int wh = window.getHeight();

		if (ww <= 0 || wh <= 0) {
			return;
		}

		boolean update = false;

		if (skyRenderTarget == null || skyWidth != ww || skyHeight != wh) {
			update = true;
			skyWidth = ww;
			skyHeight = wh;
		}

		if (update) {
			if (skyRenderTarget != null) {
				skyRenderTarget.destroyBuffers();
			}

			skyRenderTarget = new TextureTarget(skyWidth, skyHeight, true, Minecraft.ON_OSX);
		}

		if (irisLoaded) IrisCompat.preRender(mc.levelRenderer);
		mc.gameRenderer.setRenderBlockOutline(false);
		mc.levelRenderer.graphicsChanged();
		skyRenderTarget.bindWrite(true);

		isRenderingSky = true;
		RenderTarget mainRenderTarget = mc.getMainRenderTarget();
		renderActualSky(mc, event);
		isRenderingSky = false;

		mc.gameRenderer.setRenderBlockOutline(true);
		skyRenderTarget.unbindRead();
		skyRenderTarget.unbindWrite();
		mc.levelRenderer.graphicsChanged();
		mainRenderTarget.bindWrite(true);
		if (irisLoaded) IrisCompat.postRender(mc.levelRenderer);
	}

	public static void renderActualSky(Minecraft mc, RenderLevelLastEvent event) {
		if (mc == null || mc.level == null || mc.player == null) {
			return;
		}

		PoseStack poseStack = event.getPoseStack();
		final float delta = event.getPartialTick();
		Matrix4f projectionMatrix = event.getProjectionMatrix();
		LevelRenderer levelRenderer = mc.levelRenderer;
		LevelRendererLSB levelRendererLSB = (LevelRendererLSB) levelRenderer;
		GameRenderer gameRenderer = mc.gameRenderer;
		final Camera camera = gameRenderer.getMainCamera();
		Vec3 cameraPos = camera.getPosition();
		LightTexture lightTexture = gameRenderer.lightTexture();

		FogRenderer.setupColor(camera, delta, mc.level, mc.options.getEffectiveRenderDistance(), gameRenderer.getDarkenWorldAmount(delta));
		FogRenderer.levelFogColor();
		RenderSystem.clear(16640, Minecraft.ON_OSX);
		final float renderDistance = gameRenderer.getRenderDistance();
		final boolean hasSpecialFog = mc.level.effects().isFoggyAt(Mth.floor(cameraPos.x), Mth.floor(cameraPos.z)) || mc.gui.getBossOverlay().shouldCreateWorldFog();
		FogRenderer.setupFog(camera, FogRenderer.FogMode.FOG_SKY, renderDistance, hasSpecialFog, delta);
		RenderSystem.setShader(GameRenderer::getPositionShader);
		levelRenderer.renderSky(poseStack, projectionMatrix, delta, camera, false, () -> FogRenderer.setupFog(camera, FogRenderer.FogMode.FOG_SKY, renderDistance, hasSpecialFog, delta));

		PoseStack modelViewStack = RenderSystem.getModelViewStack();
		modelViewStack.pushPose();
		modelViewStack.mulPoseMatrix(poseStack.last().pose());
		RenderSystem.applyModelViewMatrix();

		if (mc.options.getCloudsType() != CloudStatus.OFF) {
			RenderSystem.setShader(GameRenderer::getPositionTexColorNormalShader);
			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
			levelRenderer.renderClouds(poseStack, projectionMatrix, delta, cameraPos.x, cameraPos.y, cameraPos.z);
		}

		RenderSystem.depthMask(false);
		levelRendererLSB.renderSnowAndRainLSB(lightTexture, delta, cameraPos.x, cameraPos.y, cameraPos.z);

		RenderSystem.depthMask(true);
		RenderSystem.disableBlend();
		modelViewStack.popPose();
		RenderSystem.applyModelViewMatrix();
		FogRenderer.setupNoFog();
	}

	@Override
	public void init() {
		irisLoaded = ModList.get().isLoaded("oculus");
	}

	@SubscribeEvent
	public static void setup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(LSBBlockEntities.SKY_BLOCK.get(), SkyBlockEntityRenderer::new);
		BlockEntityRenderers.register(LSBBlockEntities.VOID_BLOCK.get(), SkyBlockEntityRenderer::new);
	}

	@SubscribeEvent
	public static void registerShaders(RegisterShadersEvent event) throws IOException {
		event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation(LiteralSkyBlock.MOD_ID, "sky"), DefaultVertexFormat.POSITION), LSBClient::setSkyShader);
	}
}
