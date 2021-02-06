package at.ahit.server.villagerShop;

import at.ahit.server.main.Main;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R3.persistence.CraftPersistentDataContainer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.util.*;

public class ShopEngine {
    private ShopEngine(String name) { shopName = name; }

    private static ShopEngine villagerShop = new ShopEngine("Server-shop");
    public static ShopEngine getVillagerShop() { return villagerShop; }

    String shopName;
    List<ShopItem> availableItems = new ArrayList<>();

    public void openGUI(Player player, int page) {
        NamespacedKey key = new NamespacedKey(Main.plugin, "shop-page");
        player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, page);

        if (availableItems.size() <= 0)
            fillTestValues(); // TODO Remove this

        Inventory shop = Bukkit.createInventory(null, 54, shopName + " (Page " + (page + 1) + ")");

        for (int i = page * 45; i < (page + 1) * 45 && i < availableItems.size(); i++) {
            shop.setItem(i % 45, (getShopItemStack(availableItems.get(i), i)));
        }

        if (page > 0) shop.setItem(45, getCustomItem(Material.ARROW, ChatColor.RESET + "Previous Page", null, -1));
        if ((page + 1) * 45 < availableItems.size()) shop.setItem(53, getCustomItem(Material.ARROW, ChatColor.RESET + "Next Page", null, -3));
        shop.setItem(49, getCustomItem(Material.BARRIER, ChatColor.RESET + "" + ChatColor.DARK_RED + "Close Shop", null, -2));

        for (int i = 45; i <= 53; i++)
            if (shop.getItem(i) == null)
                shop.setItem(i, getCustomItem(Material.GRAY_STAINED_GLASS_PANE, " ", null, -4));

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

    public ItemStack getCustomItem(Material material, String name, List<String> lore, int index) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (lore != null)
                meta.setLore(lore);

            if (index >= -3) {
                PersistentDataContainer container = meta.getPersistentDataContainer();
                NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");
                container.set(key, PersistentDataType.INTEGER, index);
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public ItemStack getShopItemStack(ShopItem item, int index) {
        ItemMeta meta = item.itemStack.getItemMeta();
        meta.setLore(Arrays.asList("Buy-price: " + item.buyPrice, "Sell-price: " + item.sellPrice));

        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");
        container.set(key, PersistentDataType.INTEGER, index);

        ItemStack result = item.itemStack;
        result.setItemMeta(meta);

        return result;
    }

    public void onItemClick(InventoryClickEvent event) { // TODO Auch in allen anderen Chests mit "Server-shop" im Titel sind davon betroffen
        if (!event.getView().getTitle().contains(shopName))
            return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null)
            return;

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (!(event.getWhoClicked() instanceof Player))
            return;

        Player p = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");
        NamespacedKey playerKey = new NamespacedKey(Main.getPlugin(), "shop-page");
        PersistentDataContainer container = meta.getPersistentDataContainer();
        PersistentDataContainer playerContainer = p.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.INTEGER) && playerContainer.has(playerKey, PersistentDataType.INTEGER)) {
            int index = container.get(key, PersistentDataType.INTEGER);
            int shopPage = playerContainer.get(playerKey, PersistentDataType.INTEGER);

            if (index < 0)
                switch (index) {
                    case -1:
                        if (item.getType().equals(Material.ARROW)) {
                            openGUI(p, shopPage - 1);
                            return;
                        }
                        break;
                    case -2:
                        if (item.getType().equals(Material.BARRIER)) {
                            p.closeInventory();
                            return;
                        }
                        break;
                    case -3:
                        if (item.getType().equals(Material.ARROW)) {
                            openGUI(p, shopPage + 1);
                            return;
                        }
                        break;
                }

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
