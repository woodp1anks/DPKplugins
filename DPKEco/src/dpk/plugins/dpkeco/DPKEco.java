package dpk.plugins.dpkeco;

import dpk.plugins.dpkeco.command.*;
import dpk.plugins.dpkeco.listener.EcoInit;
import dpk.plugins.dpkeco.method.Command;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DPKEco extends JavaPlugin {
    public static JavaPlugin DPKEco;
    public static FileConfiguration config;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        DPKEco = this;
        config = getConfig();

        Bukkit.getPluginManager().registerEvents(new EcoInit(),this);

        Bukkit.getPluginCommand("fragments").setExecutor(new Fragments());
        Bukkit.getPluginCommand("fragmentsGive").setExecutor(new FragmentsGive());
        Bukkit.getPluginCommand("fragmentsSet").setExecutor(new FragmentsSet());
        Bukkit.getPluginCommand("fragmentsTake").setExecutor(new FragmentsTake());
        Bukkit.getPluginCommand("fragmentsPay").setExecutor(new FragmentsPay());
        Command.registerCommand("memory",new Memory());
        Command.registerCommand("memoryGive",new MemoryGive());
        Command.registerCommand("memorySet",new MemorySet());
        Command.registerCommand("memoryTake",new MemoryTake());
        Command.registerCommand("memoryPay",new MemoryPay());
        Command.registerCommand("exchange",new Exchange());
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
