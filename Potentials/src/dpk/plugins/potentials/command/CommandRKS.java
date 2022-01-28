package dpk.plugins.potentials.command;

import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.RKS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRKS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0) {
            if (strings.length == 1) {
                if (commandSender.hasPermission("ptt.otherRKS")) {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (player == null) {
                        commandSender.sendMessage(Config.getMsg("player-offline"));
                        return true;
                    }
                    int RKS = dpk.plugins.potentials.method.RKS.get(strings[0]);
                    commandSender.sendMessage(Config.getMsg("other-rks-front") + strings[0] + Config.getMsg("other-rks-after") + RKS);
                    return true;
                }
                commandSender.sendMessage(Config.getMsg("no-other-rks"));
                return true;
            }
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Config.getMsg("no-console"));
            return true;
        }
        commandSender.sendMessage(Config.getMsg("get-RKS") + RKS.get(commandSender.getName()));
        return true;
    }
}
