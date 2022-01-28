package dpk.plugins.dpkeco.method;

import ch.njol.skript.Skript;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import dpk.plugins.dpkeco.DPKEco;
import net.ess3.api.MaxMoneyException;

public class FragmentsEco {
    public static void set(String player, int amount) {
        DPKEco.config.set("data.frag-" + player, amount);
        try {
            Economy.setMoney(player,amount);
        } catch (UserDoesNotExistException | MaxMoneyException | NoLoanPermittedException e) {
            e.printStackTrace();
        }
    }
    public static int get(String player) {
        return DPKEco.config.getInt("data.frag-" + player);
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
    public static boolean check(String player, int targetAmount) {
        return (get(player) - targetAmount) >= 0;
    }


}

