package dpk.plugins.dpkrank.method;

import dpk.plugins.dpkrank.DPKRank;
import org.bukkit.Bukkit;

public class PKSRank {
    public static void setRank(String player,int rank) {
        DPKRank.config.set("PKS-" + player,rank);
    }
    public static int getRank(String player) {
        return DPKRank.config.getInt("PKS-" + player,1);
    }
    public static String getDisplayRank(String player) {
        int rank = getRank(player);
        String displayRank = DPKRank.config.getString("ranks.pks-rank.rank" + rank);
        if (displayRank != null) {
            return displayRank;
        }
        return "§c§lNULL";
    }
}
