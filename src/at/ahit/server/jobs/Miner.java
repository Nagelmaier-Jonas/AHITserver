package at.ahit.server.jobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
//3x3

public class Miner implements Listener {
    private int level = 1;
    private double playerXp = 0;
    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
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
        if(100 * level >= playerXp) {
            event.getPlayer().sendMessage("You are now mining level " + ChatColor.AQUA +  level + ChatColor.RESET + "!");
            level++;
            playerXp = 0;
        }
    }
}
