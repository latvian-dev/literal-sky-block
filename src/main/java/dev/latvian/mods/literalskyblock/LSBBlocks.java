package dev.latvian.mods.literalskyblock;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface LSBBlocks {
	DeferredRegister<Block> REGISTER = DeferredRegister.create(Block.class, LiteralSkyBlock.MOD_ID);

	Supplier<Block> SKY_BLOCK = REGISTER.register("sky_block", SkyBlock::new);
	Supplier<Block> END_SKY_BLOCK = REGISTER.register("end_sky_block", EndSkyBlock::new);
}
