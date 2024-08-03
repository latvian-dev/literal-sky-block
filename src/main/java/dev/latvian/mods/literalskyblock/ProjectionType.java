package dev.latvian.mods.literalskyblock;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public enum ProjectionType implements StringRepresentable {
	SKY("sky", false),
	VOID("void", false);

	public static final ProjectionType[] VALUES = values();
	public static final Codec<ProjectionType> CODEC = StringRepresentable.fromEnum(() -> VALUES);

	private final String name;
	public final boolean uvs;

	public Object renderType;
	public DeferredBlock<Block> skyBlock;
	public DeferredItem<BlockItem> skyBlockItem;

	ProjectionType(String name, boolean uvs) {
		this.name = name;
		this.uvs = uvs;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
