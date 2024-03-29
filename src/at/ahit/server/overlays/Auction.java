package at.ahit.server.overlays;

import at.ahit.server.enums.Color;
import at.ahit.server.jobs.Lumberjack;
import at.ahit.server.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Auction implements Listener {


    public static AuctionConfig auctionConfig = AuctionConfig.getConfig("auctionConfig");
    public static boolean isOpened = false;
    public static void openAuctionMenu(Player player) {
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(Material.ARROW, 1, "Previous"));
        items.add(SkillMenu.createItem(Material.ARROW, 1, "Next"));
        items.add(SkillMenu.createItem(Material.LIME_STAINED_GLASS_PANE, 1, "FreeSlot"));

        Inventory inventory = SkillMenu.createSkillInventory(player, "§4Auction", new HashMap<Integer, ItemStack>() {{
            for (int i = 0; i < 45; ++i){
                put(i,items.get(2));
            }
            put(45, items.get(0));
            put(53, items.get(1));
        }}, 54);
        player.openInventory(inventory);

    }

    /*public static void onAuctionUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        assert itemStack != null;
        if (itemStack.getType() != Material.AIR){
            String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
            if (name.equals("FreeSlot")){
                openSellMenu(player);
            }
        }
            event.setCancelled(true);
    }*/

    public static void openSellMenu(Player player){
        Inventory SellMenu = Bukkit.createInventory(player, InventoryType.ANVIL,"§4Select_Price");
        player.openInventory(SellMenu);
    }

    /*public static void sellItem(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        assert inventory != null;
        ItemStack itemStack = inventory.getItem(0);
        assert itemStack != null;
        itemStack.getItemMeta();
    }*/

    //@EventHandler
    public void onAuctionSellEvent(InventoryClickEvent event) {
        if(isOpened){
            openSellMenu((Player)event.getWhoClicked(), event.getCurrentItem());
            event.setCurrentItem(new ItemStack(Material.AIR));
        }
        InventoryView viewer = event.getWhoClicked().openInventory(event.getInventory());
        event.getWhoClicked().sendMessage(viewer.getTopInventory().getType().getDefaultTitle()); //size abhängig machen
    }

    public static void openSellMenu(Player player, ItemStack is){
        //Inventory SellMenu = Bukkit.createInventory(player, InventoryType.ANVIL,"§4Select_Price");

        /*Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL, "Your Title");
        InventoryView viewer = player.openInventory(inv);
        player.sendMessage(viewer.getTopInventory().getType().name());
        PrepareAnvilEvent event = new PrepareAnvilEvent(viewer, is);
        Bukkit.getServer().getPluginManager().callEvent(event);*/
        Bukkit.broadcastMessage("Ok?");
        //player.openInventory(event);
    }

    //  @EventHandler
    public void onClose(InventoryCloseEvent event) {
        isOpened = false;
    }
}
