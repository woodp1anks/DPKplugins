package dpk.plugins.potentials.command;

import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Done implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;

        }        if (Map.getPlayingMap(strings[0]) == null) {
            commandSender.sendMessage(Config.getMsg("player-not-playing-map"));
            return true;
        }
        Map.done(strings[0],Map.getPlayingMap(strings[0]));
        return true;
    }
}
