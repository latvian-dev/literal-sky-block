package dev.latvian.mods.literalskyblock.client;

import com.mojang.blaze3d.pipeline.TextureTarget;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;

public interface LevelRendererLSB {
	void lsb$renderSky(DeltaTracker deltaTracker, Camera camera, GameRenderer gameRenderer, TextureTarget target);
}
