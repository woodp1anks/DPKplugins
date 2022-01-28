package dpk.plugins.dpkexchange.listeners;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.api.Economy;
import dpk.plugins.dpkexchange.method.Exchange;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.math.BigDecimal;

public class ExchangeListener implements Listener {
    @EventHandler
    public void e(AsyncPlayerChatEvent e) {
        if (Exchange.getExchangeListenMode(e.getPlayer().getName())) {
            String message = e.getMessage();
        }
    }
}
