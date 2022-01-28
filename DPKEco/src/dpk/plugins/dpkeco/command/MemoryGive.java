package dpk.plugins.dpkeco.command;

import dpk.plugins.dpkeco.DPKEco;
import dpk.plugins.dpkeco.method.Config;
import dpk.plugins.dpkeco.method.MemoryEco;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MemoryGive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 2) {
            commandSender.sendMessage(DPKEco.config.getString("messages.wrong-command"));
            return false;
        }
        Player player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            commandSender.sendMessage(DPKEco.config.getString("messages.player-offline"));
            return true;
        }
        MemoryEco.give(strings[0], Integer.parseInt(strings[1]));
        player.sendMessage(DPKEco.config.getString("messages.get-memory") + strings[1]);
        commandSender.sendMessage(DPKEco.config.getString("messages.success-give-memory-front") + strings[0] + Config.getMsg("success-give-memory-after") + strings[1]);
        return true;
    }
}
