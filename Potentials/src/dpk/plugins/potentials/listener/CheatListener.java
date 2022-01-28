package dpk.plugins.potentials.listener;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

public class CheatListener implements Listener {
    @EventHandler
    public void e(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        if (Potentials.config.getBoolean("data.is-restarting-" + playerName)) {
            return;
        }
        if (player.hasPermission("ptt.cheat-bypass")) {
            return;
        }
        if (Map.checkIsPlayingMap(playerName)) {
            player.sendMessage(Config.getMsg("playing-cheat"));
            Map.endPlaying(playerName);
        }
    }
    @EventHandler
    public void e(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        if (Potentials.config.getBoolean("data.is-restarting-" + playerName)) {
            return;
        }
        if (player.hasPermission("ptt.cheat-bypass")) {
            return;
        }
        if (player.isFlying() && Map.checkIsPlayingMap(playerName)) {
            player.sendMessage(Config.getMsg("playing-cheat"));
            Map.endPlaying(playerName);
        }
        if (player.hasPotionEffect(PotionEffectType.SPEED) || player.hasPotionEffect(PotionEffectType.JUMP)) {
            if (Map.checkIsPlayingMap(playerName)) {
                player.sendMessage(Config.getMsg("playing-cheat"));
                Map.endPlaying(playerName);
                if (player.hasPotionEffect(PotionEffectType.JUMP)) {player.removePotionEffect(PotionEffectType.JUMP);}
                if (player.hasPotionEffect(PotionEffectType.SPEED)) {player.removePotionEffect(PotionEffectType.SPEED);}
            }
        }
    }
}
