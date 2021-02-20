package at.ahit.server.villagerShop;

import at.ahit.server.enums.EShopMenuType;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class ShopHolder implements InventoryHolder {
    @NotNull
    private String _shopID;

    @NotNull
    private EShopMenuType _type;

    @NotNull
    @Override
    public Inventory getInventory() {
        return null;
    }

    public ShopHolder(String shopID, EShopMenuType type) {
        if (shopID == null || type == null)
            throw new IllegalArgumentException("Arguments may not be null.");

        _type = type;
        _shopID = shopID;
    }

    public EShopMenuType getMenuType() {
        return _type;
    }

    public String getShopID() {
        return _shopID;
    }

    public boolean equals(Object o) {
        if (o instanceof ShopHolder) {
            ShopHolder holder = ((ShopHolder) o);
            return ((ShopHolder) o)._shopID.equals(this._shopID) && ((ShopHolder) o)._type.equals(this._type);
        }

        return false;
    }
}
