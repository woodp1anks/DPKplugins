package dpk.plugins.dpkcoin.listeners;

import dpk.plugins.dpkcoin.DPKcoin;
import dpk.plugins.dpkcoin.methods.Coins;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InitPlayerCoin implements Listener {
    @EventHandler
    public void e(PlayerJoinEvent e) {
        String playerName = e.getPlayer().getName();
        if (DPKcoin.config.get(playerName) != null) {
            return;
        }
        Coins.initPlayerCoin(playerName);
    }
}
