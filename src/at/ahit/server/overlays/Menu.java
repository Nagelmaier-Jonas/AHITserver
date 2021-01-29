package at.ahit.server.overlays;

import at.ahit.server.jobs.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Menu {

    //TODO: Set cursor position

    public static void openMenu(Player player) {
/*        Inventory inventory = Bukkit.createInventory(null, 9, "Job");

        ItemStack miner = new ItemStack(Material.STONE,1);
        ItemMeta minerMeta = miner.getItemMeta();
        assert minerMeta != null;
        minerMeta.setDisplayName("Miner");
        ArrayList<String> minerLore = new ArrayList<>();
        minerLore.add("Miner-Job");
        minerMeta.setLore(minerLore);
        miner.setItemMeta(minerMeta);

        ItemStack cook = new ItemStack(Material.FURNACE,1);
        ItemMeta cookMeta = cook.getItemMeta();
        assert cookMeta != null;
        cookMeta.setDisplayName("Cook");
        ArrayList<String> cookLore = new ArrayList<>();
        cookLore.add("Cook-Job");
        cookMeta.setLore(cookLore);
        cook.setItemMeta(cookMeta);

        ItemStack farmer = new ItemStack(Material.WHEAT,1);
        ItemMeta farmerMeta = farmer.getItemMeta();
        assert farmerMeta != null;
        farmerMeta.setDisplayName("Farmer");
        ArrayList<String> farmerLore = new ArrayList<>();
        farmerLore.add("Farmer-Job");
        farmerMeta.setLore(farmerLore);
        farmer.setItemMeta(farmerMeta);

        ItemStack hunter = new ItemStack(Material.BOW,1);
        ItemMeta hunterMeta = hunter.getItemMeta();
        assert hunterMeta != null;
        hunterMeta.setDisplayName("Hunter");
        ArrayList<String> hunterLore = new ArrayList<>();
        hunterLore.add("Hunter-Job");
        hunterMeta.setLore(hunterLore);
        hunter.setItemMeta(hunterMeta);

        ItemStack lumberjack = new ItemStack(Material.OAK_WOOD,1);
        ItemMeta lumberjackMeta = lumberjack.getItemMeta();
        assert lumberjackMeta != null;
        lumberjackMeta.setDisplayName("Lumberjack");
        ArrayList<String> lumberjackLore = new ArrayList<>();
        lumberjackLore.add("Lumberjack-Job");
        lumberjackMeta.setLore(lumberjackLore);
        lumberjack.setItemMeta(lumberjackMeta);

        ItemStack monsterHunter = new ItemStack(Material.ZOMBIE_HEAD,1);
        ItemMeta monsterHunterMeta = monsterHunter.getItemMeta();
        assert monsterHunterMeta != null;
        monsterHunterMeta.setDisplayName("MonsterHunter");
        ArrayList<String> monsterHunterLore = new ArrayList<>();
        monsterHunterLore.add("MonsterHunter-Job");
        monsterHunterMeta.setLore(monsterHunterLore);
        monsterHunter.setItemMeta(monsterHunterMeta);

        ItemStack close = new ItemStack(Material.BARRIER,1);
        ItemMeta closeMeta = close.getItemMeta();
        assert closeMeta != null;
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(0,miner);
        inventory.setItem(1,cook);
        inventory.setItem(2,farmer);
        inventory.setItem(3,hunter);
        inventory.setItem(4,lumberjack);
        inventory.setItem(5,monsterHunter);

        inventory.setItem(8,close);

        player.openInventory(inventory);*/

        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player,Material.STONE,1,"Miner",new ArrayList<>(Collections.singletonList("Miner-Job"))));
        items.add(SkillMenu.createItem(player,Material.ENCHANTED_BOOK,1,"Wizard",new ArrayList<>(Collections.singletonList("Wizard-Job"))));
        items.add(SkillMenu.createItem(player,Material.WHEAT,1,"Farmer",new ArrayList<>(Collections.singletonList("Farmer-Job"))));
        items.add(SkillMenu.createItem(player,Material.BOW,1,"Hunter",new ArrayList<>(Collections.singletonList("Hunter-Job"))));
        items.add(SkillMenu.createItem(player,Material.OAK_WOOD,1,"Lumberjack",new ArrayList<>(Collections.singletonList("Lumberjack-Job"))));
        items.add(SkillMenu.createItem(player,Material.ZOMBIE_HEAD,1,"MonsterHunter",new ArrayList<>(Collections.singletonList("MonsterHunter-Job"))));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        Inventory inventory = SkillMenu.createSkillInventory(player,"Job",new HashMap<Integer,ItemStack>(){{
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

    public static void onJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        assert itemStack != null;
            String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
            switch (name){
                case "Miner":
                    Miner.openMinerMenu(player);
                    break;
                case "Farmer":
                    Farmer.openFarmerMenu(player);
                    break;
                case "Hunter":
                    Hunter.openHunterMenu(player);
                    break;
                case "Lumberjack":
                    Lumberjack.openLumberjackMenu(player);
                    break;
                case "MonsterHunter":
                    MonsterHunter.openMonsterHunterMenu(player);
                    break;
                case "Close":
                    player.closeInventory();
                    break;
            }
            event.setCancelled(true);
    }
}
