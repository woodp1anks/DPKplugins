package dpk.plugins.dpkrank.listener;

import dpk.plugins.dpkrank.DPKRank;
import dpk.plugins.dpkrank.method.MainRank;
import dpk.plugins.dpkrank.method.PKSRank;
import dpk.plugins.dpkrank.method.SubRank;
import dpk.plugins.dpkrank.method.WeebRank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InitRank implements Listener {
    @EventHandler
    public void e(PlayerJoinEvent e) {
        if (!DPKRank.config.contains(e.getPlayer().getName())) {
            MainRank.setRank(e.getPlayer().getName(),1);
            WeebRank.setRank(e.getPlayer().getName(),1);
            PKSRank.setRank(e.getPlayer().getName(),1);
            SubRank.setRank(e.getPlayer().getName(),1);
            MainRank.switchRank(e.getPlayer().getName(),"main");
            e.getPlayer().kickPlayer(DPKRank.config.getString("messages.first-join-kick"));
        }
    }
}
