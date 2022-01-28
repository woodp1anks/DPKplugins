package dpk.plugins.dpkeco.command;

import dpk.plugins.dpkeco.DPKEco;
import dpk.plugins.dpkeco.method.FragmentsEco;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FragmentsSet implements CommandExecutor {
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
        FragmentsEco.set(strings[0], Integer.parseInt(strings[1]));
        commandSender.sendMessage(DPKEco.config.getString("messages.success-set-fragments") + strings[1]);
        player.sendMessage(DPKEco.config.getString("messages.fragments-been-set") + strings[1]);
        return true;
    }
}
