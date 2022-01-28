package woodp1anks.plugins.hotbarkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import woodp1anks.plugins.hotbarkit.HotBarKit;
import woodp1anks.plugins.hotbarkit.api.ConfigReader;
import woodp1anks.plugins.hotbarkit.api.KitSaver;

public class LoadKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(ConfigReader.getMsg("wrong-command"));
            return false;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigReader.getMsg("no-console"));
            return true;
        }
        if (!HotBarKit.config.contains("data.hotBarKits." + strings[0])) {
            commandSender.sendMessage(ConfigReader.getMsg("no-this-kit"));
            return true;
        }
        Player sender = (Player) commandSender;
        PlayerInventory senderInv = sender.getInventory();
        ItemStack[] kit = KitSaver.getKit(strings[0]);
        for (int i = 0;;) {
            if (i >= 9) {
                break;
            }
            senderInv.setItem(i,kit[i]);
            i++;
        }
        commandSender.sendMessage(ConfigReader.getMsg("success-load-kit"));
        return true;
    }
}
