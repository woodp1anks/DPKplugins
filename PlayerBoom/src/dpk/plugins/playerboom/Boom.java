package dpk.plugins.playerboom;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Boom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(PlayerBoom.config.getString("wrong-command"));
            return false;
        }
        Player player = Bukkit.getPlayer(strings[0]);
        World world = player.getWorld();
        return true;
    }
}
