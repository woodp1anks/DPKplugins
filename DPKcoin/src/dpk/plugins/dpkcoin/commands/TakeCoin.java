package dpk.plugins.dpkcoin.commands;

import dpk.plugins.dpkcoin.DPKcoin;
import dpk.plugins.dpkcoin.methods.Coins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TakeCoin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 2) {
            commandSender.sendMessage(DPKcoin.DPK + ChatColor.RED + " 你输错指令了！你应该照下面这样输指令:");
            return false;
        }
        Player player = Bukkit.getPlayer(strings[1]);
        if (!player.hasPermission("dpkcoin.command.takecoin")) {
            commandSender.sendMessage(DPKcoin.DPK + ChatColor.RED + " peach");
            return true;
        }
        if (player == null) {
            commandSender.sendMessage(DPKcoin.DPK + ChatColor.RED + " 你拿给谁金币啊:(这人不在啊:(");
            return true;
        }
        if (!Coins.checkCoin(strings[1],Integer.parseInt(strings[0]))) {
            commandSender.sendMessage(DPKcoin.DPK + ChatColor.RED + " :(你想要给人来个负金币吗(");
            return true;
        }
        Coins.takeCoins(strings[1],Integer.parseInt(strings[0]));
        commandSender.sendMessage(DPKcoin.DPK + " 成功拿走了 " + strings[1] + " " + strings[0] + " coins!");
        return true;
    }
}
