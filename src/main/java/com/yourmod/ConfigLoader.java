package com.yourmod;

import net.minecraftforge.fml.common.Loader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    public static class ItemData {
        public final String id;
        public final int quantity;
        public final String creativeTab;
        public final boolean isFood;
        public final int hunger;
        public final float saturation;

        public ItemData(String id, int quantity, String creativeTab, boolean isFood, int hunger, float saturation) {
            this.id = id;
            this.quantity = quantity;
            this.creativeTab = creativeTab;
            this.isFood = isFood;
            this.hunger = hunger;
            this.saturation = saturation;
        }
    }

    public static List<ItemData> loadItems() {
        List<ItemData> items = new ArrayList<>();
        File configFile = new File(Loader.instance().getConfigDir(), "yourmod/items.txt");

        if (!configFile.exists()) {
            generateDefaultConfig(configFile);
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            String currentId = null;
            int currentQuantity = 64;
            String currentTab = "MATERIALS";
            boolean isFood = false;
            int hunger = 4;
            float saturation = 0.6f;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    if (currentId != null) {
                        items.add(new ItemData(currentId, currentQuantity, currentTab, isFood, hunger, saturation));
                        currentId = null;
                        currentQuantity = 64;
                        currentTab = "MATERIALS";
                        isFood = false;
                        hunger = 4;
                        saturation = 0.6f;
                    }
                    continue;
                }

                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    switch (key) {
                        case "ItemID": currentId = value; break;
                        case "Item_quantity": currentQuantity = Integer.parseInt(value); break;
                        case "CreativeTab": currentTab = value.toUpperCase(); break;
                        case "IsFood": isFood = Boolean.parseBoolean(value); break;
                        case "Hunger": hunger = Integer.parseInt(value); break;
                        case "Saturation": saturation = Float.parseFloat(value); break;
                    }
                }
            }
            if (currentId != null) {
                items.add(new ItemData(currentId, currentQuantity, currentTab, isFood, hunger, saturation));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    private static void generateDefaultConfig(File file) {
        try {
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("# 自定义物品配置\n\n");
                writer.write("ItemID=copper_ingot\n");
                writer.write("Item_quantity=64\n");
                writer.write("CreativeTab=MATERIALS\n");
                writer.write("IsFood=false\n");
                writer.write("Hunger=4\n");
                writer.write("Saturation=0.6\n\n");
                writer.write("ItemID=super_apple\n");
                writer.write("Item_quantity=64\n");
                writer.write("CreativeTab=FOOD\n");
                writer.write("IsFood=true\n");
                writer.write("Hunger=8\n");
                writer.write("Saturation=0.8\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
