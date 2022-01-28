package woodp1anks.plugins.inventorysaver.api;

import org.bukkit.ChatColor;
import woodp1anks.plugins.inventorysaver.InventorySaver;

public class ConfigReader {
    public static String getMsg(String key) {
        String message = InventorySaver.config.getString("messages." + key);
        message = ChatColor.translateAlternateColorCodes('&',message);
        return message;
    }
}
