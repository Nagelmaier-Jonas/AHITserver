package at.ahit.server.villagerShop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class useProtector implements Listener {
    public void protectAreaLeft(PlayerInteractEvent event) {
        event.getPlayer().sendMessage("Set");
        protectCommand.config.Save("Loc1", event.getClickedBlock().getLocation().getX() + " " + event.getClickedBlock().getLocation().getZ());
    }
    //TODO: TOBI SCHEIßT JZ AUFS EINTRAGEN
    public void protectAreaRight(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if ((event.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == "Protector")) {
                protectAreaLeft(event);
                return;
            }

        }
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.WOODEN_AXE || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() != "Protector")
            return;
            event.getPlayer().sendMessage("Set2");
            event.setCancelled(true);
            protectCommand.config.Save("Loc2", event.getClickedBlock().getLocation().getX() + " " + event.getClickedBlock().getLocation().getZ());
        }
    }

    public static List<Location> locList = new ArrayList<>();
    @EventHandler
    public void protectedArea(PlayerInteractEvent event) {

    }

    public static void initList(Location l) {
        List<Location> locs = new ArrayList<>();
        Location l1 = new Location(l.getWorld(), Integer.parseInt((String)protectCommand.config.get("Loc1x")), 0, Integer.parseInt((String)protectCommand.config.get("Loc1y")));
        Location l2 = new Location(l.getWorld(), Integer.parseInt((String)protectCommand.config.get("Loc2x")), 0, Integer.parseInt((String)protectCommand.config.get("Loc2y")));


        double lowerX = l1.getX() < l2.getX() ? l1.getX() : l2.getX();
        double lowerZ = l1.getZ() < l2.getZ() ? l1.getZ() : l2.getZ();

        double higherX = l1.getX() > l2.getX() ? l1.getX() : l2.getX();
        double higherZ = l1.getZ() > l2.getZ() ? l1.getZ() : l2.getZ();

        for (double i = lowerX; i < higherX + 1; i++)
            for (double k = lowerZ; i < higherZ + 1; k++)
                locs.add(new Location(l.getWorld(), i, 0, k));
        locList = locs;
        protectCommand.config.Save("LOCS", locs);
    }

}
