package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Hunter implements Listener {

    @EventHandler
    public void killAnimal(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            int level = (int) Main.Load(player.getDisplayName() + "_HunterLevel");
            int playerXp = (int) Main.Load(player.getDisplayName() + "_HunterXp");

            if (event.getEntity().isDead()) {
                switch (event.getEntityType()) {
                    case PIG:
                        playerXp += 10;
                        break;
                    case COW:
                        playerXp += 10;
                        break;
                    case SHEEP:
                        playerXp += 10;
                        break;
                    case CHICKEN:
                        playerXp += 5;
                        break;
                    case SQUID:
                        playerXp += 5;
                        break;
                    case BAT:
                        playerXp += 15;
                        break;
                    case MUSHROOM_COW:
                        playerXp += 30;
                        break;
                    case RABBIT:
                        playerXp += 10;
                        break;
                    case POLAR_BEAR:
                        playerXp += 30;
                        break;
                    case TURTLE:
                        playerXp += 20;
                        break;
                    case DOLPHIN:
                        playerXp += -10;
                        player.sendMessage("Wer tötet einen Delphin! Du sau");
                        break;
                    case PANDA:
                        playerXp += 20;
                        break;
                    case FOX:
                        playerXp += 30;
                        break;
                    case HORSE:
                        playerXp += 30;
                        break;
                    case DONKEY:
                        playerXp += 40;
                        break;
                    case MULE:
                        playerXp += 40;
                        break;
                    case LLAMA:
                        playerXp += 30;
                        break;
                    default:
                        player.sendMessage("kein Tier");
                }
                if(100 * level <= playerXp) {
                    player.sendMessage("You are now hunter level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
                    Main.getConfigFile().set(player.getDisplayName() + "_HunterLevel", level);
                    Main.getConfigFile().set(player.getDisplayName() + "_HunterXp", 0);
                }else{
                    Main.getConfigFile().set(player.getDisplayName() + "_HunterXp", playerXp);
                }
                Main.getPlugin().saveConfig();
            }
        }
    }

    public static void openHunterMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "Hunter");

        ItemStack skill1 = new ItemStack(Material.CHICKEN,1);
        ItemMeta skill1Meta = skill1.getItemMeta();
        skill1Meta.setDisplayName("Skill1");
        ArrayList<String> skill1Lore = new ArrayList<String>();
        skill1Lore.add("Skill1");
        skill1Lore.add("Costs: 1000c");
        skill1Meta.setLore(skill1Lore);
        skill1.setItemMeta(skill1Meta);

        ItemStack skill2 = new ItemStack(Material.PORKCHOP,1);
        ItemMeta skill2Meta = skill2.getItemMeta();
        skill2Meta.setDisplayName("Skill2");
        ArrayList<String> skill2Lore = new ArrayList<String>();
        skill2Lore.add("Skill2");
        skill2Lore.add("Costs: 1000c");
        skill2Meta.setLore(skill2Lore);
        skill2.setItemMeta(skill2Meta);

        ItemStack skill3 = new ItemStack(Material.BEEF,1);
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

    public static void onHunterJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack.getType() != Material.AIR){
            String name = itemStack.getItemMeta().getDisplayName();

            switch (name){
                case "Skill1":
                    player.sendMessage("obtained skill1");
                    break;
                case "Skill2":
                    player.sendMessage("obtained skill2");
                    break;
                case "Skill3":
                    player.sendMessage("obtained skill3");
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
