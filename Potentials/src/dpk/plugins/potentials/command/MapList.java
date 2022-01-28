package dpk.plugins.potentials.command;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MapList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (Integer.parseInt(strings[0]) <= 0) {
            commandSender.sendMessage(Config.getMsg("map-list-error-page"));
            return true;
        }
        List<String> maps = Potentials.config.getStringList("mapList");
        if (maps == null) {
            commandSender.sendMessage(Config.getMsg("no-map-in-server"));
            return true;
        }
        int viewMap = (Integer.parseInt(strings[0]) - 1) * 10 - 1;
        if (strings[0].equals("1")) {
            viewMap = 0;
        }
        commandSender.sendMessage(Config.getMsg("map-list-head") + strings[0]);
        for(int i = viewMap;;i++) {
            try {
                String currentMap = maps.get(i);
                if (i >= viewMap + 12) {
                    break;
                }
                commandSender.sendMessage(Config.getMsg("map-list-front-map") + currentMap);
            } catch (IndexOutOfBoundsException ex) {
                break;
            }
        }
        return true;
    }
}
