package at.ahit.server.overlays;

import at.ahit.server.jobs.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Menu {

    //TODO: Set cursor position

    public static void openMenu(Player player) {
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
                case "Wizard":
                    MonsterHunter.openMonsterHunterMenu(player);
                    break;
                case "Close":
                    player.closeInventory();
                    break;
            }
            event.setCancelled(true);
    }
}
