package at.ahit.server.villagerShop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class setVillagerShop implements Listener {

    @EventHandler
    public void checkForVillager(PlayerInteractEvent event) {
        /*ItemMeta im = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
        if (im != null) {
            event.getPlayer().sendMessage(im.getLore().get(0));
            if (im.getLore().get(0) != null)
                if (im.getLore().get(0).equals("shop")) {
                    event.setCancelled(true);

                    Location l = event.getClickedBlock().getLocation();
                    Location loc = new Location(l.getWorld(), l.getX() + 0.5, l.getY(), l.getZ() + 0.5);

                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    String command = "summon villager " + loc.getX() + " " + loc.getY() + " " + loc.getZ() + " {Team:shop,VillagerData:{profession:nitwit,level:1,type:snow},Invulnerable:1,PersistenceRequired:1,Silent:1,ActiveEffects:[{Id:2,Amplifier:10,Duration:999999}]}";
                    Bukkit.dispatchCommand(console, command);

                    event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                }
        }*/
    }

    public void openVillagerEvent(PlayerInteractAtEntityEvent event) {

    }

}
