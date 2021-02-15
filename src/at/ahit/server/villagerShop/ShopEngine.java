package at.ahit.server.villagerShop;

import at.ahit.server.enums.EShopMenuType;
import at.ahit.server.enums.ETransactionType;
import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.xml.stream.events.Namespace;
import java.util.*;

public class ShopEngine {
    public ShopEngine(String ID, String name) { id = ID; shopName = name; }

    private static HashMap<String, ShopEngine> shops = new HashMap<>();

    public static void addShop(String ID, String name) {
        if (ID != null && name != null)
            if (!ID.equals("") && !shops.containsKey(ID)) {
                shops.put(ID, new ShopEngine(ID, name));
                return;
            }

            throw new IllegalArgumentException("Name was null, empty or already existed.");
    }
    public static void removeShop(String ID) {
        if (shops.containsKey(ID))
            shops.remove(ID);
    }
    public static ShopEngine getShop(String ID) {
        return shops.get(ID);
    }

    public static void clickHandler(InventoryClickEvent event) {
        /*switch (ShopEngine.getVillagerShop().isShopInventory(event)) {
            case MAIN_MENU:
                ShopEngine.getVillagerShop().shopMenuClick(event);
                break;
            case SELL_MENU:
                ShopEngine.getVillagerShop().transactionMenuClick(event, ETransactionType.SELL);
                break;
            case BUY_MENU:
                ShopEngine.getVillagerShop().transactionMenuClick(event, ETransactionType.BUY);
                break;
        }*/

        Player player = (Player) event.getWhoClicked();

        NamespacedKey key = new NamespacedKey(Main.plugin, "shop-id");
        String shopID = player.getPersistentDataContainer().get(key, PersistentDataType.STRING);

        if (!shops.containsKey(shopID))
            return;

        EShopMenuType type = shops.get(shopID).isShopInventory(event);

        if (type != EShopMenuType.NONE)
            switch (type) {
                case MAIN_MENU:
                    shops.get(shopID).shopMenuClick(event);
                    break;
                case SELL_MENU:
                    shops.get(shopID).transactionMenuClick(event, ETransactionType.SELL);
                    break;
                case BUY_MENU:
                    shops.get(shopID).transactionMenuClick(event, ETransactionType.BUY);
                    break;
            }
    }

    private String id;
    public String shopName;
    List<ShopItem> availableItems = new ArrayList<>();

    // GUIs:

    public void openShopGUI(Player player, int page) {
        NamespacedKey key = new NamespacedKey(Main.plugin, "shop-page");
        player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, page);

