package woodp1anks.plugins.hotbarkit.api;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import woodp1anks.plugins.hotbarkit.HotBarKit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class KitSaver {
    public static void saveKit(ItemStack[] items,String kitName) {
        // 序列化
        try {
            // 开启默认输出流和bukkit输出流，bukkit输出流用来存储信息
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitDataOutputStream = new BukkitObjectOutputStream(outputStream);
            // 在bukkit输出流里写出有多少物品，方便反序列化
            bukkitDataOutputStream.writeInt(items.length);
            // 循环写入itemStack[]里的所有itemStack
            for (ItemStack item : items) {
                bukkitDataOutputStream.writeObject(item);
            }
            // 关闭输出流
            bukkitDataOutputStream.close();
            // 在配置文件里保存反序列化的结果(将outputStream toByteArray之后编码成base64)
            String itemStacksToString = Base64Coder.encodeLines(outputStream.toByteArray());
            HotBarKit.config.set("data.hotBarKits." + kitName,itemStacksToString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void deleteKit(String kitName) {
        HotBarKit.config.set("data.hotBarKits." + kitName,null);
        List<String> kits = HotBarKit.config.getStringList("data.kitList");
        kits.remove(kitName);
        HotBarKit.config.set("data.kitList",kits);
    }
    public static ItemStack[] getKit(String kitName) {
        // 反序列化
        try {
            String base64ItemStacksToString = HotBarKit.config.getString("data.hotBarKits." + kitName);
            // 输入流
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(base64ItemStacksToString));
            BukkitObjectInputStream bukkitDataInputStream = new BukkitObjectInputStream(inputStream);
            // 按照序列化时输入的itemStacks的数量创建itemStack[]
            ItemStack[] items = new ItemStack[bukkitDataInputStream.readInt()];
            // for循环读取，保存
            for (int i = 0;i < items.length;i++) {
                items[i] = (ItemStack) bukkitDataInputStream.readObject();
            }
            // close
            bukkitDataInputStream.close();
            return items;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new ItemStack[8];
        }
    }
}
