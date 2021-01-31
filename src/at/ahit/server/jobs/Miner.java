package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.MyCustomConfig;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.util.*;
//DONE
//TODO: lvl shop, GOLD / IRON , SHOVEL SAND, GRAVEL, DIRT, GRASS, CONCRETEPOWDER, MAGIER BLITZ, OBTAINED MESSAGE
public class Miner implements Listener {

    public void checkBlockXp(Player player, Block b) {
        int level = (int) Main.Load(player.getDisplayName() + "_MinerLevel");
        int playerXp = (int) Main.Load(player.getDisplayName() + "_MinerXp");
        ArrayList<String> LocationList = (ArrayList<String>) config1.get("" + b.getLocation().getWorld().getName());
        if (!LocationList.contains((int) b.getLocation().getX() + " " + (int) b.getLocation().getY() + " " + (int) b.getLocation().getZ())) {
            if (!player.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                switch (b.getType()) {
                    case COAL_ORE:
                        playerXp += 5;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Miner");
                        break;
                    case IRON_ORE:
                    case REDSTONE_ORE:
                    case NETHER_QUARTZ_ORE:
                    case NETHER_GOLD_ORE:
                    case LAPIS_ORE:
                        playerXp += 10;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Miner");
                        break;
                    case GOLD_ORE:
                        playerXp += 20;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Miner");
                        break;
                    case DIAMOND_ORE:
                        playerXp += 30;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Miner");
                        break;
                    case EMERALD_ORE:
                        playerXp += 100;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Miner");
                        break;
                    case COBBLESTONE:
                    case NETHERRACK:
                    case STONE:
                        playerXp += 1;
                        Main.Save(player.getPlayer().getDisplayName() + "_LatestJob", "Miner");
                        break;
                    default:
                        break;
                }
            }
        }
        else
        {
            LocationList.remove((int) b.getLocation().getX() + " " + (int) b.getLocation().getY() + " " + (int) b.getLocation().getZ());
            config1.Save(b.getLocation().getWorld().getName(), LocationList);
        }
        if (500 * level <= playerXp) { //TODO: EXPONENTIELL
            Objects.requireNonNull(player.getPlayer()).sendMessage("You are now mining level " + ChatColor.AQUA + ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(player.getPlayer().getDisplayName() + "_MinerLevel", level);
            Main.getConfigFile().set(player.getPlayer().getDisplayName() + "_MinerXp", 0);
        } else {
            Main.getConfigFile().set(player.getPlayer().getDisplayName() + "_MinerXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    // TODO: MAGIER KÖNNTE ENCHANTLVL ERHÖHEN, as telekinesis, schlfasack, veinminer, pro lvl verschiedene sachen bei 3x3

    public void BreakThreeByThree(BlockBreakEvent event) {
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
        //ADDITEMS
        {
            breakableStuff.add(Material.STONE);
            breakableStuff.add(Material.AIR);
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
        }
        if (breakableStuff.contains(event.getBlock().getType())) {
            for (Location l : locationList) {
                if (breakableStuff.contains(l.getBlock().getType())) {
                    checkBlockXp(event.getPlayer(), event.getBlock());
                    //DAMAGE ITEM
                    UpdateMainHand(event.getPlayer(), 1);
                    //AUTOSMELT OR NOT
                    if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility1")) {
                        autoSmeltOre(event.getPlayer(), l.getBlock());
                    } else if(event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && l.getBlock().getType() != Material.AIR) {
                        l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(l.getBlock().getType()));
                        l.getBlock().setType(Material.AIR);
                    }
                    else if(event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && l.getBlock().getType() != Material.AIR && luckMaterial.contains(l.getBlock().getType())) {
                        Random r = new Random();
                        int i;
                        switch (event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)){
                            case 1:
                                if(r.nextInt(100) <= 33)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 2));
                                else
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l)));
                                break;
                            case 2:
                                i = r.nextInt(100);
                                if(i <= 25)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 2));
                                else if(i <= 50)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 3));
                                else
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l)));
                                break;
                            case 3:
                                i = r.nextInt(100);
                                if(i <= 20)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 2));
                                else if(i <= 40)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 3));
                                else if(i <= 60)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 4));
                                else
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l)));
                                break;
                            case 4:
                                i = r.nextInt(100);
                                if(i <= 25)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 2));
                                else if(i <= 50)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 3));
                                else if(i <= 75)
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l), 4));
                                else
                                    l.getBlock().getWorld().dropItemNaturally(l, new ItemStack(convertBreak(l)));

                                break;
                        }
                        l.getBlock().setType(Material.AIR);
                    }
                    else
                        l.getBlock().breakNaturally();
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
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player, Material.STONE_PICKAXE, 1, "Autosmelt", new ArrayList<>(Arrays.asList("Ores are smelted automatically", "Costs: 2500 Coins")), "Miner", 1));


        items.add(SkillMenu.createItem(player, Material.IRON_PICKAXE, 1, "Faster...", new ArrayList<>(Arrays.asList("Blocks break faster with a Pickaxe", "Costs: 10000 Coins")), "Miner", 2));
        items.add(SkillMenu.createItem(player, Material.DIAMOND_PICKAXE, 1, "BigMiner", new ArrayList<>(Arrays.asList("You can use the /mine big now!", "Costs: 25000 Coins")), "Miner", 3));
        items.add(SkillMenu.createItem(Material.BARRIER, 1, "Close"));
        //Enchants
        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility1") && (boolean) Main.Load(player.getDisplayName() + "_MinerSkill1")) {
            items.get(0).addEnchantment(Enchantment.DURABILITY, 1);
            Lumberjack.RemoveEnchantmentLore(items.get(0));
        }
        else
            items.get(0).removeEnchantment(Enchantment.DURABILITY);
        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility2") && (boolean) Main.Load(player.getDisplayName() + "_MinerSkill2")) {
            items.get(1).addEnchantment(Enchantment.DURABILITY, 1);
            Lumberjack.RemoveEnchantmentLore(items.get(1));
        }
        else
            items.get(1).removeEnchantment(Enchantment.DURABILITY);
        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility3") && (boolean) Main.Load(player.getDisplayName() + "_MinerSkill3")) {
            items.get(2).addEnchantment(Enchantment.DURABILITY, 1);
            Lumberjack.RemoveEnchantmentLore(items.get(2));
        }
        else
            items.get(2).removeEnchantment(Enchantment.DURABILITY);
        Inventory inventory = SkillMenu.createSkillInventory(player, "Miner", new HashMap<Integer, ItemStack>() {{
            put(1, items.get(0));
            put(3, items.get(1));
            put(5, items.get(2));
            put(8, items.get(3));
        }});
        player.openInventory(inventory);
    }

    public static void onMinerJobsUse(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR) {
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name) {
                case "Autosmelt":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill1")) && 3 <= (Integer) Main.Load(player.getDisplayName() + "_MinerLevel")) {
                        Main.Save(player.getDisplayName() + "_MinerSkill1", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 2500);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                    } else if (!(boolean) Main.Load(player.getDisplayName() + "_MinerSkill1")) {
                        player.sendMessage("You need " + ChatColor.GOLD + "2500 Coins" + ChatColor.RESET + " and " + ChatColor.AQUA + "Miner Level 3" + ChatColor.RESET);
                    }
                    if ((boolean) Main.Load(player.getDisplayName() + "_MinerSkill1")) {
                        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility1"))
                            Main.Save(player.getDisplayName() + "_MinerAbility1", false);
                        else
                            Main.Save(player.getDisplayName() + "_MinerAbility1", true);
                    }
                    break;
                case "Faster...":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill2")) && 5 <= (Integer) Main.Load(player.getDisplayName() + "_MinerLevel")) {
                        Main.Save(player.getDisplayName() + "_MinerSkill2", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 1000);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                    } else if (!(boolean) Main.Load(player.getDisplayName() + "_MinerSkill2")) {
                        player.sendMessage("You need " + ChatColor.GOLD + "10000 Coins" + ChatColor.RESET + " and " + ChatColor.AQUA + "Miner Level 5" + ChatColor.RESET);
                    }
                    if ((boolean) Main.Load(player.getDisplayName() + "_MinerSkill2")) {
                        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility2"))
                            Main.Save(player.getDisplayName() + "_MinerAbility2", false);
                        else
                            Main.Save(player.getDisplayName() + "_MinerAbility2", true);
                    }
                    break;
                case "BigMiner":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_MinerSkill3")) && 9 <= (Integer) Main.Load(player.getDisplayName() + "_MinerLevel")) {
                        Main.Save(player.getDisplayName() + "_MinerSkill3", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                    } else if (!(boolean) Main.Load(player.getDisplayName() + "_MinerSkill3")) {
                        player.sendMessage("You need " + ChatColor.GOLD + "25000 Coins" + ChatColor.RESET + " and " + ChatColor.AQUA + "Miner Level 9" + ChatColor.RESET);
                    }
                    if ((boolean) Main.Load(player.getDisplayName() + "_MinerSkill3")) {
                        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility3"))
                            Main.Save(player.getDisplayName() + "_MinerAbility3", false);
                        else
                            Main.Save(player.getDisplayName() + "_MinerAbility3", true);
                    }
                    break;
                case "Close":
                    Menu.openMenu(player);
                    break;
            }
            if(!name.equals("Close"))
                Miner.openMinerMenu(player);
            event.setCancelled(true);
        }
    }

    public void autoSmeltOre(BlockBreakEvent event) {

        switch (event.getBlock().getType()) {
            case IRON_ORE:
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                event.setCancelled(true);
                checkBlockXp(event.getPlayer(), event.getBlock());
                event.getBlock().setType(Material.AIR);
                break;
            case GOLD_ORE:
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                event.setCancelled(true);
                checkBlockXp(event.getPlayer(), event.getBlock());
                event.getBlock().setType(Material.AIR);
                break;
            case STONE:
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.STONE));
                event.setCancelled(true);
                checkBlockXp(event.getPlayer(), event.getBlock());
                event.getBlock().setType(Material.AIR);
                break;
        }
        UpdateMainHand(event.getPlayer(), 1);
    }

    public void autoSmeltOre(Player player, Block block) {
        if ((boolean) Main.Load(player.getDisplayName() + "_MinerAbility1") && createArray().contains(player.getInventory().getItemInMainHand().getType())) {
            switch (block.getType()) {
                case IRON_ORE:
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
                    block.setType(Material.AIR);
                    break;
                case GOLD_ORE:
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
                    block.setType(Material.AIR);
                    break;
                case STONE:
                    block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.STONE));
                    block.setType(Material.AIR);
                    break;
                default:
                    if (block.getType() != Material.AIR) {
                        block.breakNaturally();
                        block.setType(Material.AIR);
                    }
                    break;
            }
        }
    }

    public static void giveEffects(Player p) {
        if (createArray().contains(p.getInventory().getItemInMainHand().getType()) && (boolean) Main.Load(p.getDisplayName() + "_MinerAbility2")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0));
        }
        if (Arrays.asList(new Material[]{Material.DIAMOND_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.WOODEN_AXE, Material.STONE_AXE, Material.NETHERITE_AXE}).contains(p.getInventory().getItemInMainHand().getType()) && (boolean) Main.Load(p.getDisplayName() + "_LumberjackAbility2")) {
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
                giveEffects(p);
            }
        }, 20, 20);//Time in ticks before first run and each time after that*/
    }

    @EventHandler //TODO: AUSLAGERN
    public void triggerEvents(BlockBreakEvent event) {
        if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility1") && createArray().contains(event.getPlayer().getInventory().getItemInMainHand().getType()))
            autoSmeltOre(event);
        if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility3") && createArray().contains(event.getPlayer().getInventory().getItemInMainHand().getType()))
            BreakThreeByThree(event);
        checkBlockXp(event.getPlayer(), event.getBlock());
        Scoreboards.createScoreboard(Main.getConfigFile(), event.getPlayer()); //TODO: Effizienz
    }

    public static MyCustomConfig config1 = MyCustomConfig.getConfig("placedBlocks");
    public static ArrayList<String> world = new ArrayList<>();
    public static ArrayList<String> world_nether = new ArrayList<>();
    public static ArrayList<String> world_the_end = new ArrayList<>();
    public static ArrayList<Material> restrictedItems = new ArrayList<Material>(Arrays.asList(Material.GOLD_ORE, Material.IRON_ORE));
    public static ArrayList<Material> luckMaterial = new ArrayList<Material>(Arrays.asList(Material.DIAMOND_ORE, Material.COAL_ORE, Material.EMERALD_ORE, Material.REDSTONE_ORE, Material.NETHER_QUARTZ_ORE, Material.GLOWSTONE, Material.NETHER_GOLD_ORE, Material.LAPIS_ORE));
    public static void UpdateMainHand(Player p, int blocksBroken) { // TODO Don't break if UNBREAKABLE NBT
        ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();

        if (p.getInventory().getItemInMainHand().getType().getMaxDurability() > 1)
            if (im instanceof Damageable) {
                Damageable dmg = (Damageable) im;
                Random r = new Random();

                int damageToDeal = 0;

                for (int i = 0; i < blocksBroken; i++)
                    switch (p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY)) {
                        case 0:
                            damageToDeal++;
                            break;
                        case 1:
                            if (r.nextInt(100) <= 80)
                                damageToDeal++;
                            break;
                        case 2:
                            if (r.nextInt(100) <= 60)
                                damageToDeal++;
                            break;
                        case 3:
                            if (r.nextInt(100) <= 50)
                                damageToDeal++;
                            break;
                        case 4:
                            if (r.nextInt(100) <= 40)
                                damageToDeal++;
                            break;
                        default:
                            // Invalid Enchantment Level
                            break;
                    }

                dmg.setDamage(dmg.getDamage() + damageToDeal);
                p.getInventory().getItemInMainHand().setItemMeta(im);

                if (p.getInventory().getItemInMainHand().getType().getMaxDurability() < dmg.getDamage()) {
                    p.getInventory().remove(p.getInventory().getItemInMainHand());
                }
            }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (config1.get("world") == null)
            config1.Save("world", world);
        if (config1.get("world_nether") == null)
            config1.Save("world_nether", world_nether);
        if (config1.get("world_the_end") == null)
            config1.Save("world_the_end", world_the_end);

        if (restrictedItems.contains(event.getBlock().getType()))
            switch (event.getBlock().getLocation().getWorld().getName()) {
                case "world":
                    world = (ArrayList<String>) config1.get("world");
                    world.add((int) event.getBlock().getLocation().getX() + " " + (int) event.getBlock().getLocation().getY() + " " + (int) event.getBlock().getLocation().getZ());
                    config1.Save("world", world);
                    break;
                case "world_nether":
                    world_nether = (ArrayList<String>) config1.get("world_nether");
                    world_nether.add((int) event.getBlock().getLocation().getX() + " " + (int) event.getBlock().getLocation().getY() + " " + (int) event.getBlock().getLocation().getZ());
                    config1.Save("world_nether", world_nether);
                    break;
                case "world_the_end":
                    world_the_end = (ArrayList<String>) config1.get("world_the_end");
                    world_the_end.add((int) event.getBlock().getLocation().getX() + " " + (int) event.getBlock().getLocation().getY() + " " + (int) event.getBlock().getLocation().getZ());
                    config1.Save("world_the_end", world_the_end);
                    break;
            }
    }

    public Material convertBreak(Location l) {
        switch (l.getBlock().getType()){
            case DIAMOND_ORE:
                return Material.DIAMOND;
            case COAL_ORE:
                return Material.COAL;
            case EMERALD_ORE:
                return Material.EMERALD;
            case REDSTONE_ORE:
                return Material.REDSTONE;
            case NETHER_QUARTZ_ORE:
                return Material.QUARTZ;
            case GLOWSTONE:
                return Material.GLOWSTONE_DUST;
            case NETHER_GOLD_ORE:
                return Material.GOLD_NUGGET;
            case LAPIS_ORE:
                return Material.LAPIS_LAZULI;
        }
        return l.getBlock().getType();
    }
}