package dpk.plugins.potentials.command;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DelMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (!Potentials.config.contains("maps." + strings[0])) {
            commandSender.sendMessage(Config.getMsg("no-map"));
            return true;
        }
        if (Potentials.config.getStringList("mapList").contains(strings[0])) {
            List<String> list = Potentials.config.getStringList("mapList");
            list.remove(strings[0]);
            Potentials.config.set("mapList",list);
        }
        Potentials.config.set("maps." + strings[0],null);
        commandSender.sendMessage(Config.getMsg("success-del-map") + strings[0]);
        Potentials.potentials.saveConfig();
        return true;
    }
}
