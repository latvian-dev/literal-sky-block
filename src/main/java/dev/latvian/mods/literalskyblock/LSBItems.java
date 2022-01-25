package dev.latvian.mods.literalskyblock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface LSBItems {
	DeferredRegister<Item> REGISTER = DeferredRegister.create(Item.class, LiteralSkyBlock.MOD_ID);

	Supplier<Item> SKY_BLOCK = REGISTER.register("sky_block", () -> new BlockItem(LSBBlocks.SKY_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
	Supplier<Item> VOID_BLOCK = REGISTER.register("void_block", () -> new BlockItem(LSBBlocks.VOID_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
}
