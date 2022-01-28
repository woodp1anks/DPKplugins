package dpk.plugins.dpkcoin.commands;

import dpk.plugins.dpkcoin.DPKcoin;
import dpk.plugins.dpkcoin.methods.Coins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCoin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 0) {
            if (strings.length == 1) {
                Player player = Bukkit.getPlayer(strings[0]);
                if (player == null) {
                    commandSender.sendMessage(DPKcoin.DPK + ChatColor.RED + " 你想看谁的金币啊:(这人不在啊:(");
                    return true;
                }
                int playerCoins = Coins.getCoins(strings[0]);
                commandSender.sendMessage(DPKcoin.DPK + strings[0] + "have" + playerCoins + "coins!");
                return true;
            }
            commandSender.sendMessage(DPKcoin.DPK + ChatColor.RED + " 你输错指令了！你应该照下面这样输指令:");
            return false;
        }
        String senderName = commandSender.getName();
        int playerCoins = Coins.getCoins(senderName);
        commandSender.sendMessage(DPKcoin.DPK + " You have " + playerCoins + " coins!");
        return true;
    }
}
