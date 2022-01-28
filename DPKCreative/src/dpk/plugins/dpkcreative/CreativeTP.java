package dpk.plugins.dpkcreative;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreativeTP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 3) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        World world = ((Player) commandSender).getWorld();
        if (!DPKCreative.config.getStringList("creative-worlds").contains(world.getName())) {
            commandSender.sendMessage(Config.getMsg("not-in-creative-world"));
            return true;
        }
        double x = Double.parseDouble(strings[0]);
        double y = Double.parseDouble(strings[1]);
        double z = Double.parseDouble(strings[2]);
        Location location = new Location(world,x,y,z);
        ((Player) commandSender).teleport(location);
        commandSender.sendMessage(Config.getMsg("success-tp") + strings[0] + "," + strings[1] + "," + strings[2]);
        return true;
    }
}
