package dev.latvian.mods.literalskyblock;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface LSBBlocks {
	DeferredRegister<Block> REGISTER = DeferredRegister.create(Registry.BLOCK_REGISTRY, LiteralSkyBlock.MOD_ID);

	Supplier<Block> SKY_BLOCK = REGISTER.register("sky_block", SkyBlock::new);
	Supplier<Block> VOID_BLOCK = REGISTER.register("void_block", VoidBlock::new);
}
