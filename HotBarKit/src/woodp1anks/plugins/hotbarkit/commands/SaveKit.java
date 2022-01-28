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

import java.util.List;

public class SaveKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(ConfigReader.getMsg("wrong-command"));
            return true;
        }
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ConfigReader.getMsg("no-console"));
            return true;
        }
        Player sender = (Player) commandSender;
        PlayerInventory senderInv = sender.getInventory();
        ItemStack[] hotBar = new ItemStack[9];
        for (int i = 0;;) {
            if (i >= 9) {
                break;
            }
            hotBar[i] = senderInv.getItem(i);
            i = i + 1;
        }
        KitSaver.saveKit(hotBar,strings[0]);
        List<String> kitList = HotBarKit.config.getStringList("data.kitList");
        kitList.add(strings[0]);
        HotBarKit.config.set("data.kitList",kitList);
        commandSender.sendMessage(ConfigReader.getMsg("success-save-kit"));
        return true;
    }
}
