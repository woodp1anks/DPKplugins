package dpk.plugins.dpkcreative;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        String world = ((Player) commandSender).getWorld().getName();
        double x = ((Player) commandSender).getLocation().getX();
        double y = ((Player) commandSender).getLocation().getY();
        double z = ((Player) commandSender).getLocation().getZ();
        DPKCreative.config.set("hubs." + strings[0] + ".world",world);
        DPKCreative.config.set("hubs." + strings[0] + ".x",x);
        DPKCreative.config.set("hubs." + strings[0] + ".y",y);
        DPKCreative.config.set("hubs." + strings[0] + ".z",z);
        List<String> hubs = DPKCreative.config.getStringList("hubList");
        hubs.add(strings[0]);
        DPKCreative.config.set("hubList", hubs);
        commandSender.sendMessage(Config.getMsg("success-set-hub"));
        return true;
    }
}
