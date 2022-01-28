package dpk.plugins.dpkrank;

import dpk.plugins.dpkrank.commands.*;
import dpk.plugins.dpkrank.listener.DisplayRank;
import dpk.plugins.dpkrank.listener.InitRank;
import dpk.plugins.dpkrank.listener.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DPKRank extends JavaPlugin {
    public static FileConfiguration config;
    public static JavaPlugin DPKRank;
    public static String DPK = "§6§lD§f§lPK §e";
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        DPKRank = this;
        config = getConfig();
        Bukkit.getPluginCommand("setrank").setExecutor(new SetRank());
        Bukkit.getPluginCommand("setweebrank").setExecutor(new SetWeebRank());
        Bukkit.getPluginCommand("setpksrank").setExecutor(new SetPKSRank());
        Bukkit.getPluginCommand("setsubrank").setExecutor(new SetSubRank());
        Bukkit.getPluginCommand("switch").setExecutor(new Switch());
        Bukkit.getPluginManager().registerEvents(new DisplayRank(),this);
        Bukkit.getPluginManager().registerEvents(new InitRank(),this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);
        getLogger().info("§e§lDPKrank loaded! by woodp1anks");
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
