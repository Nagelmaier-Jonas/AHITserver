package at.ahit.server.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomKillListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        if(victim.equals(victim.getKiller())){
            Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + "took the L" + ChatColor.RESET);
        }

        if (victim.getKiller() != null){
            Player killer = victim.getKiller();


        }



    }
}
