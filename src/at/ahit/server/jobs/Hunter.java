package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static at.ahit.server.jobs.Lumberjack.RemoveEnchantmentLore;

public class Hunter implements Listener {

    @EventHandler
    public void checkAnimalXP(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            int level = (int) Main.Load(player.getDisplayName() + "_HunterLevel");
            int playerXp = (int) Main.Load(player.getDisplayName() + "_HunterXp");

            if (event.getEntity().isDead()) {
                switch (event.getEntityType()) {
                    case PIG:
                    case COW:
                    case SHEEP:
                        playerXp += 10;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        break;
                    case CHICKEN:
                        playerXp += 5;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        break;
                    case SQUID:
                    case RABBIT:
                        playerXp += 15;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        break;
                    case BAT:
                    case TURTLE:
                    case PANDA:
                    case HORSE:
                        playerXp += 20;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        break;
                    case MUSHROOM_COW:
                    case POLAR_BEAR:
                    case LLAMA:
                        playerXp += 30;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        break;
                    case DOLPHIN:
                        playerXp += -10;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        player.sendMessage("Wer tötet einen Delphin! Du sau");
                        break;
                    case FOX:
                        playerXp += 30;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        player.sendMessage("What does the fox say? Isch hab ihm nichts getan!");
                        break;
                    case DONKEY:
                    case MULE:
                        playerXp += 40;
                        Main.Save(player.getDisplayName() + "_LatestJob", "Hunter");
                        break;
                    default:
                        break;
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

    public ArrayList<EntityType> Animals(){
        ArrayList<EntityType> animals = new ArrayList();
        animals.add(EntityType.PIG);
        animals.add(EntityType.COW);
        animals.add(EntityType.SHEEP);
        animals.add(EntityType.CHICKEN);
        animals.add(EntityType.SQUID);
        animals.add(EntityType.RABBIT);
        animals.add(EntityType.BAT);
        animals.add(EntityType.TURTLE);
        animals.add(EntityType.HORSE);
        animals.add(EntityType.PANDA);
        animals.add(EntityType.MUSHROOM_COW);
        animals.add(EntityType.POLAR_BEAR);
        animals.add(EntityType.LLAMA);
        animals.add(EntityType.DOLPHIN);
        animals.add(EntityType.FOX);
        animals.add(EntityType.DONKEY);
        animals.add(EntityType.MULE);
        animals.add(EntityType.COD);
        animals.add(EntityType.SALMON);
        animals.add(EntityType.TROPICAL_FISH);
        return animals;
    }

    @EventHandler
    public void ExtraAnimalDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Mob) return;
        Player player = (Player) event.getDamager();
        Entity ent = event.getEntity();

        if((boolean) Main.Load(player.getDisplayName() + "_HunterAbility1") && Animals().contains(event.getEntityType())){
            event.setDamage(event.getDamage() * 1.5);
        }
    }

    @EventHandler
    public void ExtraDrops(EntityDeathEvent event){
        if(!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent)) return;
        Entity ent = event.getEntity();
        Entity killer = event.getEntity().getKiller();
        if(killer == null) return;
        Player player = (Player) killer;
        Random random = new Random();
        if(Animals().contains(ent.getType()) && (boolean) Main.Load(player.getDisplayName() + "_HunterAbility2")){
            for (ItemStack is : event.getDrops()) {
                is.setAmount(is.getAmount() + 1);
            }
        }
        if(Animals().contains(ent.getType()) && (boolean) Main.Load(player.getDisplayName() + "_HunterAbility3")){
            for (ItemStack is : event.getDrops()) {
                for (int i = 0; i < is.getAmount(); i++) {
                    if(random.nextInt(6) > 3){
                        switch (is.getType()){
                            case PORKCHOP:
                                event.getDrops().add(new ItemStack(Material.COOKED_PORKCHOP, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            case CHICKEN:
                                event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            case BEEF:
                                event.getDrops().add(new ItemStack(Material.COOKED_BEEF, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            case MUTTON:
                                event.getDrops().add(new ItemStack(Material.COOKED_MUTTON, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            case COD:
                                event.getDrops().add(new ItemStack(Material.COOKED_COD, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            case SALMON:
                                event.getDrops().add(new ItemStack(Material.COOKED_SALMON, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            case RABBIT:
                                event.getDrops().add(new ItemStack(Material.COOKED_RABBIT, 1, (short) 0,null));
                                is.setAmount(is.getAmount() - 1);
                                break;
                            default:
                                break;
                        }

                    }
                }

            }
        }
    }

    public static void openHunterMenu(Player player){
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(SkillMenu.createItem(player, Material.CHICKEN, 1, "AnimalKiller", new ArrayList<>(Arrays.asList("Your dealt damage is increased", "Cost: 2500 Coins")), "Hunter", 1));
        items.add(SkillMenu.createItem(player, Material.PORKCHOP, 1, "Butcher", new ArrayList<>(Arrays.asList("Animals drop more items", "Cost: 10000 Coins")), "Hunter", 2));
        items.add(SkillMenu.createItem(player, Material.BEEF, 1, "Waifu", new ArrayList<>(Arrays.asList("Animals have the chance to drop cooked food", "Cost: 25000 Coins")), "Hunter", 3));
        items.add(SkillMenu.createItem(Material.BARRIER, 1, "Close"));

        ItemMeta meta = items.get(0).getItemMeta();
        ItemMeta meta1 = items.get(1).getItemMeta();
        ItemMeta meta2 = items.get(2).getItemMeta();

        if ((boolean) Main.Load(player.getDisplayName() + "_HunterAbility1")) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(0).setItemMeta(meta);
            RemoveEnchantmentLore(items.get(0));
        }
        else items.get(0).setItemMeta(meta);

        if ((boolean) Main.Load(player.getDisplayName() + "_HunterAbility2")) {
            meta1.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(1).setItemMeta(meta1);
            RemoveEnchantmentLore(items.get(1));
        }
        else items.get(1).setItemMeta(meta1);

        if ((boolean) Main.Load(player.getDisplayName() + "_HunterAbility3")) {
            meta2.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(2).setItemMeta(meta2);
            RemoveEnchantmentLore(items.get(2));
        }
        else items.get(2).setItemMeta(meta2);

        Inventory inventory = SkillMenu.createSkillInventory(player, "Hunter", new HashMap<Integer, ItemStack>() {{
            put(1, items.get(0));
            put(3, items.get(1));
            put(5, items.get(2));
            put(8, items.get(3));
        }});
        player.openInventory(inventory);
    }

    public static void onHunterJobsUse(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        assert itemStack != null;
        String name = Objects.requireNonNull(itemStack.getItemMeta().getDisplayName());

        switch (name) {
            case "AnimalKiller":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 2500 && !((boolean) Main.Load(player.getDisplayName() + "_HunterSkill1")) && (int) Main.Load(player.getDisplayName() + "_HunterLevel") <= 3) {
                    Main.Save(player.getDisplayName() + "_HunterSkill1", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 2500);
                    Main.Save(player.getDisplayName() + "_HunterAbility1", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility1"));
                    player.sendMessage("You obtained a new Skill:" + ChatColor.RED + " " + name + ChatColor.RESET);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_HunterSkill1")) {
                    Main.Save(player.getDisplayName() + "_HunterAbility1", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility1"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "2500 Coins" + ChatColor.RESET + " and " + ChatColor.RED + "Hunter Level 3" + ChatColor.RESET);
                }
                Hunter.openHunterMenu(player);
                break;
            case "Butcher":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_HunterSkill2")) && (int) Main.Load(player.getDisplayName() + "_HunterLevel") <= 5) {
                    Main.Save(player.getDisplayName() + "_HunterSkill2", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 10000);
                    Main.Save(player.getDisplayName() + "_HunterAbility2", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility2"));
                    player.sendMessage("You obtained a new Skill:" + ChatColor.RED + " " + name + ChatColor.RESET);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_HunterSkill2")) {
                    Main.Save(player.getDisplayName() + "_HunterAbility2", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility2"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "10000 Coins" + ChatColor.RESET + " and " + ChatColor.RED + "Hunter Level 5" + ChatColor.RESET);
                }
                Hunter.openHunterMenu(player);
                break;
            case "Waifu":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_HunterSkill3")) && (int) Main.Load(player.getDisplayName() + "_HunterLevel") <= 9) {
                    Main.Save(player.getDisplayName() + "_HunterSkill3", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                    Main.Save(player.getDisplayName() + "_HunterAbility3", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility3"));
                    player.sendMessage("You obtained a new Skill:" + ChatColor.RED + " " + name + ChatColor.RESET);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_HunterSkill3")) {
                    Main.Save(player.getDisplayName() + "_HunterAbility3", !(boolean) Main.Load(player.getDisplayName() + "_HunterAbility3"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "25000 Coins" + ChatColor.RESET + " and " + ChatColor.RED + "Hunter Level 9" + ChatColor.RESET);
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
