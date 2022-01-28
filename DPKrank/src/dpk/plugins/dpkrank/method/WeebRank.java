package dpk.plugins.dpkrank.method;

import dpk.plugins.dpkrank.DPKRank;

public class WeebRank {
    public static void setRank(String player,int rank) {
        DPKRank.config.set("weeb-" + player,rank);
    }
    public static int getRank(String player) {
        return DPKRank.config.getInt("weeb-" + player,1);
    }
    public static String getDisplayRank(String player) {
        int rank = getRank(player);
        String displayRank = DPKRank.config.getString("ranks.weeb-rank.rank" + rank);
        if (displayRank != null) {
            return displayRank;
        }
        return "§c§lNULL";
    }
}
