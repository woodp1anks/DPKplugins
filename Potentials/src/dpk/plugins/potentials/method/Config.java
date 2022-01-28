package dpk.plugins.potentials.method;

import dpk.plugins.potentials.Potentials;
import org.bukkit.ChatColor;

public class Config {
    public static String getMsg(String key) {
        String message = Potentials.config.getString("messages." + key);
        message = ChatColor.translateAlternateColorCodes('&',message);
        return message;
    }
}
