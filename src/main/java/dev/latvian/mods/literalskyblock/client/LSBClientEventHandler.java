package dev.latvian.mods.literalskyblock.client;

import dev.latvian.mods.literalskyblock.LiteralSkyBlock;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LiteralSkyBlock.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LSBClientEventHandler {
	@SubscribeEvent
	public static void renderLast(RenderLevelLastEvent event) {
		LSBClient.renderSky(event);
	}
}
