package dpk.plugins.potentials;

import dpk.plugins.potentials.command.*;
import dpk.plugins.potentials.listener.CheatListener;
import dpk.plugins.potentials.listener.DoneMapListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Potentials extends JavaPlugin {
    public static FileConfiguration config;
    public static JavaPlugin potentials;
    public static Date serverDate;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        config = getConfig();
        potentials = this;
        serverDate = new Date();

        Bukkit.getPluginManager().registerEvents(new DoneMapListener(),potentials);
        // Bukkit.getPluginManager().registerEvents(new ReStartMapListener(),potentials);
        Bukkit.getPluginManager().registerEvents(new CheatListener(),potentials);

        Bukkit.getPluginCommand("play").setExecutor(new Play());
        Bukkit.getPluginCommand("RKSet").setExecutor(new RKSet());
        Bukkit.getPluginCommand("PTTSet").setExecutor(new PTTSet());
        Bukkit.getPluginCommand("ranking").setExecutor(new CommandRKS());
        Bukkit.getPluginCommand("pttRanking").setExecutor(new CommandPTT());
        Bukkit.getPluginCommand("stopPlay").setExecutor(new StopPlay());
        Bukkit.getPluginCommand("done").setExecutor(new Done());
        Bukkit.getPluginCommand("delMap").setExecutor(new DelMap());
        Bukkit.getPluginCommand("editMap").setExecutor(new EditMap());
        Bukkit.getPluginCommand("mapList").setExecutor(new MapList());
        Bukkit.getPluginCommand("rePlay").setExecutor(new RePlay());
        Bukkit.getPluginCommand("broad").setExecutor(new Broad());
        Bukkit.getPluginCommand("mapInfo").setExecutor(new MapInfo());
        ByteArrayOutputStream exm = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(exm);
            outputStream.writeObject(new ItemStack(Material.AIR));
            getLogger().info(Arrays.toString(exm.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
