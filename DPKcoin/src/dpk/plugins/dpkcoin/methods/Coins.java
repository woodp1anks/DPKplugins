package dpk.plugins.dpkcoin.methods;

import dpk.plugins.dpkcoin.DPKcoin;

public class Coins {
    public static void addCoins(String playerName,int addAmount) {
        int playerCoins = DPKcoin.config.getInt(playerName);
        int amount = playerCoins + addAmount;
        DPKcoin.config.set(playerName,amount);
    }
    public static void takeCoins(String playerName,int takeAmount) {
        int playerCoins = DPKcoin.config.getInt(playerName);
        int amount = playerCoins - takeAmount;
        DPKcoin.config.set(playerName,amount);
    }
    public static int getCoins(String playerName) {
        return DPKcoin.config.getInt(playerName);
    }
    public static void initPlayerCoin(String playerName) {
        DPKcoin.config.set(playerName,0);
    }
    public static boolean checkCoin(String playerName,int takeCoin) {
        if (getCoins(playerName) - takeCoin < 0) {
            return false;
        }
        return true;
    }
}
