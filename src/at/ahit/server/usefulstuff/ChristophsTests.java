package at.ahit.server.usefulstuff;

import at.ahit.server.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ChristophsTests implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

    }

    private List<door> doors = new ArrayList<>();

    /*
    @EventHandler
    public void dropHandler(PlayerDropItemEvent event) {
        Player p = event.getPlayer();

        Item item = event.getItemDrop();

        ItemMeta meta = item.getItemStack().getItemMeta();
        String name = "." + meta.getDisplayName() + ".";

        p.sendMessage(name);

        if (name == "Tunnelbohrer") { // WARUM GEHT DAS NICHT AAAAAAAAAAAA
            p.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.AIR);
            event.setCancelled(true);
        }
        else if (item.getItemStack().getType() == Material.DIAMOND)
            item.setItemStack(new ItemStack(Material.EMERALD));
        else if (item.getItemStack().getType() == Material.EMERALD)
            item.setItemStack(new ItemStack(Material.DIAMOND));
    } */

    @EventHandler
    public void twerk(PlayerToggleSneakEvent event) {

        if (event.isSneaking()) {

            Player p = event.getPlayer();
            Location l = p.getLocation();

            List<Block> surroundings = new ArrayList<>();

            List<Material> valid = Arrays.asList(Material.OAK_SAPLING, Material.DARK_OAK_SAPLING, Material.BIRCH_SAPLING, Material.JUNGLE_SAPLING, Material.SPRUCE_SAPLING, Material.ACACIA_SAPLING, Material.WARPED_FUNGUS, Material.CRIMSON_FUNGUS);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++)
                    if (valid.contains(l.getBlock().getRelative(i, 0, j).getType())) {
                        surroundings.add(l.getBlock().getRelative(i, 0, j));
                    }
            }

            Random random = new Random();
            if (surroundings.size() > 0) {
                Block b = surroundings.get(random.nextInt(surroundings.size()));

                if (random.nextInt(100) + 1 <= 20) {
                    b.applyBoneMeal(BlockFace.UP);
                }
            }
        }
    }

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
