package dpk.plugins.potentials.listener;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Map;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ReStartMapListener implements Listener {
    @EventHandler
    public void e(PlayerMoveEvent e) {
        String playerName = e.getPlayer().getName();
        String playingMap = Map.getPlayingMap(playerName);
        if (Potentials.config.get("data.cd-" + playerName) != null) {
            return;
        }
        if (playingMap == null) {
            return;
        }
        Location to = e.getTo();
        double toX = to.getX();
        double toY = to.getY();
        double toZ = to.getZ();
        double XRound = Math.round(toX);
        double YRound = Math.round(toY);
        double ZRound = Math.round(toZ);
        double spawnX = Potentials.config.getDouble("maps." + playingMap + ".pos.x");
        double spawnY = Potentials.config.getDouble("maps." + playingMap + ".pos.y");
        double spawnZ = Potentials.config.getDouble("maps." + playingMap + ".pos.z");
        if (XRound == spawnX && YRound == spawnY && ZRound == spawnZ) {
            Map.reStart(playerName);
            Potentials.config.set("data.cd-" + playerName,1);
            try {
                wait(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Potentials.config.set("data.cd-" + playerName,null);
        }
    }
}
