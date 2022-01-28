package woodp1anks.plugins.inventorysaver;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class InventorySaver extends JavaPlugin {
    public static InventorySaver self;
    public static FileConfiguration config;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        self = this;
        config = getConfig();
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
