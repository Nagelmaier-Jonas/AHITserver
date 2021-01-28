package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
//3x3

public class Miner implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int level = (int) Main.getConfigFile().get(player.getDisplayName() + "_MinerLevel");
        int playerXp = (int) Main.getConfigFile().get(player.getDisplayName() + "_MinerXp");
        if (!event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            switch (event.getBlock().getType()) {
                case COAL_ORE:
                    playerXp += 5;
                    break;
                case IRON_ORE:
                    playerXp += 10;
                    break;
                case GOLD_ORE:
                    playerXp += 20;
                    break;
                case REDSTONE_ORE:
                    playerXp += 10;
                    break;
                case DIAMOND_ORE:
                    playerXp += 30;
                    break;
                case EMERALD_ORE:
                    playerXp += 100;
                    break;
                case STONE:
                    playerXp += 1;
                    break;
                case COBBLESTONE:
                    playerXp += 0.5;
                    break;
                case NETHER_QUARTZ_ORE:
                    playerXp += 10;
                    break;
                case NETHERRACK:
                    playerXp += 0.2;
                    break;
                case NETHER_GOLD_ORE:
                    playerXp += 10;
                    break;
                default:
                    player.sendMessage("ka erz");

            }
        }
        if (100 * level <= playerXp) {
            event.getPlayer().sendMessage("You are now mining level " + ChatColor.AQUA + ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_MinerLevel", level);
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_MinerXp", 0);
        } else {
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_MinerXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    @EventHandler
    public void BreakThreeByThree(BlockBreakEvent event) {
        event.getPlayer().sendMessage("" + event.getPlayer().getLocation().getPitch());
        if ((boolean) Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_MinerAbiliti")) {
            Location location = event.getBlock().getLocation();
            List<Location> locationList = new ArrayList<Location>();
            locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY() + 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY() + 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY() - 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ()));
            locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY() - 1, location.getZ()));
            Player p = event.getPlayer();
            for (Location l : locationList) {
                p.sendMessage("" + l.getBlock().getType());

                p.getInventory().addItem(new ItemStack(l.getBlock().getType()));
                l.getBlock().setType(Material.AIR);
            }
        }
    }
}
