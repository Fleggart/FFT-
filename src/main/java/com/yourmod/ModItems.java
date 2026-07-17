package com.yourmod;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = YourMod.MODID)
public class ModItems {
    public static Item copperIngot;

    public static void init() {
        copperIngot = new ItemCopperIngot();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(copperIngot);
        System.out.println("铜锭已注册！"); // 控制台输出，方便调试
    }
}
