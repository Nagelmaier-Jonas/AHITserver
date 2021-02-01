package at.ahit.server.villagerShop;

import at.ahit.server.jobs.Lumberjack;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class villagerShop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player && ((Player) commandSender).getInventory().firstEmpty() != -1){
            ItemStack villager = new ItemStack(Material.STICK);
            ItemMeta im = villager.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("shop");
            im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
            im.setLore(lore);
            villager.setItemMeta(im);
            Lumberjack.RemoveEnchantmentLore(villager);
            ((Player) commandSender).getInventory().addItem(villager);
        }
        return true;
    }
}
