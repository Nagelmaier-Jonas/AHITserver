package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import org.bukkit.*;
import org.bukkit.block.Block;
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
        Player player = event.getPlayer();
        Block block = event.getBlock();

        int level = (int) Main.Load(player.getDisplayName() + "_LumberjackLevel");
        int playerXp = (int) Main.Load(player.getDisplayName() + "_LumberjackXp");

        Material m = event.getBlock().getType();

        // HashMap // TODO

        switch (event.getBlock().getType()){
            case OAK_LOG:
                playerXp += 5;
                breakAdjacentBlocks(player, block, Material.OAK_LOG);
                break;
            case DARK_OAK_LOG:
                playerXp += 7;
                breakAdjacentBlocks(player, block, Material.DARK_OAK_LOG);
                break;
            case BIRCH_LOG:
                playerXp += 7;
                breakAdjacentBlocks(player, block, Material.BIRCH_LOG);
                break;
            case JUNGLE_LOG:
                playerXp += 3;
                breakAdjacentBlocks(player, block, Material.JUNGLE_LOG);
                break;
            case SPRUCE_LOG:
                playerXp += 5;
                breakAdjacentBlocks(player, block, Material.SPRUCE_LOG);
                break;
            case ACACIA_LOG:
                playerXp += 7;
                breakAdjacentBlocks(player, block, Material.ACACIA_LOG);
                break;
            case CRIMSON_STEM:
                playerXp += 10;
                breakAdjacentBlocks(player, block, Material.CRIMSON_STEM);
                break;
            case WARPED_STEM:
                playerXp += 10;
                breakAdjacentBlocks(player, block, Material.WARPED_STEM);
                break;
        }

        if(100 * level <= playerXp) {
            player.sendMessage("You are now lumberjack level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
            Main.Save(player.getDisplayName() + "_LumberjackLevel", level);
            Main.Save(player.getDisplayName() + "_LumberjackXp", 0);
        }else{
            Main.Save(player.getDisplayName() + "_LumberjackXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    public static void breakAdjacentBlocks(Player p, Block b, Material m) {
        World world = p.getWorld();

        p.sendMessage("Break aufgerufen yey");
        p.sendMessage(b.getLocation().toString());

        //p.getInventory().addItem(new ItemStack(b.getType()));
        world.dropItem(b.getLocation(), new ItemStack(b.getType()));
        b.setType(Material.AIR);

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++) {
                    if (b.getRelative(i, j, k).getType() == m)
                        breakAdjacentBlocks(p, b.getRelative(i, j, k), m);
                    }
    }

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
