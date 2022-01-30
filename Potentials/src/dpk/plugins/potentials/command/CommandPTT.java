package dpk.plugins.potentials.command;

import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.PTT;
import dpk.plugins.potentials.method.RKS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPTT implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length >= 1) {
            if (commandSender.hasPermission("ptt.otherPTT")) {
                Player player = Bukkit.getPlayer(strings[0]);
                if (player == null) {
                    commandSender.sendMessage(Config.getMsg("player-offline"));
                    return true;
                }
                double PTT = dpk.plugins.potentials.method.PTT.get(strings[0]);
                commandSender.sendMessage(Config.getMsg("other-ptt-front") + strings[0] + Config.getMsg("other-ptt-after") + PTT);
                return true;
            }
            commandSender.sendMessage(Config.getMsg("no-other-ptt"));
            return true;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Config.getMsg("no-console"));
            return true;
        }
        commandSender.sendMessage(Config.getMsg("see-PTT") + PTT.get(commandSender.getName()));
        return true;
    }
}
