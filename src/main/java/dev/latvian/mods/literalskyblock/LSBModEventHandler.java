package dev.latvian.mods.literalskyblock;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = LiteralSkyBlock.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class LSBModEventHandler {
	@SubscribeEvent
	public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			for (var p : ProjectionType.VALUES) {
				event.accept(p.skyBlockItem, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			}
		}
	}
}
