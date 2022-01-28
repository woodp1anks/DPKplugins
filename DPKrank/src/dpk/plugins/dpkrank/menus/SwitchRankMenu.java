package dpk.plugins.dpkrank.menus;

import dpk.plugins.dpkrank.method.MainRank;
import dpk.plugins.dpkrank.method.PKSRank;
import dpk.plugins.dpkrank.method.SubRank;
import dpk.plugins.dpkrank.method.WeebRank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwitchRankMenu {
    // 到时候不用一个一个改，只要改这里就好
    public static Inventory inv;
    public Player openInvPlayer;
    public static String TITLE = "§e§lSwitch Display Rank";
    public static String backTitle = "§c§lBack";
    public static String descriptionTitle = "§eDescription";
    public static String mainRankTitle = "§6§lMain rank";
    public static String subRankTitle = "§7§lSub rank";
    public static String weebRankTitle = "§d§lWeeb rank";
    public static String PKSRankTitle = "§b§lPKS rank";
    public static String currentRankStr = "§7current rank: ";
    // 使用构造方法，打开菜单前得先构造一个
    public SwitchRankMenu(Player player) {
        // 菜单
        inv = Bukkit.createInventory(player,5 * 9,TITLE);
        openInvPlayer = player;
        // 设好变量
        String mainRankRank = MainRank.getDisplayRank(openInvPlayer.getName());
        String subRankRank = SubRank.getDisplayRank(openInvPlayer.getName());
        String PKSRankRank = PKSRank.getDisplayRank(openInvPlayer.getName());
        String weebRankRank = WeebRank.getDisplayRank(openInvPlayer.getName());
        // 构造几个arraylist
        List<String> mainLore = new ArrayList<>();
        List<String> subLore = new ArrayList<>();
        List<String> PKSLore = new ArrayList<>();
        List<String> weebLore = new ArrayList<>();
        List<String> backLore = new ArrayList<>();
        List<String> descriptionLore = new ArrayList<>();
        // backLore和介绍
        descriptionLore.add("");
        descriptionLore.add("§7please choose what rank do you want to display at your prefix.");
        descriptionLore.add("§7choose a rank-type under this description");
        backLore.add("");
        backLore.add("§7click to back to main menu");
        // 自己设各种rank的lore
        mainLore.add("");
        mainLore.add(currentRankStr + mainRankRank);
        mainLore.add("");
        mainLore.add(MainRank.getItsSwitched_returnSelString(openInvPlayer.getName(),"main"));
        subLore.add("");
        subLore.add(currentRankStr + subRankRank);
        subLore.add("");
        subLore.add(MainRank.getItsSwitched_returnSelString(openInvPlayer.getName(),"sub"));
        PKSLore.add("");
        PKSLore.add(currentRankStr + PKSRankRank);
        PKSLore.add("");
        PKSLore.add(MainRank.getItsSwitched_returnSelString(openInvPlayer.getName(),"pks"));
        weebLore.add("");
        weebLore.add(currentRankStr + weebRankRank);
        weebLore.add("");
        weebLore.add(MainRank.getItsSwitched_returnSelString(openInvPlayer.getName(),"weeb"));
        // 无用的玻璃板
        ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE);
        // 回到主菜单
        ItemStack back = new ItemStack(Material.WOOD_DOOR);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(backTitle);
        backMeta.setLore(backLore);
        back.setItemMeta(backMeta);
        // 介绍部分
        ItemStack description = new ItemStack(Material.PAPER);
        description.setAmount(MainRank.getRank(openInvPlayer.getName()));
        ItemMeta descriptionMeta = description.getItemMeta();
        descriptionMeta.setDisplayName(descriptionTitle);
        descriptionMeta.setLore(descriptionLore);
        description.setItemMeta(descriptionMeta);
        // mainRank
        ItemStack mainRank = new ItemStack(Material.LEAVES);
        mainRank.setAmount(MainRank.getRank(openInvPlayer.getName()));
        ItemMeta mainRankMeta = mainRank.getItemMeta();
        mainRankMeta.setDisplayName(mainRankTitle);
        mainRankMeta.setLore(mainLore);
        mainRank.setItemMeta(mainRankMeta);
        // subRank
        ItemStack subRank = new ItemStack(Material.STONE);
        subRank.setAmount(SubRank.getRank(openInvPlayer.getName()));
        ItemMeta subRankMeta = subRank.getItemMeta();
        subRankMeta.setDisplayName(subRankTitle);
        subRankMeta.setLore(subLore);
        subRank.setItemMeta(subRankMeta);
        // weebRank
        ItemStack weebRank = new ItemStack(Material.WEB);
        weebRank.setAmount(WeebRank.getRank(openInvPlayer.getName()));
        ItemMeta weebRankMeta = weebRank.getItemMeta();
        weebRankMeta.setDisplayName(weebRankTitle);
        weebRankMeta.setLore(weebLore);
        weebRank.setItemMeta(weebRankMeta);
        // PKSRank
        ItemStack PKSRank = new ItemStack(Material.NETHER_STAR);
        PKSRank.setAmount(dpk.plugins.dpkrank.method.PKSRank.getRank(openInvPlayer.getName()));
        ItemMeta PKSRankMeta = PKSRank.getItemMeta();
        PKSRankMeta.setDisplayName(PKSRankTitle);
        PKSRankMeta.setLore(PKSLore);
        PKSRank.setItemMeta(PKSRankMeta);
        // 绘制菜单
        inv.setItem(0,glassPane);
        inv.setItem(1,glassPane);
        inv.setItem(2,glassPane);
        inv.setItem(3,glassPane);
        inv.setItem(4,glassPane);
        inv.setItem(5,glassPane);
        inv.setItem(6,glassPane);
        inv.setItem(7,glassPane);
        inv.setItem(8,glassPane);
        inv.setItem(36,glassPane);
        inv.setItem(37,glassPane);
        inv.setItem(38,glassPane);
        inv.setItem(39,glassPane);
        inv.setItem(40,glassPane);
        inv.setItem(41,glassPane);
        inv.setItem(42,glassPane);
        inv.setItem(43,glassPane);
        inv.setItem(44,glassPane);
        inv.setItem(12,back);
        inv.setItem(14,description);
        inv.setItem(31,mainRank);
        inv.setItem(30,subRank);
        inv.setItem(32,PKSRank);
        inv.setItem(33,weebRank);
    }
    // 创建一个用来在构造之后打开菜单的方法
    public void open() {
        // 如果不构造，openInvPlayer就是null
        openInvPlayer.openInventory(inv);
    }
}
