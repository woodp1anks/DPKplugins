package dpk.plugins.dpkcoin;

import dpk.plugins.dpkcoin.commands.AddCoin;
import dpk.plugins.dpkcoin.commands.GetCoin;
import dpk.plugins.dpkcoin.commands.TakeCoin;
import dpk.plugins.dpkcoin.listeners.InitPlayerCoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DPKcoin extends JavaPlugin {
    // config
    public static FileConfiguration config;
    // DPK name
    public static String DPK = ChatColor.GOLD + "D" + ChatColor.WHITE + "PK" + ChatColor.YELLOW;
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        config = getConfig();
        getLogger().info(ChatColor.BOLD + DPK + ChatColor.GRAY + "维度碎片(?插件加载完成");
        Bukkit.getPluginCommand("coin").setExecutor(new GetCoin());
        Bukkit.getPluginCommand("addcoin").setExecutor(new AddCoin());
        Bukkit.getPluginCommand("takecoin").setExecutor(new TakeCoin());
        Bukkit.getPluginManager().registerEvents(new InitPlayerCoin(),this);
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
