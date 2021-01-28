package at.ahit.server.overlays;

import at.ahit.server.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Auction {

    public static void openAuctionMenu(Player player) {
        Inventory AuctionInventory = Bukkit.createInventory(null, 54, "§4Auction");
        Inventory PlayerInventory = player.getInventory();

        ItemStack freeSlot = new ItemStack(Material.LIME_STAINED_GLASS_PANE,1);
        ItemMeta freeSlotMeta = freeSlot.getItemMeta();
        freeSlotMeta.setDisplayName("FreeSlot");
        freeSlot.setItemMeta(freeSlotMeta);

        for (int i = 0; i < 45; ++i){
            AuctionInventory.setItem(i,freeSlot);
        }
        player.sendMessage(freeSlot.getItemMeta().getDisplayName() + "");

        ItemStack previous = new ItemStack(Material.ARROW,1);
        ItemMeta previousMeta = previous.getItemMeta();
        previousMeta.setDisplayName("Previous");
        previous.setItemMeta(previousMeta);

        ItemStack next = new ItemStack(Material.ARROW,1);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("Next");
        next.setItemMeta(nextMeta);

        AuctionInventory.setItem(45,previous);
        AuctionInventory.setItem(53,next);

        player.openInventory(AuctionInventory);
    }

    public static void onAuctionUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR){
            String name = itemStack.getItemMeta().getDisplayName();
            switch (name){
                case "Next":
                    break;
                case "Previous":
                    break;
                case "FreeSlot":
                    openSellMenu(player);
                    break;
            }
        }
            event.setCancelled(true);
    }

    public static void openSellMenu(Player player){
        Inventory SellMenu = Bukkit.createInventory(player, InventoryType.ANVIL,"§4Select_Price");
        player.openInventory(SellMenu);
    }

    public static void sellItem(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack[] = event.getInventory().getContents();
        player.sendMessage(itemStack[0] + "");
        player.sendMessage(itemStack[1] + "");
        player.sendMessage(itemStack[2] + "");
    }
}
