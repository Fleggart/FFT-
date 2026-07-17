package com.yourmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = YourMod.MODID)
public class ModItems {
    public static Item copperIngot;

    public static void init() {
        copperIngot = new ItemCopperIngot();
    }

    // 物品注册事件 (保持不变)
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(copperIngot);
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(
            copperIngot, 
            0, 
            new ModelResourceLocation(copperIngot.getRegistryName(), "inventory")
        );
    }
}
