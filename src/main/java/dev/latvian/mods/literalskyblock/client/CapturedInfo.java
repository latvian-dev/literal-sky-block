package dev.latvian.mods.literalskyblock.client;

import net.minecraft.client.renderer.LightTexture;
import org.joml.Matrix4f;

public record CapturedInfo(LightTexture lightTexture, Matrix4f frustumMatrix, Matrix4f projectionMatrix) {
}
