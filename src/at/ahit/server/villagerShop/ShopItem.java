package at.ahit.server.villagerShop;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class ShopItem {
    Entity seller;
    ItemStack itemStack;
    int sellPrice;
    int buyPrice;
    int amount; // TODO -1 = unendlich

    public ShopItem(Entity Seller, ItemStack SoldItem, int SellPrice, int BuyPrice, int Amount) {
        seller = Seller;
        itemStack = SoldItem;
        sellPrice = SellPrice;
        buyPrice = BuyPrice;
        amount = Amount;
    }
}