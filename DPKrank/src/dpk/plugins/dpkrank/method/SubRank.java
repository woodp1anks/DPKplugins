package dpk.plugins.dpkrank.method;

import dpk.plugins.dpkrank.DPKRank;
import org.bukkit.entity.Player;

public class SubRank {
    public static void setRank(String player,int rank) {
        DPKRank.config.set("sub-" + player, rank);
    }
    public static int getRank(String player) {
        return DPKRank.config.getInt("sub-" + player);
    }
    public static String getDisplayRank(String player) {
        int rank = getRank(player);
        String displayRank = DPKRank.config.getString("ranks.sub-rank.rank" + rank);
        if (displayRank != null) {
            return displayRank;
        }
        return "§c§lNULL";
    }
}
