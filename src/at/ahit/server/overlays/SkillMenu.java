package at.ahit.server.overlays;

import at.ahit.server.jobs.MonsterHunter;
import at.ahit.server.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SkillMenu {

    public static Inventory createSkillInventory(Player player, String title, HashMap<Integer, ItemStack> items){
        Inventory inventory = Bukkit.createInventory(null, 9, title);

        for (Integer i:items.keySet()) {
            inventory.setItem(i,items.get(i));
        }

        player.openInventory(inventory);
        return inventory;
    }

    public static ItemStack createItem(Player player,Material material, int amount, String displayName, ArrayList<String> lore, String jobType, int skillIndex){
        ItemStack itemStack = new ItemStack(material,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);

        ArrayList<String> itemLore = new ArrayList<>(lore);
        itemLore.add(checkSkillStatus(player,jobType,skillIndex));

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItem(Player player,Material material, int amount, String displayName, ArrayList<String> lore){
        ItemStack itemStack = new ItemStack(material,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);

        ArrayList<String> itemLore = new ArrayList<>(lore);

        itemMeta.setLore(itemLore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItem(Material material, int amount, String displayName){
        ItemStack itemStack = new ItemStack(material,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    private static String checkSkillStatus(Player player,String jobType, int skillIndex){
        String itemLore;
        if(skillIndex == 0)
            return "";
        if (!(boolean) Main.Load(player.getDisplayName() + "_" + jobType + "Skill" + skillIndex)) {
            itemLore = (ChatColor.RED + "Skill not acquired");
        } else {
            itemLore = (ChatColor.GREEN + "Skill acquired");
        }
        return itemLore;
    }
}
