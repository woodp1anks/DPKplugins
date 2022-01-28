package woodp1anks.plugins.hotbarkit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import woodp1anks.plugins.hotbarkit.commands.DelKit;
import woodp1anks.plugins.hotbarkit.commands.KitList;
import woodp1anks.plugins.hotbarkit.commands.LoadKit;
import woodp1anks.plugins.hotbarkit.commands.SaveKit;

public class HotBarKit extends JavaPlugin {
    public static HotBarKit self;
    public static FileConfiguration config;
    public void onLoad() {
        saveDefaultConfig();
    }
    public void onEnable() {
        self = this;
        config = getConfig();
        Bukkit.getPluginCommand("SaveKit").setExecutor(new SaveKit());
        Bukkit.getPluginCommand("LoadKit").setExecutor(new LoadKit());
        Bukkit.getPluginCommand("DeleteKit").setExecutor(new DelKit());
        Bukkit.getPluginCommand("KitList").setExecutor(new KitList());
    }
    public void onDisable() {
        saveConfig();
    }
}
