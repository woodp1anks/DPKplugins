package dpk.plugins.dpkcreative;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HubList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> hubs = DPKCreative.config.getStringList("hubList");
        if (hubs.isEmpty()) {
            commandSender.sendMessage(Config.getMsg("no-any-hub-in-server"));
            return true;
        }
        commandSender.sendMessage(Config.getMsg("hub-list-head"));
        for (int i = 0;;i++) {
            try {
                String currentHub = hubs.get(i);
                commandSender.sendMessage(Config.getMsg("hub-list-front-spawn") + currentHub);
            } catch (IndexOutOfBoundsException ex) {
                break;
            }
        }
        return true;
    }
}
