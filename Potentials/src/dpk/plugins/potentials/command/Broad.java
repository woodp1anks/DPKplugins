package dpk.plugins.potentials.command;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broad implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (Potentials.config.contains("data."))
        commandSender.sendMessage(Config.getMsg("broad-head") + strings[0]);
        for (int i = 1;;) {
            if (i > 10) {
                break;
            }
            if (Map.getNameOnBroad(strings[0],i).contains("null")) {
                break;
            }
            commandSender.sendMessage(ChatColor.getByChar(Potentials.config.getString("broad-prefix-color")) + String.valueOf(i) + ". " + ChatColor.getByChar(Potentials.config.getString("broad-player-name-color")) + Map.getNameOnBroad(strings[0],i) + ChatColor.GRAY + " - " + ChatColor.LIGHT_PURPLE + Map.getTimeOnBroad(strings[0],i) + " Ticks " + Potentials.config.getLong("data.play-second-" + Map.getNameOnBroad(strings[0],i)) + " S");
            i++;
        }
        return true;
    }
}
