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
            Location locationList = event.getBlock().getLocation();
            List<Location> list = new ArrayList<Location>();
            list.add(new Location(locationList.getWorld(), locationList.getX() + 1, locationList.getY() + 1, locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX(), locationList.getY() + 1, locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX() - 1, locationList.getY() + 1, locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX() + 1, locationList.getY(), locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX() - 1, locationList.getY(), locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX() + 1, locationList.getY() -1, locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX(), locationList.getY() -1, locationList.getZ()));
            list.add(new Location(locationList.getWorld(), locationList.getX() -1, locationList.getY() - 1, locationList.getZ()));

            for (int i = 0; i < list.size(); i++) {
                list.get(i).getBlock().setType(Material.AIR);
            }//pfusch
        }
    }
}
