package dpk.plugins.dpkcreative;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandRun {
    public static void runCommandAsOp(String player,String command) {
        Player player1 = Bukkit.getPlayer(player);
        if (!player1.isOp()) {
            player1.setOp(true);
            player1.performCommand(command);
            player1.setOp(false);
        } else if (player1.isOp()) {
            player1.performCommand(command);
        }
    }
    public static void runCommandAsOp(Player player,String command) {
        if (!player.isOp()) {
            player.setOp(true);
            player.performCommand(command);
            player.setOp(false);
        } else if (player.isOp()) {
            player.performCommand(command);
        }
    }
}
