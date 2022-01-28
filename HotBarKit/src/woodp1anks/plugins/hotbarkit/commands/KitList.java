package woodp1anks.plugins.hotbarkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import woodp1anks.plugins.hotbarkit.HotBarKit;
import woodp1anks.plugins.hotbarkit.api.ConfigReader;

import java.util.List;

public class KitList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> kits = HotBarKit.config.getStringList("data.kitList");
        if (kits.isEmpty()) {
            commandSender.sendMessage(ConfigReader.getMsg("no-kit-in-server"));
            return true;
        }
        commandSender.sendMessage(ConfigReader.getMsg("kit-list-head"));
        for (int i = 0;;i++) {
            if (i > kits.size() - 1) {
                break;
            }
            commandSender.sendMessage(ConfigReader.getMsg("kit-list-kit-prefix") + kits.get(i));
        }
        return true;
    }
}
