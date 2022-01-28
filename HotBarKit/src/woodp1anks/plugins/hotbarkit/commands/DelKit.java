package woodp1anks.plugins.hotbarkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import woodp1anks.plugins.hotbarkit.HotBarKit;
import woodp1anks.plugins.hotbarkit.api.ConfigReader;
import woodp1anks.plugins.hotbarkit.api.KitSaver;

public class DelKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(ConfigReader.getMsg("wrong-command"));
            return false;
        }
        if (!HotBarKit.config.contains("data.hotBarKits." + strings[0])) {
            commandSender.sendMessage(ConfigReader.getMsg("no-this-kit"));
            return true;
        }
        KitSaver.deleteKit(strings[0]);
        commandSender.sendMessage(ConfigReader.getMsg("success-delete-kit"));
        return true;
    }
}
