package at.ahit.server.villagerShop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class setVillagerShop implements Listener {

    @EventHandler
    public void checkForVillager(PlayerInteractEvent event) {
        List<String> ar = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore();
        if(ar.get(0).equals("shop")){
            event.setCancelled(true);

            Location l = event.getClickedBlock().getLocation();
            Location loc = new Location(l.getWorld(), l.getX()+0.5, l.getY(), l.getZ()+0.5);

            event.getClickedBlock().getLocation().getWorld().spawnEntity(loc, EntityType.VILLAGER);
            event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        }
    }

}
