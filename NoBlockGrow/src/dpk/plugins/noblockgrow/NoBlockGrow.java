package dpk.plugins.noblockgrow;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoBlockGrow extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Listener(),this);
    }
}
