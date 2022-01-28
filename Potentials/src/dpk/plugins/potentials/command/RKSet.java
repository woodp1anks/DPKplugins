package dpk.plugins.potentials.command;

import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.RKS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RKSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 2) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        Player player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            commandSender.sendMessage(Config.getMsg("player-offline"));
            return true;
        }
        RKS.set(strings[0], Integer.parseInt(strings[1]));
        commandSender.sendMessage(Config.getMsg("success-set-rks-front") + strings[0] + Config.getMsg("success-set-rks-after") + strings[1]);
        player.sendMessage(Config.getMsg("rks-been-set") + strings[1]);
        return true;
    }
}
