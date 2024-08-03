package dev.latvian.mods.literalskyblock.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import dev.latvian.mods.literalskyblock.LSBBlockEntities;
import dev.latvian.mods.literalskyblock.LiteralSkyBlock;
import dev.latvian.mods.literalskyblock.ProjectionType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(modid = LiteralSkyBlock.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LSBClientModEventHandler {
	@SubscribeEvent
	public static void setup(FMLClientSetupEvent event) {
		BlockEntityRenderers.register(LSBBlockEntities.SKY_BLOCK.get(), SkyBlockEntityRenderer::new);

		ProjectionType.SKY.renderType = LSBClient.SKY_RENDER_TYPE;
		ProjectionType.VOID.renderType = RenderType.endGateway();
	}

	@SubscribeEvent
	public static void registerShaders(RegisterShadersEvent event) throws IOException {
		event.registerShader(new ShaderInstance(event.getResourceProvider(), LiteralSkyBlock.SKY, DefaultVertexFormat.POSITION), LSBClient::setSkyShader);
	}

	/*
	@SubscribeEvent
	public static void registerNamedRenderTypes(RegisterNamedRenderTypesEvent event) {
		event.register(LiteralSkyBlock.SKY, LSBClient.SKY_BLOCK_RENDER_TYPE, LSBClient.SKY_ENTITY_RENDER_TYPE);
	}
	 */
}
