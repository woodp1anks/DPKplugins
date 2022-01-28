package dpk.plugins.dpkcreative;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DPKCreative extends JavaPlugin {
    public static FileConfiguration config;
    public static JavaPlugin dpkCreative;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        config = getConfig();
        dpkCreative = this;

        Bukkit.getPluginCommand("creativeTP").setExecutor(new CreativeTP());
        Bukkit.getPluginCommand("hub").setExecutor(new Hub());
        Bukkit.getPluginCommand("setHub").setExecutor(new SetHub());
        Bukkit.getPluginCommand("hubList").setExecutor(new HubList());
        Bukkit.getPluginCommand("delHub").setExecutor(new DelHub());
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
