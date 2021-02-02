package at.ahit.server.recepies;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wand {
    public static void addToServer() {
        ItemStack wand = new ItemStack(Material.STICK);
        ItemMeta meta = wand.getItemMeta();

        List<String> lines = Arrays.asList(new String[] { "Right-click to open menu", "Left-click to cast spells"});

        meta.setLore(lines);
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.MAGIC + "." + ChatColor.RESET + ChatColor.GOLD + " Wand " + ChatColor.RESET + ChatColor.MAGIC + ".");

        wand.setItemMeta(meta);

        ShapedRecipe wandRecipe = new ShapedRecipe(wand);
        wandRecipe.shape("d", "s");

        wandRecipe.setIngredient('d', Material.DIAMOND);
        wandRecipe.setIngredient('s', Material.STICK);

        Bukkit.getServer().addRecipe(wandRecipe);
    }
}
