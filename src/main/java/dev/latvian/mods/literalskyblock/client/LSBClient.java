package dev.latvian.mods.literalskyblock.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.latvian.mods.literalskyblock.LSBBlockEntities;
import dev.latvian.mods.literalskyblock.LSBCommon;
import dev.latvian.mods.literalskyblock.LiteralSkyBlock;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = LiteralSkyBlock.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LSBClient extends LSBCommon {
	public static final ResourceLocation SKY_TEXTURE = new ResourceLocation(LiteralSkyBlock.MOD_ID, "textures/sky_texture.png");

	private static ShaderInstance skyShader;

	public static ShaderInstance getSkyShader() {
		return skyShader;
	}

	public static final RenderType SKY_RENDER_TYPE = RenderType.create(LiteralSkyBlock.MOD_ID + "_sky", DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder()
			.setShaderState(new RenderStateShard.ShaderStateShard(LSBClient::getSkyShader))
			.setTextureState(RenderStateShard.MultiTextureStateShard.builder().add(SKY_TEXTURE, false, false).build())
			.createCompositeState(false)
	);

	@Override
	public void init() {
	}

	@SubscribeEvent
	public static void setup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(LSBBlockEntities.SKY_BLOCK.get(), SkyBlockEntityRenderer::new);
		BlockEntityRenderers.register(LSBBlockEntities.END_SKY_BLOCK.get(), SkyBlockEntityRenderer::new);
	}

	@SubscribeEvent
	public static void registerShaders(RegisterShadersEvent event) throws IOException {
		event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation(LiteralSkyBlock.MOD_ID, "sky"), DefaultVertexFormat.POSITION), s -> skyShader = s);
	}
}
