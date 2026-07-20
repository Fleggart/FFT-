package com.yourmod;

import net.minecraftforge.fml.common.Loader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    
    // 物品数据容器类
    public static class ItemData {
        public final String id;
        public final int quantity;
        public final String creativeTab;
        
        public ItemData(String id, int quantity, String creativeTab) {
            this.id = id;
            this.quantity = quantity;
            this.creativeTab = creativeTab;
        }
    }
    
    /**
     * 从 config/yourmod/items.txt 读取物品配置
     * @return 物品数据列表
     */
    public static List<ItemData> loadItems() {
        List<ItemData> items = new ArrayList<>();
        
        File configDir = new File(Loader.instance().getConfigDir(), "yourmod");
        File configFile = new File(configDir, "items.txt");
        
        // 如果目录不存在，创建它
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        
        // 如果文件不存在，生成默认配置
        if (!configFile.exists()) {
            generateDefaultConfig(configFile);
        }
        
        // 读取并解析配置文件
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            String currentId = null;
            int currentQuantity = 64;
            String currentTab = "MATERIALS";
            boolean hasData = false;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                // 跳过空行和注释
                if (line.isEmpty() || line.startsWith("#")) {
                    // 如果是空行且有数据，保存当前物品并重置
                    if (hasData && currentId != null) {
                        items.add(new ItemData(currentId, currentQuantity, currentTab));
                        currentId = null;
                        currentQuantity = 64;
                        currentTab = "MATERIALS";
                        hasData = false;
                    }
                    continue;
                }
                
                // 解析键值对
                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    
                    switch (key) {
                        case "ItemID":
                            currentId = value;
                            hasData = true;
                            break;
                        case "Item_quantity":
                            try {
                                currentQuantity = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                System.err.println("[YourMod] 数量格式错误，使用默认值64: " + value);
                                currentQuantity = 64;
                            }
                            break;
                        case "CreativeTab":
                            currentTab = value.toUpperCase();
                            break;
                    }
                }
            }
            
            // 保存最后一个物品
            if (hasData && currentId != null) {
                items.add(new ItemData(currentId, currentQuantity, currentTab));
            }
            
        } catch (Exception e) {
            System.err.println("[YourMod] 读取配置文件失败: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
    
    /**
     * 生成默认配置文件
     */
    private static void generateDefaultConfig(File file) {
        try {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("# ==========================================\n");
                writer.write("# 自定义物品配置文件\n");
                writer.write("# 每个物品之间用空行隔开\n");
                writer.write("# ==========================================\n\n");
                
                writer.write("# 第一个物品: 铜锭\n");
                writer.write("ItemID=copper_ingot\n");
                writer.write("Item_quantity=64\n");
                writer.write("CreativeTab=MATERIALS\n\n");
                
                writer.write("# 第二个物品: 钢锭\n");
                writer.write("ItemID=steel_ingot\n");
                writer.write("Item_quantity=16\n");
                writer.write("CreativeTab=MATERIALS\n\n");
                
                writer.write("# 第三个物品: 银锭\n");
                writer.write("ItemID=silver_ingot\n");
                writer.write("Item_quantity=1\n");
                writer.write("CreativeTab=MISC\n");
                
                System.out.println("[YourMod] 已生成默认配置文件: " + file.getPath());
            }
            
        } catch (Exception e) {
            System.err.println("[YourMod] 生成配置文件失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
