package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class MonsterHunter {

    public static void openMonsterHunterMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player,Material.STRING,1,"Damage+",new ArrayList<>(Arrays.asList("Your dealt damage is increased","Costs: 5000c")),"MonsterHunter",1));
        items.add(SkillMenu.createItem(player,Material.ROTTEN_FLESH,1,"Defense+",new ArrayList<>(Arrays.asList("Your damage taken will be reduced","Costs: 10000c")),"MonsterHunter",2));
        items.add(SkillMenu.createItem(player,Material.BONE,1,"HeadHunter",new ArrayList<>(Arrays.asList("Monster heads drop more often","Costs: 25000c")),"MonsterHunter",3));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        Inventory inventory = SkillMenu.createSkillInventory(player,"MonsterHunter",new HashMap<Integer,ItemStack>(){{put(1,items.get(0));put(3,items.get(1));put(5,items.get(2));put(8,items.get(3));}});
        player.openInventory(inventory);
    }

    public static void onMonsterHunterJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        assert itemStack != null;
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
                    Menu.openMenu(player);
                    break;
        }
        event.setCancelled(true);
    }
}