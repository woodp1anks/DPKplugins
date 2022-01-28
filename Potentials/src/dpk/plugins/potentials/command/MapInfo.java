package dpk.plugins.potentials.command;

import com.earth2me.essentials.libs.checkerframework.checker.units.qual.C;
import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MapInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        String mapName = strings[0];
        String world = Potentials.config.getString("maps." + mapName + ".world");
        int rksReq = Potentials.config.getInt("maps." + mapName + ".rksReq");
        int finishRKS = Potentials.config.getInt("maps." + mapName + ".finishRKS");
        commandSender.sendMessage(Config.getMsg("info-head") + mapName);
        commandSender.sendMessage(Config.getMsg("info-world") + world);
        commandSender.sendMessage(Config.getMsg("info-rksReq") + rksReq);
        commandSender.sendMessage(Config.getMsg("info-finishRKS") + finishRKS);
        return true;
    }
}
