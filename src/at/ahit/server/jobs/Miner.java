package at.ahit.server.jobs;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class Miner implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        if(event.getBlock().getType().equals(Material.COAL) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH))

    }
}
