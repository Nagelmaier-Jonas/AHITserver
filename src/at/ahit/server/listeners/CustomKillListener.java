package at.ahit.server.listeners;

import net.minecraft.server.v1_16_R3.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomKillListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = event.getEntity().getKiller();
        String killedthingname = event.getEntityType().name();


        Bukkit.broadcastMessage(ChatColor.BLUE + "VICTIM NAME: " + victim.getName() + " VICTIM ENTITY: " + event.getEntity() + ChatColor.RESET);
        Bukkit.broadcastMessage(ChatColor.BLUE + "Killedthingname: " +  killedthingname + ChatColor.RESET);
        Bukkit.broadcastMessage(ChatColor.BLUE + "Killer: " +  victim.getKiller() + ChatColor.RESET);

        if (killer != null){
            Bukkit.broadcastMessage(ChatColor.BLUE + victim.getKiller().getName() + ChatColor.RESET);
            Bukkit.broadcastMessage(ChatColor.BLUE + "KILLER NAME: " + killer.getName() + " KILLER ENTITY: " + event.getEntity().getKiller() + ChatColor.RESET);

        }

        if (victim.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.SUICIDE)
        {
            Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " took the L" + ChatColor.RESET);
        }


        switch (victim.getName()){ //SPIELERSPEZIEFISCHE
            case "Symo_TMS":
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " Got Fucked up" + ChatColor.RESET);
                break;
            default:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde von nicht idetifizierbarem Spieler vernichtet" + ChatColor.RESET);
                break;
        }

        switch (victim.getLastDamageCause().getCause()){ // CAUSE TODE
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



        switch (victim.getLastDamageCause().getEntityType()){ //ENTITYTODE
            case PRIMED_TNT:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde in Fetzen zerissen" + ChatColor.RESET);
                break;
            case ZOMBIE:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde infiziert" + ChatColor.RESET);
                break;
            case IRON_GOLEM:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " got smashed" + ChatColor.RESET);
                break;
            default:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde von nicht idetifizierbarem Entity vernichtet" + ChatColor.RESET);
                break;
        }






    }
}
