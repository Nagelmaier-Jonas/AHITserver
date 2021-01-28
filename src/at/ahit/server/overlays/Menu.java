package at.ahit.server.overlays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;

public class Menu {

    public static void openMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Job");

        ItemStack miner = new ItemStack(Material.IRON_PICKAXE,1);
        ItemMeta minerMeta = miner.getItemMeta();
        minerMeta.setDisplayName("Miner");
        ArrayList<String> minerLore = new ArrayList<String>();
        minerLore.add("Miner-Job");
        minerMeta.setLore(minerLore);
        miner.setItemMeta(minerMeta);

        ItemStack cook = new ItemStack(Material.FURNACE,1);
        ItemMeta cookMeta = cook.getItemMeta();
        cookMeta.setDisplayName("Cook");
        ArrayList<String> cookLore = new ArrayList<String>();
        cookLore.add("Cook-Job");
        cookMeta.setLore(cookLore);
        cook.setItemMeta(cookMeta);

        ItemStack farmer = new ItemStack(Material.WHEAT,1);
        ItemMeta farmerMeta = farmer.getItemMeta();
        farmerMeta.setDisplayName("Farmer");
        ArrayList<String> farmerLore = new ArrayList<String>();
        farmerLore.add("Farmer-Job");
        farmerMeta.setLore(farmerLore);
        farmer.setItemMeta(farmerMeta);

        ItemStack hunter = new ItemStack(Material.PORKCHOP,1);
        ItemMeta hunterMeta = hunter.getItemMeta();
        hunterMeta.setDisplayName("Hunter");
        ArrayList<String> hunterLore = new ArrayList<String>();
        hunterLore.add("Hunter-Job");
        hunterMeta.setLore(hunterLore);
        hunter.setItemMeta(hunterMeta);

        ItemStack lumberjack = new ItemStack(Material.IRON_AXE,1);
        ItemMeta lumberjackMeta = lumberjack.getItemMeta();
        lumberjackMeta.setDisplayName("Lumberjack");
        ArrayList<String> lumberjackLore = new ArrayList<String>();
        lumberjackLore.add("Lumberjack-Job");
        lumberjackMeta.setLore(lumberjackLore);
        lumberjack.setItemMeta(lumberjackMeta);

        ItemStack monsterHunter = new ItemStack(Material.BOW,1);
        ItemMeta monsterHunterMeta = monsterHunter.getItemMeta();
        monsterHunterMeta.setDisplayName("MonsterHunter");
        ArrayList<String> monsterHunterLore = new ArrayList<String>();
        monsterHunterLore.add("MonsterHunter-Job");
        monsterHunterMeta.setLore(monsterHunterLore);
        monsterHunter.setItemMeta(monsterHunterMeta);

        ItemStack close = new ItemStack(Material.BARRIER,1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(0,miner);
        inventory.setItem(1,cook);
        inventory.setItem(2,farmer);
        inventory.setItem(3,hunter);
        inventory.setItem(4,lumberjack);
        inventory.setItem(5,monsterHunter);

        inventory.setItem(8,close);

        player.openInventory(inventory);
    }

    public static void onJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR){
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name){
                case "Miner":
                    player.closeInventory();
                    openMinerMenu(player);
                    break;
                case "Cook":
                    player.sendMessage("Cook");
                    break;
                case "Farmer":
                    player.sendMessage("Farmer");
                    break;
                case "Hunter":
                    player.sendMessage("Hunter");
                    break;
                case "Lumberjack":
                    player.sendMessage("Lumberjack");
                    break;
                case "MonsterHunter":
                    player.sendMessage("MonsterHunter");
                    break;
                case "Close":
                    player.closeInventory();
                    break;
            }

            event.setCancelled(true);
        }
    }

    public static void openMinerMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "Miner");

        ItemStack skill1 = new ItemStack(Material.BOOK,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Skill1");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Skill1");
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.BOOK,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Skill2");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Skill2");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.BOOK,1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        skill3Meta.setDisplayName("skill3");
        ArrayList<String> skill3Lore = new ArrayList<String>();
        skill3Lore.add("skill3");
        skill3Meta.setLore(skill3Lore);
        skill3.setItemMeta(skill3Meta);

        ItemStack close = new ItemStack(Material.BARRIER,1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(0,skill1);
        inventory.setItem(0,skill2);
        inventory.setItem(0,skill3);
        inventory.setItem(8,close);

        player.openInventory(inventory);
    }

    public static void onMinerJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR){
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name){
                case "Skill1":
                    player.sendMessage("obtsined skill1");
                    itemStack.setData(new MaterialData(Material.ENCHANTED_BOOK));
                    break;
                case "Skill2":
                    player.sendMessage("obtsined skill2");
                    break;
                case "Skill3":
                    player.sendMessage("obtsined skill3");
                    break;
            }

            event.setCancelled(true);
        }
    }

}
