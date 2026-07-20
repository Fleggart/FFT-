package com.yourmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = YourMod.MODID, name = "动态物品模组", version = "1.0")
public class YourMod {
    public static final String MODID = "yourmod";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
    }
}
