package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        event.getPlayer().sendMessage("" + event.getPlayer().getLocation().getDirection());
        if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility")) {
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

    public static void openMinerMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Miner");

        ItemStack skill1 = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Faster...");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Faster mining");
        skill1Lore.add("Costs: 2500c");
        if (Main.getConfigFile().get(player.getDisplayName() + "_MinerSkill1").equals(false)) {
            skill1Lore.add(ChatColor.RED + "Item not acquired");
        } else {
            skill1Lore.add(ChatColor.GREEN + "Item acquired");
        }
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Skill2");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Skill2");
        skill2Lore.add("Costs: 1000c");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        skill3Meta.setDisplayName("Skill3");
        ArrayList<String> skill3Lore = new ArrayList<String>();
        skill3Lore.add("Skill3");
        skill3Lore.add("Costs: 1000c");
        skill3Meta.setLore(skill3Lore);
        skill3.setItemMeta(skill3Meta);

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(1, skill1);
        inventory.setItem(3, skill2);
        inventory.setItem(5, skill3);
        inventory.setItem(8, close);

        player.openInventory(inventory);
    }

    public static void onMinerJobsUse(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR) {
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name) {
                case "Faster...":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill1"))) {
                        Main.Save(player.getDisplayName() + "_MinerSkill1",true);
                        Main.Save(player.getDisplayName() + "_Amount",(int)Main.Load(player.getDisplayName() + "_Amount") - 2500);
                        Scoreboards.createScoreboard(Main.getConfigFile(),player);
                        player.closeInventory();
                        Miner.openMinerMenu(player);
                    } else {
                        Miner.openMinerMenu(player);
                        player.sendMessage("You can't buy that you little motherfucker");
                    }
                    break;
                case "Skill2":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") > 10000) {
                        Main.Save(player.getDisplayName() + "_MinerSkill2", true);
                        player.closeInventory();
                        Miner.openMinerMenu(player);
                    } else {
                        Miner.openMinerMenu(player);
                        player.sendMessage("Not enough Money");
                    }
                    break;
                case "Skill3":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") > 25000) {
                        Main.Save(player.getDisplayName() + "_MinerSkill3", true);
                        player.closeInventory();
                        Miner.openMinerMenu(player);
                    } else {
                        Miner.openMinerMenu(player);
                        player.sendMessage("Not enough Money");
                    }
                    break;
                case "Close":
                    player.closeInventory();
                    Menu.openMenu(player);
                    break;
            }

            event.setCancelled(true);
        }
    }
}
