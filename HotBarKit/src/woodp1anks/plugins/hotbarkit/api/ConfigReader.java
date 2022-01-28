package woodp1anks.plugins.hotbarkit.api;

import org.bukkit.ChatColor;
import woodp1anks.plugins.hotbarkit.HotBarKit;

public class ConfigReader {
    public static String getMsg(String key) {
        String message = HotBarKit.config.getString("messages." + key);
        message = ChatColor.translateAlternateColorCodes('&',message);
        return message;
    }
}
