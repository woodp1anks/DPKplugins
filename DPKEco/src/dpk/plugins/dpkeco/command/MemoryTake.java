package dpk.plugins.dpkeco.command;

import dpk.plugins.dpkeco.method.Config;
import dpk.plugins.dpkeco.method.MemoryEco;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MemoryTake implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 2) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        Player player = Bukkit.getPlayer(strings[0]);
        if (player ==  null) {
            commandSender.sendMessage(Config.getMsg("player-offline"));
            return true;
        }
        if (!MemoryEco.check(strings[0], Integer.parseInt(strings[1]))) {
            commandSender.sendMessage(Config.getMsg("player-no-enough-memory"));
            return true;
        }
        MemoryEco.take(strings[0], Integer.parseInt(strings[1]));
        player.sendMessage(Config.getMsg("take-memory") + strings[1]);
        commandSender.sendMessage(Config.getMsg("success-take-memory-front") + strings[1] + Config.getMsg("success-take-memory-after") + strings[0]);
        return true;
    }
}
