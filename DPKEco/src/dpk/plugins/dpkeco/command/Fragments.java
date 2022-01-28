package dpk.plugins.dpkeco.command;

import dpk.plugins.dpkeco.DPKEco;
import dpk.plugins.dpkeco.method.FragmentsEco;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fragments implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0) {
            if (strings.length == 1) {
                if (commandSender.hasPermission("DPKEco.eco.fragments.seeOther")) {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (player == null) {
                        commandSender.sendMessage(DPKEco.config.getString("messages.player-offline"));
                        return true;
                    }
                    commandSender.sendMessage(DPKEco.config.getString("messages.other-fragments-front") + strings[0] + DPKEco.config.getString("messages.other-fragments-after") + FragmentsEco.get(strings[0]));
                    return true;
                }
            }
            commandSender.sendMessage(DPKEco.config.getString("messages.wrong-command"));
            return false;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(DPKEco.config.getString("messages.no-console"));
            return true;
        }
        commandSender.sendMessage(DPKEco.config.getString("messages.check-fragments") + FragmentsEco.get(commandSender.getName()));
        return true;
    }
}
