package dpk.plugins.noblockgrow;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.world.WorldEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void e(BlockGrowEvent e) {
        e.setCancelled(true);
    }
}
