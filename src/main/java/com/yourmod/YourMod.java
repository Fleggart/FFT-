package com.yourmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = YourMod.MODID, name = "我的模组", version = "1.0")
public class YourMod {
    public static final String MODID = "yourmod";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        // 不需要手动register，Forge会自动调用@SubscribeEvent
    }
}
