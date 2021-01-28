package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import net.minecraft.server.v1_16_R3.EnchantmentProtection;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
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
        // leafTypes.add(Material.OAK_LEAVES); // Nether Wart Block | Shroomlight // TODO Nether Wart Leaves & Shroom Lights
        // leafTypes.add(Material.OAK_LEAVES); // Warped Block | ShroomLight // TODO Warped Leaves & Shroom Lights


        if (leafTypes.containsKey(m)) {
            Random random = new Random();

            if (random.nextDouble() >= 1 - (((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill2")) ? 0.2 : 0.05))
                player.getWorld().dropItem(block.getLocation(), new ItemStack(leafTypes.get(m)));

            //breakAdjacentBlocks(player, block, m, leafTypes.get(m), ((boolean) Main.Load(player.getDisplayName() + "_LumberjackSkill3")) ? 0.2 : 0.05);
        }

        // TODO: Auf "new BlockBreakEvent" umsteigen | https://bukkit.org/threads/simulating-block-destruction-by-player.27574/

        // TODO Auf skill level 1
        // TODO Hast auf skill level 2

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
            int blocksBroken = breakAdjacentBlocks(player, block, m, m, 1);
            playerXp += blocksBroken * woodTypes.get(m);

            ItemStack i = player.getInventory().getItemInMainHand();
            // i.setDurability((short) (i.getDurability() - (blocksBroken))); /// (i.getEnchantmentLevel(Enchantment.DURABILITY) + 1)))); // TODO: FIX UNBREAKING ENCHANTMENT!

            // TODO Fix Axe Durablity

            // Damageable meta = (Damageable) i.getItemMeta();

            // meta.setDamage(meta.getDamage() + blocksBroken / i.getEnchantmentLevel(Enchantment.DURABILITY) + 1);
            // meta.setDamage(100);
        }

        /*switch (event.getBlock().getType()){
            case OAK_LOG:
                playerXp += 5;
                breakAdjacentBlocks(player, block, Material.OAK_LOG);
                break;
            case DARK_OAK_LOG:
                playerXp += 7;
                breakAdjacentBlocks(player, block, Material.DARK_OAK_LOG);
                break;
            case BIRCH_LOG:
                playerXp += 7;
                breakAdjacentBlocks(player, block, Material.BIRCH_LOG);
                break;
            case JUNGLE_LOG:
                playerXp += 3;
                breakAdjacentBlocks(player, block, Material.JUNGLE_LOG);
                break;
            case SPRUCE_LOG:
                playerXp += 5;
                breakAdjacentBlocks(player, block, Material.SPRUCE_LOG);
                break;
            case ACACIA_LOG:
                playerXp += 7;
                breakAdjacentBlocks(player, block, Material.ACACIA_LOG);
                break;
            case CRIMSON_STEM:
                playerXp += 10;
                breakAdjacentBlocks(player, block, Material.CRIMSON_STEM);
                break;
            case WARPED_STEM:
                playerXp += 10;
                breakAdjacentBlocks(player, block, Material.WARPED_STEM);
                break;
        }*/

        if(100 * level <= playerXp) {
            player.sendMessage("You are now lumberjack level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
            Main.Save(player.getDisplayName() + "_LumberjackLevel", level);
            Main.Save(player.getDisplayName() + "_LumberjackXp", 0);
        }else{
            Main.Save(player.getDisplayName() + "_LumberjackXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    public static int breakAdjacentBlocks(Player p, Block b, Material m, Material drop, double chance) {
        if ((boolean) Main.Load(p.getDisplayName() + "_LumberjackSkill3"))
            return breakAdjacentBlocks(p, b, m, drop, chance, 1);
        return 1;
    }

    private static int breakAdjacentBlocks(Player p, Block b, Material m, Material drop, double chance, int count) {
        World world = p.getWorld();

        Random random = new Random();

        // p.sendMessage("Break aufgerufen yey");
        // p.sendMessage(b.getLocation().toString());

        //p.getInventory().addItem(new ItemStack(b.getType()));
        if (random.nextDouble() >= 1 - chance)
            world.dropItem(b.getLocation(), new ItemStack(drop));

        b.setType(Material.AIR);

        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                for (int k = -1; k <= 1; k++) {
                    if (b.getRelative(i, j, k).getType() == m) {
                        count += breakAdjacentBlocks(p, b.getRelative(i, j, k), m, drop, chance, 0);
                    }
                }


        return count++;
    }

    public static void openLumberjackMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "Lumberjack");

        ItemStack skill1 = new ItemStack(Material.STONE_AXE,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Skill1");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Skill1");
        skill1Lore.add("Costs: 1000c");
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.IRON_AXE,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Skill2");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Skill2");
        skill2Lore.add("Costs: 1000c");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.DIAMOND_AXE,1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        skill3Meta.setDisplayName("Skill3");
        ArrayList<String> skill3Lore = new ArrayList<String>();
        skill3Lore.add("Skill3");
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

        player.openInventory(inventory);
    }

    public static void onLumberjackJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR){
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name){
                case "Skill1":
                    player.sendMessage("obtained skill1");
                    break;
                case "Skill2":
                    player.sendMessage("MUCH higher sapling chance!"); // TODO
                    Main.Save(((Player) event.getWhoClicked()).getDisplayName() + "_LumberjackSkill2", true);
                    break;
                case "Skill3":
                    player.sendMessage("Obtained tree-insta-break!"); // TODO
                    Main.Save(((Player) event.getWhoClicked()).getDisplayName() + "_LumberjackSkill3", true);
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
