package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Lumberjack implements Listener {



    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        int level = (int) Main.Load(player.getDisplayName() + "_LumberjackLevel");
        int playerXp = (int) Main.Load(player.getDisplayName() + "_LumberjackXp");

        Material m = event.getBlock().getType();

        HashMap<Material, Integer> woodTypes = new HashMap<Material, Integer>();
        HashMap<Material, Material> leafTypes = new HashMap<Material, Material>();

        // TODO Leave Drop Chance
        leafTypes.put(Material.OAK_LEAVES, Material.OAK_SAPLING);
        leafTypes.put(Material.DARK_OAK_LEAVES, Material.DARK_OAK_SAPLING);
        leafTypes.put(Material.BIRCH_LEAVES, Material.BIRCH_SAPLING);
        leafTypes.put(Material.JUNGLE_LEAVES, Material.JUNGLE_SAPLING);
        leafTypes.put(Material.SPRUCE_LEAVES, Material.SPRUCE_SAPLING);
        leafTypes.put(Material.ACACIA_LEAVES, Material.ACACIA_SAPLING);
        // leafTypes.add(Material.OAK_LEAVES); // Nether Wart Block | Shroomlight // TODO Nether Wart Leaves & Shroom Lights // Ignore?
        // leafTypes.add(Material.OAK_LEAVES); // Warped Block | ShroomLight // TODO Warped Leaves & Shroom Lights // Ignore?


        if (leafTypes.containsKey(m)) {
            Random random = new Random();

            if (random.nextDouble() >= 1 - (((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility1")) ? 0.2 : 0.05))
                player.getWorld().dropItem(block.getLocation(), new ItemStack(leafTypes.get(m)));

            if (m == Material.OAK_LEAVES || m == Material.DARK_OAK_LEAVES) {
                if (random.nextDouble() >= 1 - (((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility1")) ? 0.02 : 0.005)) {
                    player.getWorld().dropItem(block.getLocation(), new ItemStack(Material.APPLE));
                }
            }

            UpdateMainHand(player, 1);
            event.getBlock().breakNaturally();

            event.setCancelled(true);

            //breakAdjacentBlocks(player, block, m, leafTypes.get(m), ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill3")) ? 0.2 : 0.05);
        }

        // Auf "new BlockBreakEvent" umsteigen?? Don't think so! | https://bukkit.org/threads/simulating-block-destruction-by-player.27574/

        woodTypes.put(Material.OAK_LOG, 5);
        woodTypes.put(Material.DARK_OAK_LOG, 7);
        woodTypes.put(Material.BIRCH_LOG, 7);
        woodTypes.put(Material.JUNGLE_LOG, 3);
        woodTypes.put(Material.SPRUCE_LOG, 5);
        woodTypes.put(Material.ACACIA_LOG, 7);
        woodTypes.put(Material.CRIMSON_STEM, 10);
        woodTypes.put(Material.WARPED_STEM, 10);

        List<Material> axes = Arrays.asList((new Material[] { Material.DIAMOND_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.WOODEN_AXE, Material.STONE_AXE, Material.NETHERITE_AXE }).clone());

        if (woodTypes.containsKey(m) && axes.contains(player.getInventory().getItemInMainHand().getType())) {
            int blocksBroken = breakAdjacentBlocks(player, block, m);
            playerXp += blocksBroken * woodTypes.get(m);

            UpdateMainHand(player, blocksBroken);

            // ItemStack i = player.getInventory().getItemInMainHand();
            // i.setDurability((short) (i.getDurability() - (blocksBroken))); /// (i.getEnchantmentLevel(Enchantment.DURABILITY) + 1)))); // Old Code!
        }

        // TODO: Possible Levelling Improvements (Non-Linear, don't set XP to 0)
        if(100 * level <= playerXp) {
            player.sendMessage("You are now lumberjack level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
            Main.Save(player.getDisplayName() + "_LumberjackLevel", level);
            Main.Save(player.getDisplayName() + "_LumberjackXp", 0);
        }else{
            Main.Save(player.getDisplayName() + "_LumberjackXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

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

    public static int breakAdjacentBlocks(Player p, Block b, Material m) {
        if ((boolean) Main.Load(p.getDisplayName() + "_LumberjackAbility3"))
            return breakAdjacentBlocks(p, b, m, 0, 1000);
        else
            return breakAdjacentBlocks(p, b, m, 0, 1);
    }

    private static int breakAdjacentBlocks(Player p, Block b, Material m, int count, int maxCount) {
        World world = p.getWorld();

        Random random = new Random();

        // p.sendMessage("Break aufgerufen yey");
        // p.sendMessage(b.getLocation().toString());

        //p.getInventory().addItem(new ItemStack(b.getType()));
        b.breakNaturally();

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++) {
                    if (b.getRelative(i, j, k).getType() == m && maxCount > 1) {
                        int brokenBlocks = breakAdjacentBlocks(p, b.getRelative(i, j, k), m, 0, maxCount - 1);

                        count += brokenBlocks;
                        maxCount -= brokenBlocks;
                    }
                }


        return ++count;
    }

    // TODO: Adjust Prices
    // TODO: ColorCodes in Descriptions!
    public static void openLumberjackMenu(Player player){
        /*Inventory inventory = Bukkit.createInventory(null, 9, "Lumberjack");

        ItemStack skill1 = new ItemStack(Material.STONE_AXE,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Luck");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Over 4 times sapling & apple chance!");
        skill1Lore.add("Costs: 1000c");
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.IRON_AXE,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Haste");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Haste while holding an axe!");
        skill2Lore.add("Costs: 1000c");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.DIAMOND_AXE,1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        skill3Meta.setDisplayName("Treepacitator");
        ArrayList<String> skill3Lore = new ArrayList<String>();
        skill3Lore.add("Mine whole trees!");
        skill3Lore.add("Costs: 1000c");
        skill3Meta.setLore(skill3Lore);
        skill3.setItemMeta(skill3Meta);

        ItemStack close = new ItemStack(Material.BARRIER,1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(1,skill1);
        inventory.setItem(3,skill2);
        inventory.setItem(5,skill3);
        inventory.setItem(8,close);

        player.openInventory(inventory);*/

        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player, Material.STONE_AXE, 1, "Luck", new ArrayList<>(Arrays.asList("Over 4 times sapling & apple chance when breaking leaves", "Costs: 2500 Coins")), "Lumberjack", 1));
        items.add(SkillMenu.createItem(player, Material.IRON_AXE, 1, "Haste", new ArrayList<>(Arrays.asList("Blocks break faster with an axe", "Costs: 10000 Coins")), "Lumberjack", 2));
        items.add(SkillMenu.createItem(player, Material.DIAMOND_AXE, 1, "Treepacitator", new ArrayList<>(Arrays.asList("Mine whole trees at a time", "Costs: 25000 Coins")), "Lumberjack", 3));
        items.add(SkillMenu.createItem(Material.BARRIER, 1, "Close"));

        if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill1")) {
            if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility1")) {
                items.get(0).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1); // TODO Ask the guy who called it "Loot_Bonus_Blocks" if he is stupid
                RemoveEnchantmentLore(items.get(0));
            }
        }

        if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill2")) {
            if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility2")) {
                items.get(1).addEnchantment(Enchantment.DIG_SPEED, 1);
                RemoveEnchantmentLore(items.get(1));
            }
        }

        if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill3")) {
            if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility3")) {
                items.get(2).addEnchantment(Enchantment.DURABILITY, 1);
                RemoveEnchantmentLore(items.get(2));

                // items.get(2).getItemMeta().getLore().add(ChatColor.GREEN + "Ability enabled");
            }
        }

        Inventory inventory = SkillMenu.createSkillInventory(player, "Lumberjack", new HashMap<Integer, ItemStack>() {{
            put(1, items.get(0));
            put(3, items.get(1));
            put(5, items.get(2));
            put(8, items.get(3));
        }});
        player.openInventory(inventory);
    }

    public static void AddLoreLine(ItemStack i, String line) {
        ItemMeta meta = i.getItemMeta();
        List<String> lore =  meta.getLore();
        lore.add(line);
        meta.setLore(lore);
        i.setItemMeta(meta);
    }

    public static void RemoveEnchantmentLore(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(meta);
    }

    public static void onLumberjackJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        int playerCoins = (int) Main.Load(player.getDisplayName() + "_Amount");

        if (event.getView().getTitle() == "Lumberjack")
            if (itemStack.getType() != Material.AIR){
                String name = itemStack.getItemMeta().getDisplayName();

                switch (name){
                    case "Luck":
                        if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill1")) {
                            Main.Save(player.getDisplayName() + "_LumberjackAbility1", !((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility1")));
                            Lumberjack.openLumberjackMenu(player);
                        }
                        else if (playerCoins >= 2500) {
                            player.sendMessage("Obtained new skill: Luck");

                            Main.Save(((Player) event.getWhoClicked()).getDisplayName() + "_LumberjackSkill1", true);
                            Main.Save(player.getDisplayName() + "_Amount", playerCoins - 2500);

                            Scoreboards.createScoreboard(Main.getConfigFile(), player);
                            Lumberjack.openLumberjackMenu(player);
                        }
                        else
                            player.sendMessage("YOU SHALL NOT! >:0");
                        break;
                    case "Haste":
                        if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill2")) {
                            Main.Save(player.getDisplayName() + "_LumberjackAbility2", !((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility2")));
                            Lumberjack.openLumberjackMenu(player);
                        }
                        else if (playerCoins >= 2500) {
                            player.sendMessage("Obtained new skill: Haste");

                            Main.Save(player.getDisplayName() + "_LumberjackSkill2", true);
                            Main.Save(player.getDisplayName() + "_Amount", playerCoins - 10000);

                            Scoreboards.createScoreboard(Main.getConfigFile(), player);
                            Lumberjack.openLumberjackMenu(player);
                        }
                        else
                            player.sendMessage("YOU SHALL NOT! >:0");
                        break;
                    case "Treepacitator":
                        if ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill3")) {
                            Main.Save(player.getDisplayName() + "_LumberjackAbility3", !((boolean) Main.Load(player.getDisplayName() + "_LumberjackAbility3")));
                            Lumberjack.openLumberjackMenu(player);
                        }
                        else if (playerCoins >= 2500) {
                            player.sendMessage("Obtained new skill: Treepacitator");

                            Main.Save(player.getDisplayName() + "_LumberjackSkill3", true);
                            Main.Save(player.getDisplayName() + "_Amount", playerCoins - 25000);

                            Scoreboards.createScoreboard(Main.getConfigFile(), player);
                            Lumberjack.openLumberjackMenu(player);
                        }
                        else
                            player.sendMessage("YOU SHALL NOT! >:0");
                        break;
                    case "Close":
                        Menu.openMenu(player);
                        break;
                }

                event.setCancelled(true);
            }
    }
}
