package at.ahit.server.overlays;

import at.ahit.server.jobs.Lumberjack;
import at.ahit.server.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Auction {

    public static void openAuctionMenu(Player player) {
        /*Inventory AuctionInventory = Bukkit.createInventory(null, 54, "§4Auction");

        ItemStack freeSlot = new ItemStack(Material.LIME_STAINED_GLASS_PANE,1);
        ItemMeta freeSlotMeta = freeSlot.getItemMeta();
        assert freeSlotMeta != null;
        freeSlotMeta.setDisplayName("FreeSlot");
        freeSlot.setItemMeta(freeSlotMeta);

        for (int i = 0; i < 45; ++i){
            AuctionInventory.setItem(i,freeSlot);
        }
        player.sendMessage(freeSlot.getItemMeta().getDisplayName() + "");

        ItemStack previous = new ItemStack(Material.ARROW,1);
        ItemMeta previousMeta = previous.getItemMeta();
        assert previousMeta != null;
        previousMeta.setDisplayName("Previous");
        previous.setItemMeta(previousMeta);

        ItemStack next = new ItemStack(Material.ARROW,1);
        ItemMeta nextMeta = next.getItemMeta();
        assert nextMeta != null;
        nextMeta.setDisplayName("Next");
        next.setItemMeta(nextMeta);

        AuctionInventory.setItem(45,previous);
        AuctionInventory.setItem(53,next);

        player.openInventory(AuctionInventory);*/
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

    public static void onAuctionUse(InventoryClickEvent event){
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
    }

    public static void openSellMenu(Player player){
        Inventory SellMenu = Bukkit.createInventory(player, InventoryType.ANVIL,"§4Select_Price");
        player.openInventory(SellMenu);
    }

    public static void sellItem(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        assert inventory != null;
        ItemStack itemStack = inventory.getItem(0);
        assert itemStack != null;
        itemStack.getItemMeta();
    }
}
