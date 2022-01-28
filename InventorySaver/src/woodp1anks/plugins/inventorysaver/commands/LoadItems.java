package woodp1anks.plugins.inventorysaver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import woodp1anks.plugins.inventorysaver.api.ConfigItemsSaver;
import woodp1anks.plugins.inventorysaver.api.ConfigReader;

public class LoadItems implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(ConfigReader.getMsg("wrong-command"));
            return false;
        }
        Inventory inventory = ConfigItemsSaver.getItems(strings[0]);
        ((Player) commandSender).getInventory().set
        return true;
    }
}
