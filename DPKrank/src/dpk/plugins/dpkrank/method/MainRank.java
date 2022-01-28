package dpk.plugins.dpkrank.method;

import dpk.plugins.dpkrank.DPKRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MainRank {
    public static void setRank(String player,int rank) {
        DPKRank.config.set(player,rank);
    }
    public static int getRank(String player) {
        return DPKRank.config.getInt(player);
    }
    public static String getDisplayRank(String player) {
        int rank = getRank(player);
        String displayRank = DPKRank.config.getString("ranks.main-rank.rank" + rank);
        if (displayRank != null) {
            return displayRank;
        }
        return "§c§lNULL";
    }
    public static void reFlashRank(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        String displayRank = getDisplayRank(playerName);
        String weebDisplayRank = WeebRank.getDisplayRank(playerName);
        String PKSDisplayRank = PKSRank.getDisplayRank(playerName);
        String SubDisplayRank = SubRank.getDisplayRank(playerName);
        String switchedRank = getSwitchedRank(playerName);
        player.setDisplayName(playerName);
        if (Objects.equals(switchedRank, "main")) {
            player.setDisplayName(displayRank + " " + ChatColor.RESET + playerName);
            player.setPlayerListName(player.getDisplayName());
            player.setCustomName(player.getDisplayName());
            player.setCustomNameVisible(true);
        }
        if (Objects.equals(switchedRank, "weeb")) {
            player.setDisplayName(weebDisplayRank + " " + ChatColor.RESET + playerName);
            player.setPlayerListName(player.getDisplayName());
            player.setCustomName(player.getDisplayName());
            player.setCustomNameVisible(true);
        }
        if (Objects.equals(switchedRank, "pks")) {
            player.setDisplayName(PKSDisplayRank + " " + ChatColor.RESET + playerName);
            player.setPlayerListName(player.getDisplayName());
            player.setCustomName(player.getDisplayName());
            player.setCustomNameVisible(true);
        }
        if (Objects.equals(switchedRank,"sub")) {
            player.setDisplayName(SubDisplayRank + " " + ChatColor.RESET + playerName);
            player.setPlayerListName(player.getDisplayName());
            player.setCustomName(player.getDisplayName());
            player.setCustomNameVisible(true);
        }
    }
    public static void switchRank(String player,String rankType) {
        DPKRank.config.set("switch-" + player,rankType);
    }
    public static String getSwitchedRank(String player) {
        return DPKRank.config.getString("switch-" + player);
    }
    public static String getItsSwitched_returnSelString(String player,String rankType) {
        if (getSwitchedRank(player).equals(rankType)) {
            return "§a§lSelected";
        }
        return "§c§lNot selected";
    }
}
