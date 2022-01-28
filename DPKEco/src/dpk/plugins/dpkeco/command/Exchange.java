package dpk.plugins.dpkeco.command;

import dpk.plugins.dpkeco.method.Config;
import dpk.plugins.dpkeco.method.Eco;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Exchange implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (!strings[0].equals("memory")) {
            if (!strings[0].equals("fragments")) {
                commandSender.sendMessage(Config.getMsg("wrong-type"));
                return true;
            }
        }
        String playerName = commandSender.getName();
        if (!Eco.checkCanExchange(playerName,strings[0])) {
            commandSender.sendMessage(Config.getMsg("cant-exchange"));
            return true;
        }
        Eco.exchange(playerName,strings[0]);
        if (strings[0].equals("memory")) {
            commandSender.sendMessage(Config.getMsg("success-exchange-front") + "fragments" + Config.getMsg("success-exchange-after") + strings[0]);
        }
        if (strings[0].equals("fragments")) {
            commandSender.sendMessage(Config.getMsg("success-exchange-front") + "memory" + Config.getMsg("success-exchange-after") + strings[0]);
        }
        return true;
    }
}
