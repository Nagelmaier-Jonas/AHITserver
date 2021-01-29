package at.ahit.server.listeners;

import net.minecraft.server.v1_16_R3.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomKillListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if(victim.equals(victim.getKiller())){
            Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + "took the L" + ChatColor.RESET);
        }

        if (victim.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
            Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + "got fucked in the ass by " + killer.getName() + killer.getItemInHand() + ChatColor.RESET);
        }

        if (victim.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LAVA) {
            Bukkit.broadcastMessage(victim.getName() + " wurde von " + killer.getName() + " in den Warmen tod gestürtzt" + ChatColor.RESET);
        }

        if (victim.getLastDamageCause().getEntityType() == EntityType.PRIMED_TNT) {
            Bukkit.broadcastMessage(victim.getName() + " wurde in Fetzen zerissen" + ChatColor.RESET);
        }

        if (victim.getName().equals("Symo_TMS")){
            Bukkit.broadcastMessage(victim.getName() + " Got Fucked up" + ChatColor.RESET);
        }


    }
}
