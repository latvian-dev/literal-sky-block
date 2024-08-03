package dev.latvian.mods.literalskyblock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface LSBBlocks {
	DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(LiteralSkyBlock.MOD_ID);

	DeferredBlock<Block> SKY_BLOCK = REGISTER.registerBlock("sky_block", SkyBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
	DeferredBlock<Block> VOID_BLOCK = REGISTER.registerBlock("void_block", VoidBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
}
