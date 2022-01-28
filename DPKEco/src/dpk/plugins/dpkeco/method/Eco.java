package dpk.plugins.dpkeco.method;

import dpk.plugins.dpkeco.DPKEco;

public class Eco {
    public static boolean checkCanExchange(String player,String targetType) {
        int memoryFragmentsExchangeRate = DPKEco.config.getInt("memory-fragments-exchange-rate");
        if (targetType.equals("memory")) {
            return FragmentsEco.get(player) >= memoryFragmentsExchangeRate;
        }
        if (targetType.equals("fragments")) {
            return MemoryEco.get(player) >= 1;
        }
        return false;
    }
    public static void exchange(String player,String targetType) {
        int memoryFragmentsExchangeRate = DPKEco.config.getInt("memory-fragments-exchange-rate");
        if (targetType.equals("memory")) {
            if (checkCanExchange(player,targetType)) {
                FragmentsEco.take(player,memoryFragmentsExchangeRate);
                MemoryEco.give(player,1);
            }
        }
        if (targetType.equals("fragments")) {
            if (checkCanExchange(player,targetType)) {
                MemoryEco.take(player,1);
                FragmentsEco.give(player,memoryFragmentsExchangeRate);
            }
        }
    }
}
