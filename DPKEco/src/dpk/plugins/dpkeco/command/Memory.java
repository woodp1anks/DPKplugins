package dpk.plugins.dpkeco.command;

import dpk.plugins.dpkeco.DPKEco;
import dpk.plugins.dpkeco.method.MemoryEco;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Memory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0) {
            if (strings.length == 1) {
                if (commandSender.hasPermission("DPKEco.eco.memory.seeOther")) {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (player == null) {
                        commandSender.sendMessage(DPKEco.config.getString("messages.player-offline"));
                        return true;
                    }
                    commandSender.sendMessage(DPKEco.config.getString("messages.other-memory-front") + strings[0] + DPKEco.config.getString("messages.other-memory-after") + MemoryEco.get(strings[0]));
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
        commandSender.sendMessage(DPKEco.config.getString("messages.check-memory") + MemoryEco.get(commandSender.getName()));
        return true;
    }
}
