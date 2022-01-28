package dpk.plugins.dpkeco.method;

import dpk.plugins.dpkeco.DPKEco;

public class MemoryEco {
    public static void set(String player,int amount) {
        DPKEco.config.set("data.core-" + player,amount);
    }
    public static int get(String player) {
        return DPKEco.config.getInt("data.core-" + player);
    }
    public static void give(String player,int amount) {
        set(player,get(player) + amount);
    }
    public static void take(String player,int amount) {
        set(player,get(player) - amount);
    }
    public static void pay(String fromPlayer,String toPlayer,int amount) {
        give(toPlayer,amount);
        take(fromPlayer,amount);
    }
    public static boolean check(String player,int targetAmount) {
        if ((get(player) - targetAmount) < 0) {
            return false;
        }
        return true;
    }
}
