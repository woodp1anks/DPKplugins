package dpk.plugins.potentials.command;

import dpk.plugins.potentials.Potentials;
import dpk.plugins.potentials.method.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class EditMap implements CommandExecutor {
    int toInt(String str) {
        return Integer.parseInt(str);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 2) {
            commandSender.sendMessage(Config.getMsg("wrong-command"));
            return false;
        }
        if (!strings[1].equals("spawn")) {
            if (!strings[1].equals("end")) {
                if (!strings[1].equals("world")) {
                    if (!strings[1].equals("finishRKS")) {
                        if (!strings[1].equals("rksReq")) {
                            commandSender.sendMessage(Config.getMsg("wrong-edit"));
                            return false;
                        }
                    }
                }
            }
        }
        // 第一次编辑
        if (!Potentials.config.getStringList("mapList").contains(strings[0])) {
            List<String> mapList = Potentials.config.getStringList("mapList");
            mapList.add(strings[0]);
            Potentials.config.set("mapList",mapList);
            Potentials.potentials.saveConfig();
        }
        if (strings[1].equals("spawn")) {
            if (strings.length != 2) {
                if (strings.length == 5) {
                    Potentials.config.set("maps." + strings[0] + ".pos.x",toInt(strings[2]));
                    Potentials.config.set("maps." + strings[0] + ".pos.y",toInt(strings[3]));
                    Potentials.config.set("maps." + strings[0] + ".pos.z",toInt(strings[4]));
                    commandSender.sendMessage(Config.getMsg("success-set-map-spawn"));
                    return true;
                }
                commandSender.sendMessage(Config.getMsg("edit-help-spawn"));
                return true;
            }
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(Config.getMsg("no-console"));
                return true;
            }
            Location location = ((Player) commandSender).getLocation();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            float yaw = location.getYaw();
            float pitch = location.getPitch();
            Potentials.config.set("maps." + strings[0] + ".pos.xa",x);
            Potentials.config.set("maps." + strings[0] + ".pos.y",y);
            Potentials.config.set("maps." + strings[0] + ".pos.z",z);
            Potentials.config.set("maps." + strings[0] + ".pos.yaw",yaw);
            Potentials.config.set("maps." + strings[0] + ".pos.pitch",pitch);
            commandSender.sendMessage(Config.getMsg("success-set-map-spawn"));
        }
        if (strings[1].equals("end")) {
            if (strings.length != 2) {
                if (strings.length == 5) {
                    Potentials.config.set("maps." + strings[0] + ".end.x",toInt(strings[2]));
                    Potentials.config.set("maps." + strings[0] + ".end.y",toInt(strings[3]));
                    Potentials.config.set("maps." + strings[0] + ".end.z",toInt(strings[4]));
                    commandSender.sendMessage(Config.getMsg("success-set-map-end"));
                }
                commandSender.sendMessage(Config.getMsg("edit-help-end"));
                return true;
            }
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(Config.getMsg("no-console"));
                return true;
            }
            Location location = ((Player) commandSender).getLocation();
            double x = Math.round(location.getX());
            double y = Math.round(location.getY());
            double z = Math.round(location.getZ());
            Potentials.config.set("maps." + strings[0] + ".end.x",x);
            Potentials.config.set("maps." + strings[0] + ".end.y",y);
            Potentials.config.set("maps." + strings[0] + ".end.z",z);
            commandSender.sendMessage(Config.getMsg("success-set-map-end"));
        }
        if (strings[1].equals("world")) {
            if (strings.length != 2) {
                if (strings.length == 3) {
                    Potentials.config.set("maps." + strings[0] + ".world",strings[2]);
                    commandSender.sendMessage(Config.getMsg("success-set-map-world"));
                    return true;
                }
                commandSender.sendMessage(Config.getMsg("edit-help-world"));
                return true;
            }
            String world = ((Player) commandSender).getWorld().getName();
            Potentials.config.set("maps." + strings[0] + ".world",world);
            commandSender.sendMessage(Config.getMsg("success-set-map-world"));
        }
        if (strings[1].equals("finishRKS")) {
            if (strings.length != 3) {
                commandSender.sendMessage(Config.getMsg("edit-help-finishRKS"));
                return true;
            }
            Potentials.config.set("maps." + strings[0] + ".finishRKS",toInt(strings[2]));
            commandSender.sendMessage(Config.getMsg("success-set-map-finishRKS"));
        }
        if (strings[1].equals("rksReq")) {
            if (strings.length != 3) {
                commandSender.sendMessage(Config.getMsg("edit-help-rksReq"));
                return true;
            }
            Potentials.config.set("maps." + strings[0] + ".rksReq",toInt(strings[2]));
            commandSender.sendMessage(Config.getMsg("success-set-map-rksReq"));
        }
        return true;
    }
}
