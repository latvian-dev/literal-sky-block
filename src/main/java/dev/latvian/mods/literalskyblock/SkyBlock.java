package dev.latvian.mods.literalskyblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SkyBlock extends BaseEntityBlock {
	public SkyBlock() {
		super(Properties.copy(Blocks.STONE));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SkyBlockEntity(pos, state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor level, BlockPos pos, BlockPos pos1) {
		BlockEntity blockEntity = level.getBlockEntity(pos);

		if (blockEntity instanceof SkyBlockEntity) {
			((SkyBlockEntity) blockEntity).neighborChanged();
		}

		return super.updateShape(state, direction, state1, level, pos, pos1);
	}

	@Override
	@Deprecated
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean b) {
		super.neighborChanged(state, level, pos, block, pos1, b);

		BlockEntity blockEntity = level.getBlockEntity(pos);

		if (blockEntity instanceof SkyBlockEntity) {
			((SkyBlockEntity) blockEntity).neighborChanged();
		}
	}
}
