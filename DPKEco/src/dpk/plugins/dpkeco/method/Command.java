package dpk.plugins.dpkeco.method;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

public class Command {
    public static void registerCommand(String command,CommandExecutor executor) {
        Bukkit.getPluginCommand(command).setExecutor(executor);
    }
}
