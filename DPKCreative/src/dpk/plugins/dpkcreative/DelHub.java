package dpk.plugins.dpkcreative;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DelHub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        List<String> hubs = DPKCreative.config.getStringList("hubList");
        if (!hubs.contains(strings[0])) {
            commandSender.sendMessage(Config.getMsg("no-this-hub"));
            return true;
        }
        DPKCreative.config.set("hubs." + strings[0],null);
        hubs.remove(strings[0]);
        DPKCreative.config.set("hubList", hubs);
        commandSender.sendMessage(Config.getMsg("success-del-hub"));
        return true;
    }
}
