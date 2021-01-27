package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;
//3x3

public class Miner implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {

        int level = (int)Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_MinerLevel");
        int playerXp = (int)Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_MinerXp");
        if(event.getBlock().getType().equals(Material.COAL_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 5;
        }
        if(event.getBlock().getType().equals(Material.IRON_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 10;
        }
        if(event.getBlock().getType().equals(Material.GOLD_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 20;
        }
        if(event.getBlock().getType().equals(Material.REDSTONE_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 10;
        }
        if(event.getBlock().getType().equals(Material.DIAMOND_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 30;
        }
        if(event.getBlock().getType().equals(Material.EMERALD_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 100;
        }
        if(event.getBlock().getType().equals(Material.STONE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 1;
        }
        if(event.getBlock().getType().equals(Material.COBBLESTONE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 0.5;
        }
        if(event.getBlock().getType().equals(Material.NETHER_QUARTZ_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 10;
        }
        if(event.getBlock().getType().equals(Material.NETHERRACK) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 0.2;
        }
        if(event.getBlock().getType().equals(Material.NETHER_GOLD_ORE) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 10;
        }
        if(100 * level <= playerXp) {
            event.getPlayer().sendMessage("You are now mining level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_MinerLevel", level);
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_MinerXp", 0);
        }else{
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_MinerXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }
    @EventHandler
    public void BreakThreeByThree(BlockBreakEvent event) {
        event.getPlayer().sendMessage(""+Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_MinerAbiliti"));
        if((boolean) Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_MinerAbiliti") == true){
            Location location = event.getBlock().getLocation();
            List<Location> locationList = new ArrayList<Location>();
            locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY() + 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY() + 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY() -1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX(), location.getY() -1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() -1, location.getY() - 1, location.getZ()));
            for (Location l: locationList) {
                l.getBlock().setType(Material.AIR);
            }
        }
    }
}
