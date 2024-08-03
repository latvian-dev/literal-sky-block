package dev.latvian.mods.literalskyblock.client;

import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.latvian.mods.literalskyblock.LiteralSkyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

public class LSBClient {
	private static ShaderInstance skyShader;
	private static TextureTarget skyRenderTarget;

	public static ShaderInstance getSkyShader() {
		return skyShader;
	}

	public static void setSkyShader(ShaderInstance shader) {
		skyShader = shader;
	}

	public static final RenderType SKY_RENDER_TYPE = RenderType.create(LiteralSkyBlock.SKY.toString(), DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder()
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

	public static void renderSky(RenderLevelStageEvent event) {
		// AFTER_LEVEL ?
		if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) {
			return;
		}

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		int ww = window.getWidth();
		int wh = window.getHeight();

		if (ww <= 0 || wh <= 0) {
			return;
		}

		if (skyRenderTarget == null || skyRenderTarget.width != ww || skyRenderTarget.height != wh) {
			if (skyRenderTarget != null) {
				skyRenderTarget.destroyBuffers();
			}

			skyRenderTarget = new TextureTarget(ww, wh, true, Minecraft.ON_OSX);
		}

		skyRenderTarget.bindWrite(false);
		skyRenderTarget.clear(Minecraft.ON_OSX);
		skyRenderTarget.bindWrite(false);

		((LevelRendererLSB) mc.levelRenderer).lsb$renderSky(event.getPartialTick(), event.getCamera(), mc.gameRenderer, skyRenderTarget);

		mc.getMainRenderTarget().bindWrite(false);
	}

	public static void drawUI(GuiGraphics graphics) {
		setSkyTexture();

		int w = 80;
		int h = 50;

		graphics.fill(0, 0, w + 1, h + 1, 5, 0xFF000000);

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		var builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		builder.addVertex(0F, 0F, 10F).setUv(0F, 1F);
		builder.addVertex(0F, h, 10F).setUv(0F, 0F);
		builder.addVertex(w, h, 10F).setUv(1F, 0F);
		builder.addVertex(w, 0F, 10F).setUv(1F, 1F);
		BufferUploader.drawWithShader(builder.buildOrThrow());
	}
}
