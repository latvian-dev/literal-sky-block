package dev.latvian.mods.literalskyblock;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.function.Supplier;

public interface LSBBlockEntities {
	DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, LiteralSkyBlock.MOD_ID);

	Supplier<BlockEntityType<SkyBlockEntity>> SKY_BLOCK = REGISTER.register("sky_block", () -> BlockEntityType.Builder.of(SkyBlockEntity::new, Arrays.stream(ProjectionType.VALUES).map(p -> p.skyBlock).map(DeferredHolder::get).toArray(Block[]::new)).build(null));
}
