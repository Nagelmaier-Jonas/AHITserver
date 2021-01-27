package at.ahit.server.main.Tobi.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClick implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().getType().equals(Material.STICK)){
            player.sendMessage("Du hast einen Stock im arsch");
            if(!player.getTargetBlock(null, 1000).getType().equals(Material.AIR)) {
                Location l = new Location(player.getWorld(), player.getTargetBlock(null, 1000).getLocation().getX(), player.getTargetBlock(null, 1000).getLocation().getY(), player.getTargetBlock(null, 1000).getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                //unnötig player.teleport(player.getTargetBlock(null, 1000).getLocation());
                player.teleport(l);
            }
        }

    }
}
