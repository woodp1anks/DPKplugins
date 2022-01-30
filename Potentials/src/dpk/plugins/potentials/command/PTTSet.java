package dpk.plugins.potentials.command;

import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.PTT;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PTTSet implements CommandExecutor {
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
        PTT.set(strings[0], Double.parseDouble(strings[1]));
        commandSender.sendMessage(Config.getMsg("success-set-ptt-front") + strings[0] + Config.getMsg("success-set-ptt-after") + strings[1]);
        player.sendMessage(Config.getMsg("ptt-been-set") + strings[1]);
        return true;
    }
}
