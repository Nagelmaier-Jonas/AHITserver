package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Farmer implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int level = (int) Main.Load(player.getDisplayName() + "_FarmerLevel");
        int playerXp = (int) Main.Load(player.getDisplayName() + "_FarmerXp");

        if (event.getBlock().getState().equals(CropState.RIPE)){
            switch (event.getBlock().getType()) {
                case CARROTS:
                    playerXp += 5;
                    break;
                case WHEAT_SEEDS:
                    playerXp += 1;
                    break;
                case BEETROOT_SEEDS:
                    playerXp += 1;
                    break;
                case POTATOES:
                    playerXp += 10;
                    break;
                case COCOA_BEANS:
                    playerXp += 10;
                    break;
                case NETHER_WART:
                    playerXp += 10;
                    break;
        }

        if (event.getBlock().getType().equals(Material.MELON) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 10;
        }
        if (event.getBlock().getType().equals(Material.PUMPKIN)){
            playerXp += 10;
        }

        }
        if (100 * level <= playerXp) {
            event.getPlayer().sendMessage("You are now farming level " + ChatColor.AQUA + ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_FarmerLevel", level);
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_FarmerXp", 0);
        } else {
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_FarmerXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    public static void openFarmerMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "Farmer");

        ItemStack skill1 = new ItemStack(Material.STONE_HOE,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Skill1");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Skill1");
        skill1Lore.add("Costs: 1000c");
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.IRON_HOE,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Skill2");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Skill2");
        skill2Lore.add("Costs: 1000c");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.DIAMOND_HOE,1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        skill3Meta.setDisplayName("Skill3");
        ArrayList<String> skill3Lore = new ArrayList<String>();
        skill3Lore.add("Skill3");
        skill3Lore.add("Costs: 1000c");
        skill3Meta.setLore(skill3Lore);
        skill3.setItemMeta(skill3Meta);

        ItemStack close = new ItemStack(Material.BARRIER,1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(1,skill1);
        inventory.setItem(3,skill2);
        inventory.setItem(5,skill3);
        inventory.setItem(8,close);

        player.openInventory(inventory);
    }

    public static void onFarmerJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR){
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name){
                case "Skill1":
                    player.sendMessage("obtained skill1");
                    break;
                case "Skill2":
                    player.sendMessage("obtained skill2");
                    break;
                case "Skill3":
                    player.sendMessage("obtained skill3");
                    break;
                case "Close":
                    Menu.openMenu(player);
                    break;
            }

            event.setCancelled(true);
        }
    }
}
