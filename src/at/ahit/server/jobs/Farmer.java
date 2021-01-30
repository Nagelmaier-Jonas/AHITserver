package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Farmer implements Listener {

    //@EventHandler TODO: KOMMENTAR WEIL BUGFIX
    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int level = (int) Main.Load(player.getDisplayName() + "_FarmerLevel");
        int playerXp = (int) Main.Load(player.getDisplayName() + "_FarmerXp");

        ArrayList<Material> Crops = new ArrayList<>();
        Crops.add(Material.CARROTS);
        Crops.add(Material.WHEAT_SEEDS);
        Crops.add(Material.BEETROOT_SEEDS);
        Crops.add(Material.COCOA_BEANS);
        Crops.add(Material.NETHER_WART);
        Crops.add(Material.POTATOES);

        //TODO:Scoreboard reload besser diese

        if (Crops.contains(event.getBlock().getType())) {
            if (isFullyGrownOld(event.getBlock())) {
                switch (event.getBlock().getType()) {
                    case WHEAT_SEEDS:
                        playerXp += 1;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Farmer");
                        Scoreboards.createScoreboard(Main.getConfigFile(), event.getPlayer());
                        break;
                    case CARROTS:
                    case BEETROOT_SEEDS:
                    case POTATOES:
                        playerXp += 5;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Farmer");
                        Scoreboards.createScoreboard(Main.getConfigFile(), event.getPlayer());
                        break;
                    case COCOA_BEANS:
                    case NETHER_WART:
                        playerXp += 10;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Farmer");
                        Scoreboards.createScoreboard(Main.getConfigFile(), event.getPlayer());
                        break;
                }
            }
        }
        if (event.getBlock().getType().equals(Material.MELON) && !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)){
            playerXp += 10;
            Main.Save(player.getDisplayName() + "_LatestJob", "Farmer");
            Scoreboards.createScoreboard(Main.getConfigFile(), event.getPlayer());
        }
        if (event.getBlock().getType().equals(Material.PUMPKIN)){
            playerXp += 10;
            Main.Save(player.getDisplayName() + "_LatestJob", "Farmer");
        }
        if (100 * level <= playerXp) {
            event.getPlayer().sendMessage("You are now farming level " + ChatColor.AQUA + ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_FarmerLevel", level);
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_FarmerXp", 0);
        } else {
            Main.getConfigFile().set(event.getPlayer().getDisplayName() + "_FarmerXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }

    public void CropMaster(Player player, Block block){
        if ((boolean)Main.Load(player.getDisplayName() + "FarmerAbility1")){
            Material material = block.getType();
            block.getDrops().add(new ItemStack(material));
        }
    }

    public boolean isFullyGrownOld(Block block) {
        MaterialData md = block.getState().getData();
        if(md instanceof Crops) {
            return (((Crops) md).getState() == CropState.RIPE);
        }
        else return false;
    }

    public static void openFarmerMenu(Player player){

        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player, Material.STONE_HOE, 1, "CropMaster", new ArrayList<>(
                Arrays.asList("You will get more crops", "Costs: 5000c")), "Farmer", 1));
        items.add(SkillMenu.createItem(player, Material.IRON_HOE, 1, "FarmerWife", new ArrayList<>(Arrays.asList(
                "You have a chance to cook your crops instantly", "Costs: 10000c")), "Farmer", 2));
        items.add(SkillMenu.createItem(player, Material.DIAMOND_HOE, 1, "Recycler", new ArrayList<>(Arrays.asList(
                "Place crops in 3x3 Fields", "Costs: 25000c")), "Farmer", 3));
        items.add(SkillMenu.createItem(Material.BARRIER, 1, "Close"));

        Inventory inventory = SkillMenu.createSkillInventory(player, "Farmer", new HashMap<Integer, ItemStack>() {{
            put(1, items.get(0));
            put(3, items.get(1));
            put(5, items.get(2));
            put(8, items.get(3));
        }});
        player.openInventory(inventory);
    }

    public static void onFarmerJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();


        switch (name) {
            case "CropMaster":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_FarmerSkill1"))) {
                    Main.Save(player.getDisplayName() + "_FarmerSkill1", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 2500);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                    Farmer.openFarmerMenu(player);
                } else {
                    Farmer.openFarmerMenu(player);
                    player.sendMessage("You can't buy that you little motherfucker");
                }
                break;
            case "FarmerWife":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_FarmerSkill2"))) {
                    Main.Save(player.getDisplayName() + "_FarmerSkill2", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 1000);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                    Farmer.openFarmerMenu(player);
                } else {
                    Farmer.openFarmerMenu(player);
                    player.sendMessage("You can't buy that you little motherfucker");
                }
                break;
            case "Recycler":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_FarmerSkill3"))) {
                    Main.Save(player.getDisplayName() + "_FarmerSkill3", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                    Farmer.openFarmerMenu(player);
                } else {
                    Farmer.openFarmerMenu(player);
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
