package dev.latvian.mods.literalskyblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class EndSkyBlockEntity extends SkyBlockEntity {
	public EndSkyBlockEntity(BlockPos pos, BlockState state) {
		super(LSBBlockEntities.END_SKY_BLOCK.get(), pos, state);
	}
}
