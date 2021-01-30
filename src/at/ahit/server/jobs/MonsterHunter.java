package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class MonsterHunter implements Listener {

    public ArrayList<EntityType> NonMonsters(){
        ArrayList<EntityType> nonMonsters = new ArrayList();
        nonMonsters.add(EntityType.PIG);
        nonMonsters.add(EntityType.COW);
        nonMonsters.add(EntityType.SHEEP);
        nonMonsters.add(EntityType.CHICKEN);
        nonMonsters.add(EntityType.SQUID);
        nonMonsters.add(EntityType.RABBIT);
        nonMonsters.add(EntityType.BAT);
        nonMonsters.add(EntityType.TURTLE);
        nonMonsters.add(EntityType.HORSE);
        nonMonsters.add(EntityType.PANDA);
        nonMonsters.add(EntityType.MUSHROOM_COW);
        nonMonsters.add(EntityType.POLAR_BEAR);
        nonMonsters.add(EntityType.LLAMA);
        nonMonsters.add(EntityType.DOLPHIN);
        nonMonsters.add(EntityType.FOX);
        nonMonsters.add(EntityType.DONKEY);
        nonMonsters.add(EntityType.MULE);
        nonMonsters.add(EntityType.COD);
        nonMonsters.add(EntityType.SALMON);
        nonMonsters.add(EntityType.TROPICAL_FISH);
        nonMonsters.add(EntityType.PLAYER);
        nonMonsters.add(EntityType.VILLAGER);
        nonMonsters.add(EntityType.WOLF);
        nonMonsters.add(EntityType.PARROT);
        nonMonsters.add(EntityType.OCELOT);
        nonMonsters.add(EntityType.CAT);
        nonMonsters.add(EntityType.BEE);
        nonMonsters.add(EntityType.PUFFERFISH);
        return nonMonsters;
    }

    public static void openMonsterHunterMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player,Material.STRING,1,"Damage+",new ArrayList<>(Arrays.asList("Your dealt damage is increased","Costs: 5000c")),"MonsterHunter",1));
        items.add(SkillMenu.createItem(player,Material.ROTTEN_FLESH,1,"Defense+",new ArrayList<>(Arrays.asList("Your damage taken will be reduced","Costs: 10000c")),"MonsterHunter",2));
        items.add(SkillMenu.createItem(player,Material.BONE,1,"HeadHunter",new ArrayList<>(Arrays.asList("Monster heads drop more often","Costs: 25000c")),"MonsterHunter",3));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        ItemMeta meta = items.get(0).getItemMeta();
        ItemMeta meta1 = items.get(1).getItemMeta();
        ItemMeta meta2 = items.get(2).getItemMeta();

        if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility1")) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(0).setItemMeta(meta);
        }
        else items.get(0).setItemMeta(meta);

        if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility2")) {
            meta1.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(1).setItemMeta(meta1);
        }
        else items.get(1).setItemMeta(meta1);

        if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility3")) {
            meta2.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(2).setItemMeta(meta2);
        }
        else items.get(2).setItemMeta(meta2);

        Inventory inventory = SkillMenu.createSkillInventory(player,"MonsterHunter",new HashMap<Integer,ItemStack>(){{put(1,items.get(0));put(3,items.get(1));put(5,items.get(2));put(8,items.get(3));}});
        player.openInventory(inventory);
    }

    public static void onMonsterHunterJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta().getDisplayName());

        switch (name) {
            case "AnimalKiller":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_HunterSkill1")) && (int) Main.Load(player.getDisplayName() + "_HunterLevel") <= 3) {
                    Main.Save(player.getDisplayName() + "_HunterSkill1", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 2500);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_HunterSkill1")) {
                    Main.Save(player.getDisplayName() + "_HunterAbility1", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility1"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "2500 Coins" + ChatColor.RESET + " and " + ChatColor.AQUA + "Hunter Level 3" + ChatColor.RESET);
                }
                Hunter.openHunterMenu(player);
                break;
            case "Butcher":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_HunterSkill2")) && (int) Main.Load(player.getDisplayName() + "_HunterLevel") <= 5) {
                    Main.Save(player.getDisplayName() + "_HunterSkill2", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 10000);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_HunterSkill2")) {
                    Main.Save(player.getDisplayName() + "_HunterAbility2", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility2"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "10000 Coins" + ChatColor.RESET + " and " + ChatColor.AQUA + "Hunter Level 5" + ChatColor.RESET);
                }
                Hunter.openHunterMenu(player);
                break;
            case "Waifu":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_HunterSkill3")) && (int) Main.Load(player.getDisplayName() + "_HunterLevel") <= 9) {
                    Main.Save(player.getDisplayName() + "_HunterSkill3", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_HunterSkill3")) {
                    Main.Save(player.getDisplayName() + "_HunterAbility3", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility3"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "25000 Coins" + ChatColor.RESET + " and " + ChatColor.AQUA + "Hunter Level 9" + ChatColor.RESET);
                }
                Hunter.openHunterMenu(player);
                break;
            case "Close":
                Menu.openMenu(player);
                break;
        }
        event.setCancelled(true);
    }
}