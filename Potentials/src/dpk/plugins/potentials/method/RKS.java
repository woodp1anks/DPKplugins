package dpk.plugins.potentials.method;

import dpk.plugins.potentials.Potentials;

public class RKS {
    public static void set(String player,int amount) {
        Potentials.config.set("data.rks-" + player,amount);
    }
    public static int get(String player) {
        return Potentials.config.getInt("data.rks-" + player);
    }
    public static void give(String player,int amount) {
        set(player,get(player) + amount);
    }
    public static void take(String player,int amount) {
        set(player,get(player) - amount);
    }
}
