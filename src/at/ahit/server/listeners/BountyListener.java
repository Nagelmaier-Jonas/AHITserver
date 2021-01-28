package at.ahit.server.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BountyListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer instanceof Player){
            if (killer.equals(victim)){
                return;
            }
            return;
        }



    }
}
