package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Lumberjack implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        // event.getPlayer().sendMessage("I'm alive!");

        Player player = event.getPlayer();


        int level = (int) Main.getConfigFile().get(player.getDisplayName() + "_LumberjackLevel");
        int playerXp = (int)Main.getConfigFile().get(player.getDisplayName() + "_LumberjackXp");

        Material m = event.getBlock().getType();

        if (m == Material.OAK_WOOD) // TODO
            playerXp += 100;

        /*switch (event.getBlock().getType()){
            case OAK_WOOD:
                playerXp += 5;
                break;
            case DARK_OAK_WOOD:
                playerXp += 7;
                break;
            case BIRCH_WOOD:
                playerXp += 7;
                break;
            case JUNGLE_WOOD:
                playerXp += 3;
                break;
            case SPRUCE_WOOD:
                playerXp += 5;
                break;
            case ACACIA_WOOD:
                playerXp += 7;
                break;
            case CRIMSON_STEM:
                playerXp += 10;
                break;
            case WARPED_STEM:
                playerXp += 10;
                break;
            default:
                player.sendMessage("ka Hoiz");
        }*/

        if(100 * level <= playerXp) {
            player.sendMessage("You are now lumberjack level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(player.getDisplayName() + "_LumberjackLevel", level);
            Main.getConfigFile().set(player.getDisplayName() + "_LumberjackXp", 0);
        }else{
            Main.getConfigFile().set(player.getDisplayName() + "_LumberjackXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    // public static int breakWoodRecursive()

    public static void openLumberjackMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "Lumberjack");

        ItemStack skill1 = new ItemStack(Material.STONE_AXE,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Skill1");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Skill1");
        skill1Lore.add("Costs: 1000c");
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.IRON_AXE,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Skill2");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Skill2");
        skill2Lore.add("Costs: 1000c");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.DIAMOND_AXE,1);
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

    public static void onLumberjackJobsUse(InventoryClickEvent event){
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
                    player.closeInventory();
                    Menu.openMenu(player);
                    break;
            }

            event.setCancelled(true);
        }
    }
}
