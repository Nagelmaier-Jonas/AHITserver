package at.ahit.server.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class CustomKillListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        switch (victim.getName()){ //SPIELERSPEZIEFISCHE
            case "Symo_TMS":
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " Got Fucked up" + ChatColor.RESET);
                break;
            case "Joni04":
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " Got Fucked up" + ChatColor.RESET);
                break;
            default:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " hat noch keinen Spezifischen Tod ausgewählt" + ChatColor.RESET);
                break;
        }


        switch (Objects.requireNonNull(victim.getLastDamageCause()).getCause()){ // CAUSE TODE
            case ENTITY_ATTACK:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " got fucked in the ass by " + victim.getLastDamageCause().getEntity().getName() + ChatColor.RESET);
                break;
            case LAVA:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " ist zerwchmolzen" + ChatColor.RESET);
                break;
            case ENTITY_EXPLOSION:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde in Fetzen zerissen" + ChatColor.RESET);
                break;
            case BLOCK_EXPLOSION:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde von einem Block in Fetzen zerissen" + ChatColor.RESET);
                break;
            case ENTITY_SWEEP_ATTACK:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " ist Colateralschaden" + ChatColor.RESET);
                break;
            case FIRE:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " ist jetzt Holzkohle" + ChatColor.RESET);
                break;
            default:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde von nicht idetifizierbarem Cause vernichtet" + ChatColor.RESET);
                break;
        }


    }
}
