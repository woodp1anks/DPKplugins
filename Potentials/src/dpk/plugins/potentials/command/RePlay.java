package dpk.plugins.potentials.command;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import dpk.plugins.potentials.method.Map;
import net.milkbowl.vault.permission.plugins.Permission_GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RePlay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String playerName = commandSender.getName();
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Config.getMsg("no-console"));
            return true;
        }
        if (!Map.checkIsPlayingMap(playerName)) {
            commandSender.sendMessage(Config.getMsg("not-playing-map"));
            return true;
        }
        Map.reStart(playerName);
        String playingMap = Map.getPlayingMap(playerName);
        World world = Bukkit.getWorld(Potentials.config.getString("maps." + playingMap + ".world"));
        long x = Potentials.config.getLong("maps." + playingMap + ".pos.x");
        long y = Potentials.config.getLong("maps." + playingMap + ".pos.y");
        long z = Potentials.config.getLong("maps." + playingMap + ".pos.z");
        float yaw = Potentials.config.getInt("maps." + playingMap + ".pos.yaw");
        float pitch = Potentials.config.getInt("maps." + playingMap + ".pos.pitch");
        Location location = new Location(world,x,y,z,yaw,pitch);
        Potentials.config.set("data.is-restarting-" + playerName,true);
        ((Player) commandSender).teleport(location);
        Potentials.config.set("data.is-restarting-" + playerName,null);
        return true;
    }
}
