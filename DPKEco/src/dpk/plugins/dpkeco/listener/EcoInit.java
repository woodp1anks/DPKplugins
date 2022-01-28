package dpk.plugins.dpkeco.listener;

import ch.njol.skript.variables.Variables;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import dpk.plugins.dpkeco.DPKEco;
import dpk.plugins.dpkeco.method.FragmentsEco;
import dpk.plugins.dpkeco.method.MemoryEco;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EcoInit implements Listener {
    @EventHandler
    public void e(PlayerJoinEvent e) {
        String playerName = e.getPlayer().getName();
        if (!DPKEco.config.contains("data.frag-" + playerName)) {
            if (DPKEco.config.getBoolean("ess-eco-to-fragments-when-first")) {
                try {
                    double essCoins = Economy.getMoney(playerName);
                    int skMemory = (int) Variables.getVariable("memory." + playerName,null,false);
                    e.getPlayer().sendMessage(String.valueOf(skMemory));
                    FragmentsEco.set(playerName,(int) essCoins);
                    MemoryEco.set(playerName,skMemory);
                } catch (UserDoesNotExistException ex) {
                    FragmentsEco.set(playerName,DPKEco.config.getInt("default-fragments"));
                    MemoryEco.set(playerName,DPKEco.config.getInt("default-memory"));
                } catch (NullPointerException ex) {
                    try {
                        FragmentsEco.set(playerName,(int) Economy.getMoney(playerName));
                        MemoryEco.set(playerName,DPKEco.config.getInt("default-memory"));
                    } catch (UserDoesNotExistException exc) {
                        FragmentsEco.set(playerName,DPKEco.config.getInt("default-fragments"));
                        MemoryEco.set(playerName,DPKEco.config.getInt("default-memory"));
                    }
                    MemoryEco.set(playerName,DPKEco.config.getInt("default-memory"));
                }
            } else {
                FragmentsEco.set(playerName,DPKEco.config.getInt("default-fragments"));
                MemoryEco.set(playerName,DPKEco.config.getInt("default-memory"));
            }
        }
    }
}
