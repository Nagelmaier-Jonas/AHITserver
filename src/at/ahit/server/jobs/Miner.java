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
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Array;
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
                    playerXp += 1;
                    break;
                case NETHER_QUARTZ_ORE:
                    playerXp += 10;
                    break;
                case NETHERRACK:
                    playerXp += 1;
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

    // TODO: DEPENDS ON DIRECTION, MAGIER KÖNNTE ENCHANTLVL ERHÖHEN, as telekinesis
    @EventHandler
    public void BreakThreeByThree(BlockBreakEvent event) {

        if ((boolean) Main.Load(event.getPlayer().getDisplayName() + "_MinerAbility3") && createArray().contains(event.getPlayer().getInventory().getItemInMainHand().getType())) {
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

            for (Location l : locationList) {
                ItemMeta im = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                if(l.getBlock().getType() != Material.AIR) {
                    if (im instanceof Damageable) {
                        event.getPlayer().sendMessage("Damaged");
                        Damageable dmg = (Damageable) im;
                        Random r = new Random();
                        switch (event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY))
                        {
                            case 0:
                                dmg.setDamage(dmg.getDamage() + 1);
                                break;
                            case 1:
                                if(r.nextInt(100) <= 80)
                                    dmg.setDamage(dmg.getDamage() + 1);
                                break;
                            case 2:
                                if(r.nextInt(100) <= 60)
                                    dmg.setDamage(dmg.getDamage() + 1);
                                break;
                            case 3:
                                if(r.nextInt(100) <= 50)
                                    dmg.setDamage(dmg.getDamage() + 1);
                                break;
                            case 4: // TODO 80, 60, 50 40
                                if(r.nextInt(100) <= 40)
                                    dmg.setDamage(dmg.getDamage() + 1);
                                break;
                        }
                        event.getPlayer().getInventory().getItemInMainHand().setItemMeta(im);
                    }
                }
                l.getBlock().breakNaturally();
            }
        }
    }
    // shop
    public static void openMinerMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Miner");

        ItemStack skill1 = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Autosmelt");
        ArrayList<String> skill1Lore = new ArrayList<String>();
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
        ArrayList<String> skill2Lore = new ArrayList<String>();
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
        ArrayList<String> skill3Lore = new ArrayList<String>();
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
        if(createArray().contains(p.getInventory().getItemInMainHand().getType()) && (boolean) Main.Load(p.getDisplayName() + "_MinerSkill2")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0));
        }
    }

    public static ArrayList<Material> createArray() {
        ArrayList<Material> pickAxeList = new ArrayList<Material>();
        pickAxeList.add(Material.WOODEN_PICKAXE);
        pickAxeList.add(Material.STONE_PICKAXE);
        pickAxeList.add(Material.IRON_PICKAXE);
        pickAxeList.add(Material.GOLDEN_PICKAXE);
        pickAxeList.add(Material.DIAMOND_PICKAXE);
        pickAxeList.add(Material.NETHERITE_PICKAXE);
        return pickAxeList;
    }

    public static void startRunnable(){
        List<Player> pList = (List<Player>) Bukkit.getOnlinePlayers();
        Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable(){

            @Override
            public void run() {
                for (Player p:pList) {
                    giveHaste(p);
                }
            }

        }, 20, 20);//Time in ticks before first run and each time after that*/
    }
}
