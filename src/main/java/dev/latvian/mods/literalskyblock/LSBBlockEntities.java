package dev.latvian.mods.literalskyblock;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface LSBBlockEntities {
	DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, LiteralSkyBlock.MOD_ID);

	Supplier<BlockEntityType<SkyBlockEntity>> SKY_BLOCK = REGISTER.register("sky_block", () -> BlockEntityType.Builder.of(SkyBlockEntity::new, LSBBlocks.SKY_BLOCK.get()).build(null));
	Supplier<BlockEntityType<VoidBlockEntity>> VOID_BLOCK = REGISTER.register("void_block", () -> BlockEntityType.Builder.of(VoidBlockEntity::new, LSBBlocks.VOID_BLOCK.get()).build(null));
}
