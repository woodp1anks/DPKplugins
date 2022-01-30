package dpk.plugins.potentials.method;

import dpk.plugins.potentials.Potentials;

public class PTT {
    public static void set(String player,double ptt) {
        Potentials.config.set("data.ptt-" + player,ptt);
    }
    public static double get(String player) {
        return Potentials.config.getDouble("data.ptt-" + player);
    }
    public static void give(String player,double ptt) {
        set(player,get(player) + ptt);
    }
}
