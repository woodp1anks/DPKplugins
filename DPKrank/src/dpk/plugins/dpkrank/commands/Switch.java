package dpk.plugins.dpkrank.commands;

import dpk.plugins.dpkrank.DPKRank;
import dpk.plugins.dpkrank.menus.SwitchRankMenu;
import dpk.plugins.dpkrank.method.MainRank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Switch implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            if (strings.length == 0) {
                new SwitchRankMenu( (Player) commandSender).open();
                return true;
            }
            commandSender.sendMessage(DPKRank.config.getString("messages.wrong-command"));
            return false;
        }
        if (!Objects.equals(strings[0], "main")) {
            if (!Objects.equals(strings[0], "weeb")) {
                if (!Objects.equals(strings[0],"sub")) {
                    if (!Objects.equals(strings[0], "pks")) {
                        commandSender.sendMessage(DPKRank.config.getString("messages.wrong-switch"));
                        return false;
                    }
                }
            }
        }
        String playerName = commandSender.getName();
        MainRank.switchRank(playerName,strings[0]);
        commandSender.sendMessage(DPKRank.config.getString("messages.switched-rank") + strings[0]);
        MainRank.reFlashRank(playerName);
        return true;
    }
}
