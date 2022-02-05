package dpk.plugins.potentials.listener;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Map;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DoneMapListener implements Listener {
    @EventHandler
    public void e(PlayerMoveEvent e) {
        String playerName = e.getPlayer().getName();
        String playingMap = Map.getPlayingMap(playerName);
        if (!e.getPlayer().hasPermission("prac.off")) {
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
        double endX = Potentials.config.getDouble("maps." + playingMap + ".end.x");
        double endY = Potentials.config.getDouble("maps." + playingMap + ".end.y");
        double endZ = Potentials.config.getDouble("maps." + playingMap + ".end.z");
        if (XRound == endX) {
            if (YRound == endY) {
                if (ZRound == endZ) {
                    Map.done(playerName,playingMap);
                }
            }
        }
    }
}
