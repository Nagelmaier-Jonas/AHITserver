package at.ahit.server.usefulstuff;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ChristophsTests implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

    }

    private List<door> doors = new ArrayList<>();

    @EventHandler
    public void onInteraction(PlayerInteractEvent event) {
        if (event.hasBlock() && event.getAction() == Action.RIGHT_CLICK_BLOCK)
            if (event.getClickedBlock().getType() == Material.OAK_SIGN || event.getClickedBlock().getType() == Material.OAK_WALL_SIGN) {

                BlockState state = event.getClickedBlock().getState();
                Sign sign = (Sign) state;

                sign.getLocation();
                // TODO??
                // World world = new

                List<String> lines = Arrays.asList(sign.getLines().clone());

                /*for (String s: lines) {
                    if (s != null)
                        event.getPlayer().sendMessage(s);
                }*/

                boolean found = false;
                for (door d: doors) {
                    if (d.toggleBlock.getLocation().equals(event.getClickedBlock().getLocation())) {
                        found = true;
                        d.toggle(event.getPlayer());
                        event.getPlayer().sendMessage("DOOR TOGGLED");
                    }
                }

                if (!found) {
                    doors.add(new door(event.getPlayer(), event.getClickedBlock().getLocation(), event.getBlockFace()));
                    event.getPlayer().sendMessage("DOOR CREATED");
                }
            }
        }
    }

class door {
    public Block toggleBlock;
    private HashMap<Location, BlockState> components;
    private boolean state; // False = Geschlossen, True = Offen

    public door(Block ToggleBlock, HashMap<Location, BlockState> Components) {
        toggleBlock = ToggleBlock;
        components = Components;
        state = false;
    }

    public door(Player player, Location location, @NotNull BlockFace face)
    {
        toggleBlock = player.getWorld().getBlockAt(location);
        components = new HashMap<>();
        state = false;

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                components.put(toggleBlock.getRelative(2, 0, -1).getRelative(i, j, 0).getLocation(),
                toggleBlock.getRelative(2, 0, -1).getRelative(i, j, 0).getState());
    }

    public void toggle(Player p) {
        for (Location l: components.keySet()) {
            p.getWorld().getBlockAt(l).setType(state ? Material.AIR : components.get(l).getType());
            if (!state)
                p.getWorld().getBlockAt(l).setBlockData(components.get(l).getBlockData());
        }

        state = !state;
    }
}
