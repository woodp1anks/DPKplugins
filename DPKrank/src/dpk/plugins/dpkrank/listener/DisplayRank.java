package dpk.plugins.dpkrank.listener;

import dpk.plugins.dpkrank.method.MainRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DisplayRank implements Listener {
    @EventHandler
    public void e(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        MainRank.reFlashRank(player.getName());
    }
}