        key = new NamespacedKey(Main.plugin, "shop-id");
        player.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);

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

    public void openItemBuyGUI(Player player, ShopItem item) {
        Inventory inv = Bukkit.createInventory(null, 27, "Buying for: $" + item.buyPrice);// \"" + (item.itemStack.getItemMeta().getDisplayName().equals("") ? item.itemStack.getItemMeta().getLocalizedName() : item.itemStack.getItemMeta().getDisplayName()) + "\"");

        for (int i = 9; i < 12; i++) {
            inv.setItem(i, getCustomItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.RESET + "" + ChatColor.GREEN + "Buy", null, 0));
            inv.setItem(26 - i, getCustomItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RESET + "" + ChatColor.RED + "Cancel", null, 1));
        }

        inv.setItem(13, item.itemStack);

        for (int i = 0; i < 27; i++)
            if (inv.getItem(i) == null)
                inv.setItem(i, getCustomItem(Material.GRAY_STAINED_GLASS_PANE, "", null, 2));

        player.openInventory(inv);
    }

    public void openItemSellGUI(Player player, ShopItem item) {
        Inventory inv = Bukkit.createInventory(null, 27, "Selling for: $" + item.sellPrice);// \"" + (item.itemStack.getItemMeta().getDisplayName().equals("") ? item.itemStack.getItemMeta().getLocalizedName() : item.itemStack.getItemMeta().getDisplayName()) + "\"");

        for (int i = 9; i < 12; i++) {
            inv.setItem(i, getCustomItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.RESET + "" + ChatColor.GREEN + "Sell", null, 0));
            inv.setItem(26 - i, getCustomItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RESET + "" + ChatColor.RED + "Cancel", null, 1));
        }

        inv.setItem(13, item.itemStack);

        for (int i = 0; i < 27; i++)
            if (inv.getItem(i) == null)
                inv.setItem(i, getCustomItem(Material.GRAY_STAINED_GLASS_PANE, "", null, 2));

        player.openInventory(inv);
    }

    // Other stuff:

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
        ItemStack clonedItem = item.itemStack.clone();

        ItemMeta meta = clonedItem.getItemMeta();
        if (meta != null) {
            meta.setLore(Arrays.asList("Buy-price: " + item.buyPrice, "Sell-price: " + item.sellPrice));
            PersistentDataContainer container = meta.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");
            container.set(key, PersistentDataType.INTEGER, index);

            clonedItem.setItemMeta(meta);
        }

        return clonedItem;
    }

    public EShopMenuType isShopInventory(InventoryClickEvent event) {
        if (event.getInventory().getHolder() != null)
            return EShopMenuType.NONE;

        String title = event.getView().getTitle();

        if (title.contains(shopName))
            return EShopMenuType.MAIN_MENU;
        else if (title.contains("Buying for:"))
            return EShopMenuType.BUY_MENU;
        else if (title.contains("Selling for:"))
            return EShopMenuType.SELL_MENU;

        return EShopMenuType.NONE;
    }

    public void transactionMenuClick(InventoryClickEvent event, ETransactionType type) {
        event.setCancelled(true);

        if (event.getCurrentItem() == null)
            return;

        if (event.getCurrentItem().getItemMeta() == null)
            return;

        if (!(event.getWhoClicked() instanceof Player))
            return;

        Player p = (Player) event.getWhoClicked();

        PersistentDataContainer container = event.getCurrentItem().getItemMeta().getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "item-index");

        if (!container.has(key, PersistentDataType.INTEGER))
            return;

        int index = container.get(key, PersistentDataType.INTEGER);

        switch (index) {
            case 0:
                key = new NamespacedKey(Main.getPlugin(), "clicked-item-price");
                if (p.getPersistentDataContainer().has(key, PersistentDataType.INTEGER))
                    if (Main.Load(p.getDisplayName() + "_Amount") != null) {
                        int playerMoney = (int) Main.Load(p.getDisplayName() + "_Amount");
                        int price = p.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);

                        ItemStack item = event.getInventory().getItem(13).clone();
                        int multiplier = (event.isShiftClick() ?
                                (item.getAmount() == 1 ?
                                        item.getMaxStackSize() :
                                        1) :
                                1);

                        if (type == ETransactionType.BUY) {
                            item.setAmount(item.getAmount() * multiplier);

                            if (playerMoney >= (price * multiplier)) {
                                Main.Save(p.getDisplayName() + "_Amount", playerMoney - (price * multiplier));
                                p.getWorld().dropItem(p.getLocation(), item);

                                Scoreboards.createScoreboard(Main.getConfigFile(), p);
                            }
                        } else if (type == ETransactionType.SELL) {
                            while ((p.getInventory().containsAtLeast(item, item.getAmount()))) {
                                p.getInventory().removeItem(item);
                                playerMoney += price;

                                if (!event.isShiftClick())
                                    break;
                            }

                            Main.Save(p.getDisplayName() + "_Amount", playerMoney);
                            Scoreboards.createScoreboard(Main.getConfigFile(), p);
                        }
                    }
                break;
            case 1:
                key = new NamespacedKey(Main.getPlugin(), "shop-page");
                if (p.getPersistentDataContainer().has(key, PersistentDataType.INTEGER))
                    openShopGUI(p, p.getPersistentDataContainer().get(key, PersistentDataType.INTEGER));
                break;
        }
    }

    public void shopMenuClick(InventoryClickEvent event) { // Jetzt nicht mehr // T_ODO Auch in allen anderen Chests mit "Server-shop" im Titel sind davon betroffen
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
                            openShopGUI(p, shopPage - 1);
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
                            openShopGUI(p, shopPage + 1);
                            return;
                        }
                        break;
                }

            if (availableItems.size() <= index)
                return;

            ShopItem clickedItem = availableItems.get(index);
            if (clickedItem.itemStack.getItemMeta().getDisplayName() != null)
                if (clickedItem.itemStack.getItemMeta().getDisplayName().equals(meta.getDisplayName())) { // TODO: Not actual proof the item is identical
                    key = new NamespacedKey(Main.getPlugin(), "clicked-item-price");

                    if (event.getClick() == ClickType.LEFT) {
                        openItemBuyGUI(p, clickedItem);
                        p.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, clickedItem.buyPrice);
                    }
                    else if (event.getClick() == ClickType.RIGHT) {
                        openItemSellGUI(p, clickedItem);
                        p.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, clickedItem.sellPrice);
                    }
                }
        }
    }
}
