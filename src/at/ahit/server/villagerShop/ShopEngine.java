package at.ahit.server.villagerShop;

import at.ahit.server.main.Main;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.persistence.CraftPersistentDataContainer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShopEngine {
    private ShopEngine(String name) { shopName = name; }

    private static ShopEngine villagerShop = new ShopEngine("Server-shop:");
    public static ShopEngine getVillagerShop() { return villagerShop; }

    String shopName;
    List<ShopItem> availableItems = new ArrayList<>();

    public void openGUI(Player player, int page) {
        if (availableItems.size() <= 0)
            fillTestValues(); // TODO Remove this

        Inventory shop = Bukkit.createInventory(null, 54, shopName);

        for (int i = page * 45; i < (page + 1) * 45 && i < availableItems.size(); i++) {
            shop.setItem(i, (getShopItemStack(availableItems.get(i), i)));
        }



        player.openInventory(shop);
    }

    private void fillTestValues() {
        Material[] materials = Material.values();
        Random rng = new Random();

        for (int i = 0; i < 1000; i++) {
            int num = rng.nextInt(materials.length);
            ItemStack itemStack = new ItemStack(materials[num]);
            int buyPrice = rng.nextInt(10000);

            ShopItem item = new ShopItem(null, itemStack, (int) (buyPrice * 0.9), buyPrice, rng.nextInt(64));
            availableItems.add(item);
        }
    }

    public ItemStack getNextPageItem() {
        return null;
    }

    public ItemStack getCloseItem() {
        return null;
    }

    public ItemStack getPreviousPageItem() {
        return null;
    }

    public ItemStack getShopItemStack(ShopItem item, int index) {
        ItemMeta meta = item.itemStack.getItemMeta();
        meta.setLore(Arrays.asList("Buy-price: " + item.sellPrice, "Sell-price: " + item.sellPrice));

        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");
        container.set(key, PersistentDataType.INTEGER, index);

        ItemStack result = item.itemStack;
        result.setItemMeta(meta);

        return result;
    }

    public void onItemClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if (event.getCurrentItem() == null)
            return;

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        ItemMeta meta = event.getCurrentItem().getItemMeta();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.INTEGER)) {
            int index = container.get(key, PersistentDataType.INTEGER);

            if (availableItems.size() <= index)
                return;

            ShopItem clickedItem = availableItems.get(index);
            if (clickedItem.itemStack.getItemMeta().getDisplayName() != null)
                if (clickedItem.itemStack.getItemMeta().getDisplayName().equals(meta.getDisplayName())) { // TODO: Not actual proof the item is identical
                    // TODO Create item detail view from ShopItem
                }
        }
    }
}
