package at.ahit.server.overlays;

import at.ahit.server.jobs.*;
import at.ahit.server.main.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static at.ahit.server.jobs.Lumberjack.RemoveEnchantmentLore;
import static at.ahit.server.usefulstuff.AQuestMethods.getInventoryLocation;

public class QuestMenu {
    
    public static void createQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player,Material.STONE,1,"MinerQuests",new ArrayList<>(Collections.singletonList("Miner-Quests"))));
        items.add(SkillMenu.createItem(player,Material.ENCHANTED_BOOK,1,"WizardQuests",new ArrayList<>(Collections.singletonList("Wizard-Quests"))));
        items.add(SkillMenu.createItem(player,Material.WHEAT,1,"FarmerQuests",new ArrayList<>(Collections.singletonList("Farmer-Quests"))));
        items.add(SkillMenu.createItem(player,Material.BOW,1,"HunterQuests",new ArrayList<>(Collections.singletonList("Hunter-Quests"))));
        items.add(SkillMenu.createItem(player,Material.OAK_WOOD,1,"LumberjackQuests",new ArrayList<>(Collections.singletonList("Lumberjack-Quests"))));
        items.add(SkillMenu.createItem(player,Material.ZOMBIE_HEAD,1,"MonsterHunterQuests",new ArrayList<>(Collections.singletonList("MonsterHunter-Quests"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        Inventory inventory = SkillMenu.createSkillInventory(player,"Quests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(5,items.get(5));
            put(8,items.get(6));
        }});
        player.openInventory(inventory);
    }

    public static void onQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "MinerQuests":
                QuestMenu.createMinerQuestMenu(player);
                break;
            case "FarmerQuests":
                QuestMenu.createFarmerQuestMenu(player);
                break;
            case "HunterQuests":
                QuestMenu.createHunterQuestMenu(player);
                break;
            case "LumberjackQuests":
                QuestMenu.createLumberjackQuestMenu(player);
                break;
            case "MonsterHunterQuests":
                QuestMenu.createMonsterHunterQuestMenu(player);
                break;
            case "WizardQuests":
                QuestMenu.createWizardQuestMenu(player);
                break;
            case "Close":
                player.closeInventory();
                break;
        }
        event.setCancelled(true);
    }


    public static void createFarmerQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        String quests = (String) Main.Load(player.getDisplayName() + "_QuestsFarmer");

        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest1",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Collect you first Seeds",ChatColor.GREEN + "(4x Seeds required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest2",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Craft a Compostor",ChatColor.GREEN + "(1x Compostor required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest3",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Craft a Diamond Hoe",ChatColor.GREEN + "(1x Diamond Hoe)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest4",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Build a Big Farm",ChatColor.GREEN + "(64x Pumpkin Pies required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest5",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get The Ultimate Hoe",ChatColor.GREEN + "(1x Netherite Hoe)"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        addEnchantments(quests,items);

        Inventory inventory = SkillMenu.createSkillInventory(player,"FarmerQuests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(8,items.get(5));
        }});
        player.openInventory(inventory);
    }
    public static void onFarmerQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "Quest1":
                checkQuests(player, "Farmer", 1);
                break;
            case "Quest2":
                checkQuests(player, "Farmer", 2);
                break;
            case "Quest3":
                checkQuests(player, "Farmer", 3);
                break;
            case "Quest4":
                checkQuests(player, "Farmer", 4);
                break;
            case "Quest5":
                checkQuests(player, "Farmer", 5);
                break;
            case "Close":
                createQuestMenu(player);
                break;
        }
        event.setCancelled(true);
    }
    public static void executeFarmerQuest(Player player, Integer questIndex){
        switch (questIndex){
            case 1:
                if (getInventoryLocation(Material.WHEAT_SEEDS , 4, player, 12, true, true, Material.WHEAT, 6, "Wheat", true))
                    Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 0, 0, 0, 0");
                break;
            case 2:
                if (getInventoryLocation(Material.COMPOSTER , 1, player, 17, false, false, Material.ACACIA_BOAT, 0, "none", true))
                    Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 0, 0, 0");
                break;
            case 3:
                if (getInventoryLocation(Material.DIAMOND_HOE , 1, player, 100, false, false, Material.ACACIA_BOAT, 0, "none", true))
                    Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 1, 0, 0");
                break;
            case 4:
                if (getInventoryLocation(Material.PUMPKIN_PIE , 64, player, 400, true, true, Material.ENCHANTED_GOLDEN_APPLE, 1, "God Apple", true))
                    Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 1, 1, 0");
                break;
            case 5:
                if (getInventoryLocation(Material.NETHERITE_HOE, 1, player, 1500, false, true, Material.DIAMOND, 2, "DIAMOND", true))
                    Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 1, 1, 1");
                break;
        }
        createFarmerQuestMenu(player);
    }


    public static void createHunterQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        String quests = (String) Main.Load(player.getDisplayName() + "_QuestsHunter");

        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest1",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get your first food",ChatColor.GREEN + "(3x Raw Food required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest2",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Craft some Arrows",ChatColor.GREEN + "(64x Arrows required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest3",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Pink Wool",ChatColor.GREEN + "(15x Pink Wool required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest4",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get a Saddle",ChatColor.GREEN + "(1x Saddle required required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest5",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Leather",ChatColor.GREEN + "(64x Leather required)"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        addEnchantments(quests,items);

        Inventory inventory = SkillMenu.createSkillInventory(player,"HunterQuests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(8,items.get(5));
        }});
        player.openInventory(inventory);
    }
    public static void onHunterQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "Quest1":
                checkQuests(player, "Hunter", 1);
                break;
            case "Quest2":
                checkQuests(player, "Hunter", 2);
                break;
            case "Quest3":
                checkQuests(player, "Hunter", 3);
                break;
            case "Quest4":
                checkQuests(player, "Hunter", 4);
                break;
            case "Quest5":
                checkQuests(player, "Hunter", 5);
                break;
            case "Close":
                createQuestMenu(player);
                break;
        }
        event.setCancelled(true);
    }
    public static void executeHunterQuest(Player player, Integer questIndex){
        switch (questIndex){
            case 1:
                if (getInventoryLocation(Material.BEEF , 3,  player, 12, true,true, Material.COOKED_BEEF, 3, "Steaks", false)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.PORKCHOP , 3,  player, 12, true,true, Material.COOKED_PORKCHOP, 3, "Cooked Porkchop", false)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.MUTTON , 3,  player, 12, true,true, Material.COOKED_MUTTON, 3, "Cooked Mutton", false)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.CHICKEN , 3,  player, 12, true,true, Material.COOKED_CHICKEN, 3, "Cooked Chicken", false)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.RABBIT , 3,  player, 12, true,true, Material.COOKED_RABBIT, 3, "Cooked Rabbit", false)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.SALMON , 3,  player, 12, true,true, Material.COOKED_SALMON, 3, "Cooked Salmon", false)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.COD , 3,  player, 12, true,true, Material.COOKED_COD, 3, "Cooked Cod", true)){
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                }
                break;
            case 2:
                if (getInventoryLocation(Material.ARROW , 64,  player, 45, false, false, Material.COOKED_BEEF, 3, "Steaks", true))
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 0, 0, 0");
                break;
            case 3:
                if (getInventoryLocation(Material.PINK_WOOL , 15,  player, 50, false, false, Material.COOKED_BEEF, 3, "Steaks", true))
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 1, 0, 0");
                break;
            case 4:
                if (getInventoryLocation(Material.SADDLE , 1,  player, 300, false, false, Material.ACACIA_BOAT, 0, "none", true))
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 1, 1, 0");
                break;
            case 5:
                if (getInventoryLocation(Material.LEATHER, 64, player, 500, true, true, Material.BOOKSHELF, 16, "Bookshelf", true))
                    Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 1, 1, 1");
                break;
        }
        createHunterQuestMenu(player);
    }


    public static void createLumberjackQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        String quests = (String) Main.Load(player.getDisplayName() + "_QuestsLumberjack");

        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest1",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Wood",ChatColor.GREEN + "(32x Wood Logs required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest2",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Wood from the Nether",ChatColor.GREEN + "(32x Nether Logs required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest3",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get Storage for your Wood",ChatColor.GREEN + "(64x Chests required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest4",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Not Implemented",ChatColor.GREEN + ""))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest5",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get The Ultimate Axe",ChatColor.GREEN + "(1x Netherite Axe)"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        addEnchantments(quests,items);

        Inventory inventory = SkillMenu.createSkillInventory(player,"LumberjackQuests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(8,items.get(5));
        }});
        player.openInventory(inventory);
    }
    public static void onLumberjackQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "Quest1":
                checkQuests(player, "Lumberjack", 1);
                break;
            case "Quest2":
                checkQuests(player, "Lumberjack", 2);
                break;
            case "Quest3":
                checkQuests(player, "Lumberjack", 3);
                break;
            case "Quest4":
                checkQuests(player, "Lumberjack", 4);
                break;
            case "Quest5":
                checkQuests(player, "Lumberjack", 5);
                break;
            case "Close":
                createQuestMenu(player);
                break;
        }
        event.setCancelled(true);
    }
    public static void executeLumberjackQuest(Player player, Integer questIndex){
        switch (questIndex){
            case 1:
                if (getInventoryLocation(Material.OAK_LOG, 32, player, 50, false,false, Material.ACACIA_BOAT, 0, "none", false)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.BIRCH_LOG, 32, player, 50, false,false, Material.ACACIA_BOAT, 0, "none", false)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.ACACIA_LOG, 32, player, 50, false,false, Material.ACACIA_BOAT, 0, "none", false)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.DARK_OAK_LOG, 32, player, 50, false,false, Material.ACACIA_BOAT, 0, "none", false)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.JUNGLE_LOG, 32, player, 50, false,false, Material.ACACIA_BOAT, 0, "none", false)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                }else if (getInventoryLocation(Material.SPRUCE_LOG, 32, player, 50, false,false, Material.ACACIA_BOAT, 0, "none", true)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                }
                break;
            case 2:
                if (getInventoryLocation(Material.CRIMSON_STEM, 32, player, 100, false,false, Material.ACACIA_BOAT, 0, "none", false)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 0, 0, 0");
                }else if (getInventoryLocation(Material.WARPED_STEM, 32, player, 100, false,false, Material.ACACIA_BOAT, 0, "none", true)){
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 0, 0, 0");
                }
                break;
            case 3:
                if (getInventoryLocation(Material.CHEST, 64, player, 400, false,false, Material.ACACIA_BOAT, 0, "none", true))
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 1, 0, 0");
                break;
            case 4:
                if (false)
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 1, 1, 0");
                break;
            case 5:
                if (getInventoryLocation(Material.NETHERITE_AXE, 1, player, 150, false, true, Material.DIAMOND, 3, "Diamonds", true))
                    Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 1, 1, 1");
                break;
        }
        createLumberjackQuestMenu(player);
    }


    public static void createMinerQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        String quests = (String) Main.Load(player.getDisplayName() + "_QuestsMiner");

        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest1",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Clean Stone",ChatColor.GREEN + "(64x Clean Stone required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest2",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Lapis Lazuli",ChatColor.GREEN + "(32x Lapis Lazuli required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest3",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Emeralds",ChatColor.GREEN + "(2x Emeralds required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest4",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Diamonds",ChatColor.GREEN + "(16x Diamonds required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest5",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get the Ultimate Pickaxe",ChatColor.GREEN + "(1x Netherite Pickaxe required)"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        addEnchantments(quests,items);

        Inventory inventory = SkillMenu.createSkillInventory(player,"MinerQuests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(8,items.get(5));
        }});
        player.openInventory(inventory);
    }
    public static void onMinerQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "Quest1":
                checkQuests(player, "Miner", 1);
                break;
            case "Quest2":
                checkQuests(player, "Miner", 2);
                break;
            case "Quest3":
                checkQuests(player, "Miner", 3);
                break;
            case "Quest4":
                checkQuests(player, "Miner", 4);
                break;
            case "Quest5":
                checkQuests(player, "Miner", 5);
                break;
            case "Close":
                createQuestMenu(player);
                break;
        }
        event.setCancelled(true);
    }
    public static void executeMinerQuest(Player player, Integer questIndex){
        switch (questIndex){
            case 1:
                if (getInventoryLocation(Material.STONE , 64,  player, 50, false, true, Material.TORCH, 32, "Torches", true))
                     Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 0, 0, 0, 0");
                break;
            case 2:
                if (getInventoryLocation(Material.LAPIS_LAZULI , 32,  player, 200, false, false, Material.ACACIA_BOAT, 0, "none", true))
                     Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 0, 0, 0");
                break;
            case 3:
                if (getInventoryLocation(Material.EMERALD , 2,  player, 500, false, false, Material.ACACIA_BOAT, 0, "none", true))
                     Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 1, 0, 0");
                break;
            case 4:
                if (getInventoryLocation(Material.DIAMOND , 16,  player, 800, false, true, Material.DIAMOND_PICKAXE, 1, "Diamond Pickaxe", true))
                     Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 1, 1, 0");
                break;
            case 5:
                if (getInventoryLocation(Material.NETHERITE_PICKAXE , 1,  player, 1500, false, false, Material.ACACIA_BOAT, 0, "none", true))
                      Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 1, 1, 1");
                break;
        }
        createMinerQuestMenu(player);
    }


    public static void createMonsterHunterQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        String quests = (String) Main.Load(player.getDisplayName() + "_QuestsMonsterHunter");

        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest1",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get some Ender Pearls",ChatColor.GREEN + "(12x Ender Pearls required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest2",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Fight the Darkness",ChatColor.GREEN + "(1x Wither Skull required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest3",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Kill the Screaming Thing",ChatColor.GREEN + "(1x Netherstar required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest4",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Bring me his Head",ChatColor.GREEN + "(1x Dragon Head required)"))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest5",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Get the Ultimate Sword",ChatColor.GREEN + "(1x Netherite Sword required)"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        addEnchantments(quests,items);

        Inventory inventory = SkillMenu.createSkillInventory(player,"MonsterHunterQuests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(8,items.get(5));
        }});
        player.openInventory(inventory);
    }
    public static void onMonsterHunterQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "Quest1":
                checkQuests(player, "MonsterHunter", 1);
                break;
            case "Quest2":
                checkQuests(player, "MonsterHunter", 2);
                break;
            case "Quest3":
                checkQuests(player, "MonsterHunter", 3);
                break;
            case "Quest4":
                checkQuests(player, "MonsterHunter", 4);
                break;
            case "Quest5":
                checkQuests(player, "MonsterHunter", 5);
                break;
            case "Close":
                createQuestMenu(player);
                break;
        }
        event.setCancelled(true);
    }
    public static void executeMonsterHunterQuest(Player player, Integer questIndex){
        switch (questIndex){
            case 1:
                if (getInventoryLocation(Material.ENDER_PEARL , 12,  player, 50, false, false, Material.ACACIA_BOAT, 0, "none", true))
                     Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 0, 0, 0, 0");
                break;
            case 2:
                if (getInventoryLocation(Material.WITHER_SKELETON_SKULL , 1,  player, 200, false, false, Material.ACACIA_BOAT, 0, "none", true))
                     Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 0, 0, 0");
                break;
            case 3:
                if (getInventoryLocation(Material.NETHER_STAR , 1,  player, 700, false, false, Material.ACACIA_BOAT, 0, "none", true))
                        Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 1, 0, 0");
                break;
            case 4:
                if (getInventoryLocation(Material.DRAGON_HEAD , 1,  player, 1500, false, false, Material.ACACIA_BOAT, 0, "none", true))
                        Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 1, 1, 0");
                break;
            case 5:
                if (getInventoryLocation(Material.NETHERITE_SWORD, 1, player, 1500, false, false, Material.ACACIA_BOAT, 0, "none",true))
                        Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 1, 1, 1");
                break;
        }
        createMonsterHunterQuestMenu(player);
    }


    public static void createWizardQuestMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();
        String quests = (String) Main.Load(player.getDisplayName() + "_QuestsWizard");

        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest1",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Not Implemented",ChatColor.GREEN + ""))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest2",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Not Implemented",ChatColor.GREEN + ""))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest3",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Not Implemented",ChatColor.GREEN + ""))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest4",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Not Implemented",ChatColor.GREEN + ""))));
        items.add(SkillMenu.createItem(player,Material.BOOK,1,"Quest5",new ArrayList<>(Arrays.asList(ChatColor.AQUA + "Not Implemented",ChatColor.GREEN + ""))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        addEnchantments(quests,items);

        Inventory inventory = SkillMenu.createSkillInventory(player,"WizardQuests",new HashMap<Integer,ItemStack>(){{
            put(0,items.get(0));
            put(1,items.get(1));
            put(2,items.get(2));
            put(3,items.get(3));
            put(4,items.get(4));
            put(8,items.get(5));
        }});
        player.openInventory(inventory);
    }
    public static void onWizardQuestsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
        switch (name){
            case "Quest1":
                checkQuests(player, "Wizard", 1);
                break;
            case "Quest2":
                checkQuests(player, "Wizard", 2);
                break;
            case "Quest3":
                checkQuests(player, "Wizard", 3);
                break;
            case "Quest4":
                checkQuests(player, "Wizard", 4);
                break;
            case "Quest5":
                checkQuests(player, "Wizard", 5);
                break;
            case "Close":
                createQuestMenu(player);
                break;
        }
        event.setCancelled(true);
    }
    public static void executeWizardQuest(Player player, Integer questIndex){
        switch (questIndex){
            case 1:
                if (false)
                    Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 0, 0, 0, 0");
                break;
            case 2:
                if (false)
                    Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 0, 0, 0");
                break;
            case 3:
                if (false)
                    Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 1, 0, 0");
                break;
            case 4:
                if (false)
                    Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 1, 1, 0");
                break;
            case 5:
                if (false)
                     Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 1, 1, 1");
                break;
        }
        createWizardQuestMenu(player);
    }


    public static void checkQuests(Player player, String jobType, Integer questIndex){

        String quests = (String) Main.Load(player.getDisplayName() + "_Quests" + jobType);

        switch (questIndex){
            case 1:
                if (quests.equals("0, 0, 0, 0, 0")){
                    executeQuest(player,jobType,questIndex);
                }
                else {
                    player.sendMessage(ChatColor.RED + "You must do the previous Quest, or you've done it already!");
                }
                break;
            case 2:
                if (quests.equals("1, 0, 0, 0, 0")){
                    executeQuest(player,jobType,questIndex);
                }
                else {
                    player.sendMessage(ChatColor.RED + "You must do the previous Quest, or you've done it already!");
                }
                break;
            case 3:
                if (quests.equals("1, 1, 0, 0, 0")){
                    executeQuest(player,jobType,questIndex);
                }
                else {
                    player.sendMessage(ChatColor.RED + "You must do the previous Quest, or you've done it already!");
                }
                break;
            case 4:
                if (quests.equals("1, 1, 1, 0, 0")){
                    executeQuest(player,jobType,questIndex);
                }
                else {
                    player.sendMessage(ChatColor.RED + "You must do the previous Quest, or you've done it already!");
                }
                break;
            case 5:
                if (quests.equals("1, 1, 1, 1, 0")){
                    if ((Integer)Main.Load(player.getDisplayName() + "_" + jobType + "Level") == 10) {
                        executeQuest(player,jobType,questIndex);
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "You must be" + jobType + "lvl 10!");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "You must do the previous Quest, or you've done it already!");
                }
                break;
        }
    }

    public static void executeQuest(Player player, String jobType, Integer questIndex){
        switch (jobType){
            case "Miner":
                QuestMenu.executeMinerQuest(player, questIndex);
                break;
            case "Farmer":
                QuestMenu.executeFarmerQuest(player, questIndex);
                break;
            case "Hunter":
                QuestMenu.executeHunterQuest(player, questIndex);
                break;
            case "Lumberjack":
                QuestMenu.executeLumberjackQuest(player, questIndex);
                break;
            case "MonsterHunter":
                QuestMenu.executeMonsterHunterQuest(player, questIndex);
                break;
            case "Wizard":
                QuestMenu.executeWizardQuest(player, questIndex);
                break;
        }
    }

    public static void addEnchantments(String quests, ArrayList<ItemStack> items){
        switch (quests){
            case "1, 0, 0, 0, 0":
                addEnchantmentToItem(items,0);
                break;
            case "1, 1, 0, 0, 0":
                addEnchantmentToItem(items,0);
                addEnchantmentToItem(items,1);
                break;
            case "1, 1, 1, 0, 0":
                addEnchantmentToItem(items,0);
                addEnchantmentToItem(items,1);
                addEnchantmentToItem(items,2);
                break;
            case "1, 1, 1, 1, 0":
                addEnchantmentToItem(items,0);
                addEnchantmentToItem(items,1);
                addEnchantmentToItem(items,2);
                addEnchantmentToItem(items,3);
                break;
            case "1, 1, 1, 1, 1":
                addEnchantmentToItem(items,0);
                addEnchantmentToItem(items,1);
                addEnchantmentToItem(items,2);
                addEnchantmentToItem(items,3);
                addEnchantmentToItem(items,4);
                break;
        }
    }

    public static void addEnchantmentToItem(ArrayList<ItemStack> items, Integer index){
        ItemMeta meta = items.get(index).getItemMeta();

        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        items.get(index).setItemMeta(meta);
        RemoveEnchantmentLore(items.get(index));
    }
}
