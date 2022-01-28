package dpk.plugins.dpkcreative;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Hub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            if (strings.length == 0) {
                String args0 = DPKCreative.config.getString("default-hub");
                String worldName = DPKCreative.config.getString("hubs." + args0 + ".world");
                World world = Bukkit.getWorld(worldName);
                double x = DPKCreative.config.getDouble("hubs." + args0 + ".x");
                double y = DPKCreative.config.getDouble("hubs." + args0 + ".y");
                double z = DPKCreative.config.getDouble("hubs." + args0 + ".z");
                String playerWorld = ((Player) commandSender).getWorld().getName();
                Location location = new Location(world,x,y,z);
                ((Player) commandSender).teleport(location);
                commandSender.sendMessage(Config.getMsg("success-tp") + x + "," + y + "," + z);
                if (DPKCreative.config.getStringList("creative-worlds").contains(playerWorld)) {
                    if (!commandSender.hasPermission("DPKCreative.noClear")) {
                        if (((Player) commandSender).hasPotionEffect(PotionEffectType.SPEED)) {
                            ((Player) commandSender).removePotionEffect(PotionEffectType.SPEED);
                        }
                        if (((Player) commandSender).hasPotionEffect(PotionEffectType.JUMP)) {
                            ((Player) commandSender).removePotionEffect(PotionEffectType.JUMP);
                        }
                        CommandRun.runCommandAsOp(((Player) commandSender), "clear");
                    }
                }
                return true;
            }
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (!DPKCreative.config.contains("hubs." + strings[0])) {
            commandSender.sendMessage(Config.getMsg("no-this-hub"));
            return true;
        }
        String worldName = DPKCreative.config.getString("hubs." + strings[0] + ".world");
        World world = Bukkit.getWorld(worldName);
        double x = DPKCreative.config.getDouble("hubs." + strings[0] + ".x");
        double y = DPKCreative.config.getDouble("hubs." + strings[0] + ".y");
        double z = DPKCreative.config.getDouble("hubs." + strings[0] + ".z");
        String playerWorld = ((Player) commandSender).getWorld().getName();
        Location location = new Location(world,x,y,z);
        ((Player) commandSender).teleport(location);
        if (DPKCreative.config.getStringList("creative-worlds").contains(playerWorld)) {
            if (!commandSender.hasPermission("DPKCreative.noClear")) {
                if (((Player) commandSender).hasPotionEffect(PotionEffectType.SPEED)) {
                    ((Player) commandSender).removePotionEffect(PotionEffectType.SPEED);
                }
                if (((Player) commandSender).hasPotionEffect(PotionEffectType.JUMP)) {
                    ((Player) commandSender).removePotionEffect(PotionEffectType.JUMP);
                }
                CommandRun.runCommandAsOp(commandSender.getName(),"clear");
                return true;
            }
        }
        return true;
    }
}
