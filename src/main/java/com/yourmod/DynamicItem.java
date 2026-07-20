package com.yourmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DynamicItem extends Item {
    private final String itemId;

    public DynamicItem(String itemId, int stackSize, String creativeTab) {
        this.itemId = itemId;
        this.maxStackSize = stackSize;
        this.setRegistryName(itemId);
        this.setTranslationKey(itemId);
        this.setCreativeTab(getCreativeTabByName(creativeTab));
    }

    public String getItemId() {
        return itemId;
    }

    private CreativeTabs getCreativeTabByName(String name) {
        switch (name.toUpperCase()) {
            case "MATERIALS": return CreativeTabs.MATERIALS;
            case "MISC": return CreativeTabs.MISC;
            case "FOOD": return CreativeTabs.FOOD;
            case "TOOLS": return CreativeTabs.TOOLS;
            case "COMBAT": return CreativeTabs.COMBAT;
            default: return CreativeTabs.MISC;
        }
    }
}
