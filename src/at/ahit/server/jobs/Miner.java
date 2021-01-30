package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//3x3

public class Miner implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int level = (int) Main.Load(player.getDisplayName() + "_MinerLevel");
        int playerXp = (int) Main.Load(player.getDisplayName() + "_MinerXp");
        if (!event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            switch (event.getBlock().getType()) {
                case COAL_ORE:
                    playerXp += 5;
                    break;
                case IRON_ORE:
                case REDSTONE_ORE:
                case NETHER_QUARTZ_ORE:
                case NETHER_GOLD_ORE:
                    playerXp += 10;
                    break;
                case GOLD_ORE:
                    playerXp += 20;
                    break;
                case DIAMOND_ORE:
                    playerXp += 30;
                    break;
                case EMERALD_ORE:
                    playerXp += 100;
                    break;
                case COBBLESTONE:
                case NETHERRACK:
                case STONE:
                    playerXp += 1;
                    break;
                default:
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

    // TODO: DEPENDS ON DIRECTION, MAGIER KÖNNTE ENCHANTLVL ERHÖHEN, as telekinesis
    @EventHandler
    public void BreakThreeByThree(BlockBreakEvent event) {

        if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility3") && createArray().contains(event.getPlayer().getInventory().getItemInMainHand().getType())) {
            Location location = event.getBlock().getLocation();
            List<Location> locationList = new ArrayList<>();
            switch ((String) Main.Load(event.getPlayer().getDisplayName() + "_BlockHitDirection")) {
                case "NORTH":
                case "SOUTH":
                    locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY() + 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY() + 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY() - 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY() - 1, location.getZ()));
                    break;
                case "UP":
                case "DOWN":
                    locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ() + 1));
                    locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ() - 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1));
                    locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ() + 1));
                    locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ() - 1));
                    break;
                case "WEST":
                case "EAST":
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ() + 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ() - 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ() + 1));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ()));
                    locationList.add(new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ() - 1));
                    break;
            }

            ArrayList<Material> breakableStuff = new ArrayList<>();
            breakableStuff.add(Material.STONE);
            breakableStuff.add(Material.ANDESITE);
            breakableStuff.add(Material.BLACKSTONE);
            breakableStuff.add(Material.GILDED_BLACKSTONE);
            breakableStuff.add(Material.POLISHED_BLACKSTONE);
            breakableStuff.add(Material.CHISELED_POLISHED_BLACKSTONE);
            breakableStuff.add(Material.POLISHED_BLACKSTONE_BRICKS);
            breakableStuff.add(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
            breakableStuff.add(Material.COBBLESTONE);
            breakableStuff.add(Material.DIORITE);
            breakableStuff.add(Material.GLOWSTONE);
            breakableStuff.add(Material.GRANITE);
            breakableStuff.add(Material.ICE);
            breakableStuff.add(Material.PRISMARINE);
            breakableStuff.add(Material.IRON_ORE);
            breakableStuff.add(Material.COAL_ORE);
            breakableStuff.add(Material.GOLD_ORE);
            breakableStuff.add(Material.DIAMOND_ORE);
            breakableStuff.add(Material.ANCIENT_DEBRIS);
            breakableStuff.add(Material.GOLD_BLOCK);
            breakableStuff.add(Material.COAL_BLOCK);
            breakableStuff.add(Material.IRON_BLOCK);
            breakableStuff.add(Material.DIAMOND_BLOCK);
            breakableStuff.add(Material.NETHERITE_BLOCK);
            breakableStuff.add(Material.NETHER_GOLD_ORE);
            breakableStuff.add(Material.NETHER_QUARTZ_ORE);
            breakableStuff.add(Material.POLISHED_DIORITE);
            breakableStuff.add(Material.POLISHED_ANDESITE);
            breakableStuff.add(Material.DIRT);
            breakableStuff.add(Material.GRASS_BLOCK);
            breakableStuff.add(Material.PODZOL);
            breakableStuff.add(Material.GRAVEL);
            breakableStuff.add(Material.GLASS);
            breakableStuff.add(Material.LAPIS_ORE);
            breakableStuff.add(Material.LAPIS_BLOCK);
            breakableStuff.add(Material.SANDSTONE);
            breakableStuff.add(Material.CHISELED_SANDSTONE);
            breakableStuff.add(Material.CUT_SANDSTONE);
            breakableStuff.add(Material.SMOOTH_QUARTZ);
            breakableStuff.add(Material.SMOOTH_RED_SANDSTONE);
            breakableStuff.add(Material.SMOOTH_SANDSTONE);
            breakableStuff.add(Material.SMOOTH_STONE);
            breakableStuff.add(Material.BRICKS);
            breakableStuff.add(Material.SNOW_BLOCK);
            breakableStuff.add(Material.CLAY);
            breakableStuff.add(Material.NETHERRACK);
            breakableStuff.add(Material.SOUL_SAND);
            breakableStuff.add(Material.SOUL_SOIL);
            breakableStuff.add(Material.BASALT);
            breakableStuff.add(Material.POLISHED_BASALT);
            breakableStuff.add(Material.STONE_BRICKS);
            breakableStuff.add(Material.MOSSY_STONE_BRICKS);
            breakableStuff.add(Material.CRACKED_STONE_BRICKS);
            breakableStuff.add(Material.CHISELED_STONE_BRICKS);
            breakableStuff.add(Material.CRACKED_NETHER_BRICKS);
            breakableStuff.add(Material.CHISELED_NETHER_BRICKS);
            breakableStuff.add(Material.END_STONE);
            breakableStuff.add(Material.END_STONE_BRICKS);
            breakableStuff.add(Material.EMERALD_ORE);
            breakableStuff.add(Material.EMERALD_BLOCK);
            breakableStuff.add(Material.CHISELED_QUARTZ_BLOCK);
            breakableStuff.add(Material.QUARTZ_BLOCK);
            breakableStuff.add(Material.QUARTZ_BRICKS);
            breakableStuff.add(Material.QUARTZ_PILLAR);
            breakableStuff.add(Material.WHITE_TERRACOTTA);
            breakableStuff.add(Material.ORANGE_TERRACOTTA);
            breakableStuff.add(Material.MAGENTA_TERRACOTTA);
            breakableStuff.add(Material.LIGHT_BLUE_TERRACOTTA);
            breakableStuff.add(Material.YELLOW_TERRACOTTA);
            breakableStuff.add(Material.LIME_TERRACOTTA);
            breakableStuff.add(Material.PINK_TERRACOTTA);
            breakableStuff.add(Material.GRAY_TERRACOTTA);
            breakableStuff.add(Material.LIGHT_GRAY_TERRACOTTA);
            breakableStuff.add(Material.CYAN_TERRACOTTA);
            breakableStuff.add(Material.PURPLE_TERRACOTTA);
            breakableStuff.add(Material.BLUE_TERRACOTTA);
            breakableStuff.add(Material.BROWN_TERRACOTTA);
            breakableStuff.add(Material.GREEN_TERRACOTTA);
            breakableStuff.add(Material.RED_TERRACOTTA);
            breakableStuff.add(Material.BLACK_TERRACOTTA);
            breakableStuff.add(Material.TERRACOTTA);
            breakableStuff.add(Material.PACKED_ICE);
            breakableStuff.add(Material.PRISMARINE);
            breakableStuff.add(Material.PRISMARINE_BRICKS);
            breakableStuff.add(Material.DARK_PRISMARINE);
            breakableStuff.add(Material.RED_SANDSTONE);
            breakableStuff.add(Material.CHISELED_RED_SANDSTONE);
            breakableStuff.add(Material.CUT_RED_SANDSTONE);
            breakableStuff.add(Material.MAGMA_BLOCK);
            breakableStuff.add(Material.BONE_BLOCK);
            breakableStuff.add(Material.RED_NETHER_BRICKS);
            breakableStuff.add(Material.WHITE_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.ORANGE_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.MAGENTA_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.YELLOW_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.LIME_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.PINK_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.GRAY_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.CYAN_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.PURPLE_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.BLUE_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.BROWN_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.GREEN_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.RED_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.BLACK_GLAZED_TERRACOTTA);
            breakableStuff.add(Material.WHITE_CONCRETE);
            breakableStuff.add(Material.ORANGE_CONCRETE);
            breakableStuff.add(Material.MAGENTA_CONCRETE);
            breakableStuff.add(Material.LIGHT_BLUE_CONCRETE);
            breakableStuff.add(Material.YELLOW_CONCRETE);
            breakableStuff.add(Material.LIME_CONCRETE);
            breakableStuff.add(Material.PINK_CONCRETE);
            breakableStuff.add(Material.GRAY_CONCRETE);
            breakableStuff.add(Material.LIGHT_GRAY_CONCRETE);
            breakableStuff.add(Material.CYAN_CONCRETE);
            breakableStuff.add(Material.PURPLE_CONCRETE);
            breakableStuff.add(Material.BLUE_CONCRETE);
            breakableStuff.add(Material.BROWN_CONCRETE);
            breakableStuff.add(Material.GREEN_CONCRETE);
            breakableStuff.add(Material.RED_CONCRETE);
            breakableStuff.add(Material.BLACK_CONCRETE);
            breakableStuff.add(Material.BLACK_CONCRETE);
            breakableStuff.add(Material.REDSTONE_ORE);
            breakableStuff.add(Material.REDSTONE_BLOCK);


            if (breakableStuff.contains(event.getBlock().getType())) {
                for (Location l : locationList) {
                    ItemMeta im = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                    if (breakableStuff.contains(l.getBlock().getType())) {
                        if (im instanceof Damageable) {
                            Damageable dmg = (Damageable) im;
                            Random r = new Random();
                            switch (event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY)) {
                                case 0:
                                    dmg.setDamage(dmg.getDamage() + 1);
                                    break;
                                case 1:
                                    if (r.nextInt(100) <= 80)
                                        dmg.setDamage(dmg.getDamage() + 1);
                                    break;
                                case 2:
                                    if (r.nextInt(100) <= 60)
                                        dmg.setDamage(dmg.getDamage() + 1);
                                    break;
                                case 3:
                                    if (r.nextInt(100) <= 50)
                                        dmg.setDamage(dmg.getDamage() + 1);
                                    break;
                                case 4:
                                    if (r.nextInt(100) <= 40)
                                        dmg.setDamage(dmg.getDamage() + 1);
                                    break;
                            }
                            event.getPlayer().getInventory().getItemInMainHand().setItemMeta(im);
                        }
                        l.getBlock().breakNaturally();
                    }
                }
            }
        }
    }

    @EventHandler
    public void GetBlockLookingDirection(PlayerInteractEvent event) {
        Main.Save(event.getPlayer().getDisplayName() + "_BlockHitDirection", event.getBlockFace().toString());
    }

    // shop
    public static void openMinerMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Miner");

        ItemStack skill1 = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Autosmelt");
        ArrayList<String> skill1Lore = new ArrayList<>();
        skill1Lore.add("Ores are smelted automatically");
        skill1Lore.add("Costs: 2500 Coins");
        if (!(boolean) Main.Load(player.getDisplayName() + "_MinerSkill1")) {
            skill1Lore.add(ChatColor.RED + "Skill not acquired");
        } else {
            skill1Lore.add(ChatColor.GREEN + "Skill acquired");
        }

        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Faster...");
        ArrayList<String> skill2Lore = new ArrayList<>();
        skill2Lore.add("Blocks break faster with a Pickaxe");
        skill2Lore.add("Costs: 10000 Coins");
        if (!(boolean) Main.Load(player.getDisplayName() + "_MinerSkill2")) {
            skill2Lore.add(ChatColor.RED + "Skill not acquired");
        } else {
            skill2Lore.add(ChatColor.GREEN + "Skill acquired");
        }
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        skill3Meta.setDisplayName("BigMiner");
        ArrayList<String> skill3Lore = new ArrayList<>();
        skill3Lore.add("You can use the /mine big now!");
        skill3Lore.add("Costs: 25000 Coins");
        if (!(boolean) Main.Load(player.getDisplayName() + "_MinerSkill3")) {
            skill3Lore.add(ChatColor.RED + "Skill not acquired");
        } else {
            skill3Lore.add(ChatColor.GREEN + "Skill acquired");
        }
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
                case "Autosmelt":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill1"))) {
                        Main.Save(player.getDisplayName() + "_MinerSkill1", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 2500);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                        player.closeInventory();
                        Miner.openMinerMenu(player);
                    } else {
                        Miner.openMinerMenu(player);
                        player.sendMessage("You can't buy that you little motherfucker");
                    }
                    break;
                case "Faster...":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill2"))) {
                        Main.Save(player.getDisplayName() + "_MinerSkill2", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 1000);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                        player.closeInventory();
                        Miner.openMinerMenu(player);
                    } else {
                        Miner.openMinerMenu(player);
                        player.sendMessage("You can't buy that you little motherfucker");
                    }
                    break;
                case "BigMiner":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill3"))) {
                        Main.Save(player.getDisplayName() + "_MinerSkill3", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                        player.closeInventory();
                        Miner.openMinerMenu(player);
                    } else {
                        Miner.openMinerMenu(player);
                        player.sendMessage("You can't buy that you little motherfucker");
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

    @EventHandler
    public void autoSmeltOre(BlockBreakEvent event) {
        if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility1")) {
            switch (event.getBlock().getType()) {
                case IRON_ORE:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                case GOLD_ORE:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
                case STONE:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.STONE));
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    break;
            }
        }
    }


    public static void giveHaste(Player p) {
        if (createArray().contains(p.getInventory().getItemInMainHand().getType()) && (boolean) Main.Load(p.getDisplayName() + "_MinerSkill2")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0));
        }
    }

    public static ArrayList<Material> createArray() {
        ArrayList<Material> pickAxeList = new ArrayList<>();
        pickAxeList.add(Material.WOODEN_PICKAXE);
        pickAxeList.add(Material.STONE_PICKAXE);
        pickAxeList.add(Material.IRON_PICKAXE);
        pickAxeList.add(Material.GOLDEN_PICKAXE);
        pickAxeList.add(Material.DIAMOND_PICKAXE);
        pickAxeList.add(Material.NETHERITE_PICKAXE);
        return pickAxeList;
    }

    public static void startRunnable() {
        List<Player> pList = (List<Player>) Bukkit.getOnlinePlayers();
        Bukkit.getScheduler().runTaskTimer(Main.plugin, () -> {
            for (Player p : pList) {
                giveHaste(p);
            }
        }, 20, 20);//Time in ticks before first run and each time after that*/
    }
}
