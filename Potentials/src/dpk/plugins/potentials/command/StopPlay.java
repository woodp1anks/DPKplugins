package dpk.plugins.potentials.command;

import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopPlay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (Map.getPlayingMap(commandSender.getName()) == null) {
            commandSender.sendMessage(Config.getMsg("not-playing-map"));
            return true;
        }
        Map.endPlaying(commandSender.getName());
        commandSender.sendMessage(Config.getMsg("success-stop-play"));
        return true;
    }
}
