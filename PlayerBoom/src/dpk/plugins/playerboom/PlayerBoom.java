package dpk.plugins.playerboom;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerBoom extends JavaPlugin {
    public static FileConfiguration config;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        config = getConfig();
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
