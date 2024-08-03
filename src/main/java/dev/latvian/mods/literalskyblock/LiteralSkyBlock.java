package dev.latvian.mods.literalskyblock;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(LiteralSkyBlock.MOD_ID)
public class LiteralSkyBlock {
	public static final String MOD_ID = "literalskyblock";

	public static final ResourceLocation SKY = ResourceLocation.fromNamespaceAndPath(MOD_ID, "sky");

	public LiteralSkyBlock(IEventBus bus) {
		LSBBlocks.REGISTER.register(bus);
		LSBItems.REGISTER.register(bus);
		LSBBlockEntities.REGISTER.register(bus);
	}
}