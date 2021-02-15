package at.ahit.server.villagerShop;

import at.ahit.server.jobs.Lumberjack;
import at.ahit.server.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.print.attribute.standard.MediaSize;
import javax.xml.stream.events.Namespace;
import java.util.ArrayList;
import java.util.List;

public class villagerShop implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player && ((Player) commandSender).getInventory().firstEmpty() != -1){
            int ID = 0;

            try {
                ID = Integer.parseInt(strings[0]);
            }
            catch (Exception e) {
                ID = 0;
            }

            ((Player) commandSender).getInventory().addItem(villagerStick(ID));
        }
        return true;
    }

    public static boolean isVillagerStick(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null)
            return false;

        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "villager-shop-id");
        return (meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER));

        /*return (itemStack.getType() == stick.getType()) && (stickMeta.getDisplayName().equals(meta.getDisplayName())) &&
                (meta.hasLore() ? meta.getLore().equals(stickMeta.getLore()) : false) &&
                (meta.hasEnchants() ? meta.getEnchants().equals(stickMeta.hasEnchants()): false) &&;*/
    }

    public static ItemStack villagerStick(int ID) {
        ItemStack villager = new ItemStack(Material.STICK);
        ItemMeta im = villager.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Spawn villager-shop");
        lore.add("ID: " + ID);
        assert im != null;
        im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "villager-shop-id");
        im.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, ID);
        im.setLore(lore);
        villager.setItemMeta(im);
        Lumberjack.RemoveEnchantmentLore(villager);

        return villager;
    }

}
