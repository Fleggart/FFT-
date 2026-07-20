package com.yourmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = YourMod.MODID)
public class ModItems {
    public static final List<DynamicItem> ITEMS = new ArrayList<>();

    // 从配置文件加载并创建物品
    public static void init() {
        ITEMS.clear();
        
        List<ConfigLoader.ItemData> dataList = ConfigLoader.loadItems();
        
        for (ConfigLoader.ItemData data : dataList) {
            DynamicItem item = new DynamicItem(data.id, data.quantity, data.creativeTab);
            ITEMS.add(item);
            System.out.println("[YourMod] 已创建物品: " + data.id);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (DynamicItem item : ITEMS) {
            event.getRegistry().register(item);
            System.out.println("[YourMod] 已注册物品: " + item.getItemId());
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (DynamicItem item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(
                item,
                0,
                new ModelResourceLocation(item.getRegistryName(), "inventory")
            );
            System.out.println("[YourMod] 已注册模型: " + item.getItemId());
        }
    }
}
