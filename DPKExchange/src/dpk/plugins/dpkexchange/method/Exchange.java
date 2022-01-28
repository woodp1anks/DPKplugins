package dpk.plugins.dpkexchange.method;

import dpk.plugins.dpkexchange.DPKExchange;
import org.bukkit.ChatColor;

public class Exchange {
    public static boolean checkCouldExchange(String message,String targetType,String player) {
        String exchangeCore = ChatColor.GOLD + "" + ChatColor.BOLD + "Dimension " + ChatColor.WHITE + "" + ChatColor.BOLD + "PK > " + ChatColor.WHITE + "成功为 " + ChatColor.YELLOW + "" + ChatColor.BOLD + player + ChatColor.WHITE + " 添加 " + ChatColor.GREEN + "" + ChatColor.BOLD + "1 " + ChatColor.WHITE + "维度核心！";
        String exchangeCoin = ChatColor.GOLD + "" + ChatColor.BOLD + "Dimension " + ChatColor.WHITE + "" + ChatColor.BOLD + "PK > " + ChatColor.GREEN + "你消耗了 " + ChatColor.AQUA + "" + ChatColor.BOLD + "1 " + ChatColor.GREEN + "维度核心，正在进行下一步";
        String exchangeCoin2 = ChatColor.GREEN + "$1000 已添加到你的银行账户";
        if (targetType.equals("core")) {
            return message.equals(exchangeCore);
        }
        if (targetType.equals("coin")) {
            if (!message.equals(exchangeCoin)) {
                if (!message.equals(exchangeCoin2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static void setExchangeListenMode(String player,boolean choose) {
        DPKExchange.config.set("exchange-listen-" + player,choose);
    }
    public static boolean getExchangeListenMode(String player) {
        return DPKExchange.config.getBoolean(player,false);
    }
}
