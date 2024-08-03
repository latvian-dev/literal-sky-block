package dev.latvian.mods.literalskyblock.client;

import dev.latvian.mods.literalskyblock.LiteralSkyBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = LiteralSkyBlock.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class LSBClientGameEventHandler {
	@SubscribeEvent
	public static void renderLast(RenderLevelStageEvent event) {
		LSBClient.renderSky(event);
	}

	/*
	@SubscribeEvent
	public static void drawUI(RenderGuiEvent.Post event) {
		LSBClient.drawUI(event.getGuiGraphics());
	}
	 */
}
