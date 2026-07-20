package com.yourmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class DynamicItem extends ItemFood {
    private final String itemId;

    public DynamicItem(ConfigLoader.ItemData data) {
        super(data.hunger, data.saturation, data.isFood);
        this.itemId = data.id;
        this.setRegistryName(data.id);
        this.setTranslationKey(data.id);
        this.maxStackSize = data.quantity;
        this.setCreativeTab(getCreativeTabByName(data.creativeTab));
    }

    private CreativeTabs getCreativeTabByName(String name) {
        switch (name.toUpperCase()) {
            case "MATERIALS": return CreativeTabs.MATERIALS;
            case "MISC": return CreativeTabs.MISC;
            case "FOOD": return CreativeTabs.FOOD;
            default: return CreativeTabs.MISC;
        }
    }
}
