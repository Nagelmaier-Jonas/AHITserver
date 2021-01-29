package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class MonsterHunter {

    public static void openMonsterHunterMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "MonsterHunter");

        ItemStack skill1 = new ItemStack(Material.STRING,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        assert skill1Meta != null;
        skill1Meta.setDisplayName("Damage+");
        ArrayList<String> skill1Lore = new ArrayList<>();
        skill1Lore.add("Your dealt damage is increased");
        skill1Lore.add("Costs: 5000c");
        if (!(boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill1")) {
            skill1Lore.add(ChatColor.RED + "Skill not acquired");
        } else {
            skill1Lore.add(ChatColor.GREEN + "Skill acquired");
        }
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.ROTTEN_FLESH,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        assert skill2Meta != null;
        skill2Meta.setDisplayName("Defense+");
        ArrayList<String> skill2Lore = new ArrayList<>();
        skill2Lore.add("Your damage taken will be reduced");
        skill2Lore.add("Costs: 10000c");
        if (!(boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill2")) {
            skill2Lore.add(ChatColor.RED + "Skill not acquired");
        } else {
            skill2Lore.add(ChatColor.GREEN + "Skill acquired");
        }
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.BONE,1);
        ItemMeta skill3Meta = skill3.getItemMeta();
        assert skill3Meta != null;
        skill3Meta.setDisplayName("HeadHunter");
        ArrayList<String> skill3Lore = new ArrayList<>();
        skill3Lore.add("Monster heads drop more often");
        skill3Lore.add("Costs: 25000c");
        if (!(boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill3")) {
            skill3Lore.add(ChatColor.RED + "Skill not acquired");
        } else {
            skill3Lore.add(ChatColor.GREEN + "Skill acquired");
        }
        skill3Meta.setLore(skill3Lore);
        skill3.setItemMeta(skill3Meta);

        ItemStack close = new ItemStack(Material.BARRIER,1);
        ItemMeta closeMeta = close.getItemMeta();
        assert closeMeta != null;
        closeMeta.setDisplayName("Close");
        close.setItemMeta(closeMeta);

        inventory.setItem(1,skill1);
        inventory.setItem(3,skill2);
        inventory.setItem(5,skill3);
        inventory.setItem(8,close);

        player.openInventory(inventory);
    }

    public static void onMonsterHunterJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        assert itemStack != null;
        if (itemStack.getType() != Material.AIR) {
            String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();

            switch (name) {
                case "Damage+":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill1"))) {
                        Main.Save(player.getDisplayName() + "_MonsterHunterSkill1", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 2500);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                        player.closeInventory();
                        MonsterHunter.openMonsterHunterMenu(player);
                    } else {
                        MonsterHunter.openMonsterHunterMenu(player);
                        player.sendMessage("You can't buy that you little motherfucker");
                    }
                    break;
                case "Defense+":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill2"))) {
                        Main.Save(player.getDisplayName() + "_MonsterHunterSkill2", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 1000);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                        player.closeInventory();
                        MonsterHunter.openMonsterHunterMenu(player);
                    } else {
                        MonsterHunter.openMonsterHunterMenu(player);
                        player.sendMessage("You can't buy that you little motherfucker");
                    }
                    break;
                case "HeadHunter":
                    if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill3"))) {
                        Main.Save(player.getDisplayName() + "_MonsterHunterSkill3", true);
                        Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                        Scoreboards.createScoreboard(Main.getConfigFile(), player);
                        player.closeInventory();
                        MonsterHunter.openMonsterHunterMenu(player);
                    } else {
                        MonsterHunter.openMonsterHunterMenu(player);
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
}
