package dpk.plugins.dpkexchange;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DPKExchange extends JavaPlugin {
    public static FileConfiguration config;
    public static JavaPlugin DPKExchange;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        DPKExchange = this;
        config = getConfig();
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
