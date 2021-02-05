package at.ahit.server.villagerShop;

import at.ahit.server.main.Main;
import org.bukkit.*;
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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class setVillagerShop implements Listener {

    @EventHandler
    public void checkForVillager(PlayerInteractEvent event) {
        ItemMeta im = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
        if (im != null && im.getLore() != null) {
            event.getPlayer().sendMessage(im.getLore().get(0)); // TODO Remove this line
            if (im.getLore().get(0) != null)
                if (im.getLore().get(0).equals("shop")) {
                    event.setCancelled(true);

                    Location l = event.getClickedBlock().getLocation();
                    Location loc = new Location(l.getWorld(), l.getX() + 0.5, l.getY(), l.getZ() + 0.5);
                    Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
                    villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 500));

                    PersistentDataContainer container = villager.getPersistentDataContainer();
                    NamespacedKey key = new NamespacedKey(Main.getPlugin(), "shop-type");
                    container.set(key, PersistentDataType.BYTE, (byte) 1);

                    /*ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    String command = "summon villager " + loc.getX() + " " + loc.getY() + " " + loc.getZ() + " {Team:shop,VillagerData:{profession:nitwit,level:1,type:snow},Invulnerable:1,PersistenceRequired:1,Silent:1,ActiveEffects:[{Id:2,Amplifier:10,Duration:999999}]}";
                    Bukkit.dispatchCommand(console, command);*/

                    event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                }
        }
    }

    @EventHandler
    public void openVillagerEvent(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() != null)
            if (event.getRightClicked().getPersistentDataContainer() != null) {
                Entity e = event.getRightClicked();

                NamespacedKey key = new NamespacedKey(Main.getPlugin(), "shop-type");
                if (e.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                    byte type = e.getPersistentDataContainer().get(key, PersistentDataType.BYTE);
                    if (type == 1)
                        event.getPlayer().sendMessage("I opened the inventory yey.");
                }
            }
    }

}
