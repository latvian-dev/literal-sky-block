package dev.latvian.mods.literalskyblock;

import dev.latvian.mods.literalskyblock.client.LSBClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author LatvianModder
 */
@Mod(LiteralSkyBlock.MOD_ID)
public class LiteralSkyBlock {
	public static final String MOD_ID = "literalskyblock";

	public static LSBCommon PROXY;

	public LiteralSkyBlock() {
		PROXY = DistExecutor.safeRunForDist(() -> LSBClient::new, () -> LSBCommon::new);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		LSBBlocks.REGISTER.register(bus);
		LSBItems.REGISTER.register(bus);
		LSBBlockEntities.REGISTER.register(bus);
		PROXY.init();
	}
}