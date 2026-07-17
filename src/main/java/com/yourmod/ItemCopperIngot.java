package com.yourmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCopperIngot extends Item {
    public ItemCopperIngot() {
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.MATERIALS);
        this.setRegistryName("copper_ingot");
        this.setUnlocalizedName(this.getRegistryName().toString());
    }
}
