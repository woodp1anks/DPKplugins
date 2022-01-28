package dpk.plugins.potentials.command;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Play implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Config.getMsg("no-console"));
            return true;
        }
        if (!Potentials.config.contains("maps." + strings[0])) {
            commandSender.sendMessage(Config.getMsg("no-map"));
            return true;
        }
        Map.begin(commandSender.getName(),strings[0]);
        return true;
    }
}
