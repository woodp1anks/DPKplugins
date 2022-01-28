package dpk.plugins.dpkrank.listener;

import dpk.plugins.dpkrank.menus.SwitchRankMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {
    @EventHandler
    public void e(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryView inv = player.getOpenInventory();
        if (inv.getTitle().equals(SwitchRankMenu.TITLE)) {
            e.setCancelled(true);
            // 检测点击位置是否在菜单内
            if (e.getRawSlot() < 0 || e.getRawSlot() > e.getInventory().getSize()) {
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            // 是否啥都没点
            if (clickedItem == null) {
                return;
            }
            // 返回
            if (clickedItem.getItemMeta().getDisplayName().equals(SwitchRankMenu.backTitle)) {
                player.performCommand("menu1");
                return;
            }
            // mainRank
            if (clickedItem.getItemMeta().getDisplayName().equals(SwitchRankMenu.mainRankTitle)) {
                player.performCommand("switch main");
                player.closeInventory();
                new SwitchRankMenu(player).open();
                return;
            }
            // subRank
            if (clickedItem.getItemMeta().getDisplayName().equals(SwitchRankMenu.subRankTitle)) {
                player.performCommand("switch sub");
                player.closeInventory();
                new SwitchRankMenu(player).open();
                return;
            }
            // weebRank
            if (clickedItem.getItemMeta().getDisplayName().equals(SwitchRankMenu.weebRankTitle)) {
                player.performCommand("switch weeb");
                player.closeInventory();
                new SwitchRankMenu(player).open();
                return;
            }
            // PKSRank
            if (clickedItem.getItemMeta().getDisplayName().equals(SwitchRankMenu.PKSRankTitle)) {
                player.performCommand("switch pks");
                player.closeInventory();
                new SwitchRankMenu(player).open();
                return;
            }
        }
    }
}
