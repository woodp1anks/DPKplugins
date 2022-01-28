package dpk.plugins.dpkjumps;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DPKJumps extends JavaPlugin {
    public static JavaPlugin dpkJumps;
    public static FileConfiguration config;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        dpkJumps = this;
        config = getConfig();
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
