package dev.latvian.mods.literalskyblock;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface LSBItems {
	DeferredRegister.Items REGISTER = DeferredRegister.createItems(LiteralSkyBlock.MOD_ID);

	DeferredItem<BlockItem> SKY_BLOCK = REGISTER.registerSimpleBlockItem("sky_block", LSBBlocks.SKY_BLOCK);
	DeferredItem<BlockItem> VOID_BLOCK = REGISTER.registerSimpleBlockItem("void_block", LSBBlocks.VOID_BLOCK);
}
