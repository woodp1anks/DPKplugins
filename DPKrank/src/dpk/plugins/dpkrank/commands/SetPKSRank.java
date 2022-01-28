package dpk.plugins.dpkrank.commands;

import dpk.plugins.dpkrank.DPKRank;
import dpk.plugins.dpkrank.method.MainRank;
import dpk.plugins.dpkrank.method.PKSRank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPKSRank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 2) {
            commandSender.sendMessage(DPKRank.config.getString("messages.wrong-command"));
            return false;
        }
        if (Bukkit.getPlayer(strings[0]) == null) {
            commandSender.sendMessage(DPKRank.config.getString("messages.player-offline"));
            return true;
        }
        PKSRank.setRank(strings[0],Integer.parseInt(strings[1]));
        Player player = Bukkit.getPlayer(strings[0]);
        player.sendMessage(DPKRank.config.getString("messages.rankup") + "PKSRank " + PKSRank.getDisplayRank(strings[0]));
        Bukkit.broadcastMessage(DPKRank.config.getString("messages.broadcast-rankup-front") + strings[0] + DPKRank.config.getString("messages.broadcast-rankup-after") + "PKSrank " + PKSRank.getDisplayRank(strings[0]));
        MainRank.reFlashRank(strings[0]);
        return true;
    }
}
