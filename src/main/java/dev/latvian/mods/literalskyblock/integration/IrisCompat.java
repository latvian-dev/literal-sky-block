package dev.latvian.mods.literalskyblock.integration;

/*
import com.mojang.logging.LogUtils;
import net.coderbot.iris.Iris;
import net.coderbot.iris.pipeline.WorldRenderingPhase;
import net.coderbot.iris.pipeline.WorldRenderingPipeline;
import net.irisshaders.iris.api.v0.IrisApi;
import net.minecraft.client.renderer.LevelRenderer;
import org.slf4j.Logger;

import java.lang.reflect.Field;

public class IrisCompat {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Field PIPELINE;

    static {
        Field pipeline;
        try {
            //noinspection JavaReflectionMemberAccess
            pipeline = LevelRenderer.class.getDeclaredField("pipeline");
            pipeline.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            pipeline = null;
            LOGGER.error("Failed to get Iris pipeline field", e);
        }
        PIPELINE = pipeline;
    }

    public static void preRender(LevelRenderer renderer) {
        if (PIPELINE == null) return;
        try {
            final WorldRenderingPipeline pipeline = Iris.getPipelineManager().preparePipeline(Iris.getCurrentDimension());
            PIPELINE.set(renderer, pipeline);
            //pipeline.beginLevelRendering();
            pipeline.setOverridePhase(WorldRenderingPhase.NONE);
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Exception in preRender", e);
        }
    }

    public static void postRender(LevelRenderer renderer) {
        if (PIPELINE == null) return;
        try {
            final WorldRenderingPipeline pipeline = (WorldRenderingPipeline)PIPELINE.get(renderer);
            //pipeline.finalizeLevelRendering();
            PIPELINE.set(renderer, null);
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Exception in postRender", e);
        }
    }

    public static boolean shadersEnabled() {
        return IrisApi.getInstance().isShaderPackInUse();
    }
}
 */