package woodp1anks.plugins.inventorysaver.api;

import org.bukkit.inventory.ItemStack;
import woodp1anks.plugins.inventorysaver.InventorySaver;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ConfigItemsSaver {
    public static void saveItems(ItemStack[] items, String name) {
        // 序列化
        try  {
            ByteArrayOutputStream inventoryByteArrayOutput = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitOutput = new BukkitObjectOutputStream(inventoryByteArrayOutput);
            bukkitOutput.writeInt(items.length);

            for (int i = 0;i < items.length;i++) {
                bukkitOutput.writeObject(items[i]);
            }

            bukkitOutput.close();
            String inventoryToString = Base64Coder.encodeLines(inventoryByteArrayOutput.toByteArray());
            InventorySaver.config.set("data.inventories." + name,inventoryToString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static ItemStack[] getItems(String name) {
        // 反序列化
        String inventoryToString = InventorySaver.config.getString("data.inventories." + name);
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(inventoryToString));
            BukkitObjectInputStream bukkitInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = (Inventory) bukkitInput.readObject();
            bukkitInput.close();
            return inventory;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
