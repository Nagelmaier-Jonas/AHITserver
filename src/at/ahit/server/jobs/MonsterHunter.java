package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Menu;
import at.ahit.server.overlays.Scoreboards;
import at.ahit.server.overlays.SkillMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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

public class MonsterHunter implements Listener {

    //@EventHandler
   //public void CheckMonsterXP()

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

        items.add(SkillMenu.createItem(player,Material.STRING,1,"Damage+",new ArrayList<>(Arrays.asList("Your dealt damage is increased","Cost: 5000 Coins")),"MonsterHunter",1));
        items.add(SkillMenu.createItem(player,Material.ROTTEN_FLESH,1,"Defense+",new ArrayList<>(Arrays.asList("Your damage taken will be reduced","Cost: 10000 Coins")),"MonsterHunter",2));
        items.add(SkillMenu.createItem(player,Material.BONE,1,"HeadHunter",new ArrayList<>(Arrays.asList("Monster heads drop more often","Cost: 25000 Coins")),"MonsterHunter",3));
        items.add(SkillMenu.createItem(Material.BARRIER,1,"Close"));

        ItemMeta meta = items.get(0).getItemMeta();
        ItemMeta meta1 = items.get(1).getItemMeta();
        ItemMeta meta2 = items.get(2).getItemMeta();

        if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility1")) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(0).setItemMeta(meta);
            RemoveEnchantmentLore(items.get(0));
        }
        else items.get(0).setItemMeta(meta);

        if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility2")) {
            meta1.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(1).setItemMeta(meta1);
            RemoveEnchantmentLore(items.get(1));
        }
        else items.get(1).setItemMeta(meta1);

        if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility3")) {
            meta2.addEnchant(Enchantment.DURABILITY, 1, true);
            items.get(2).setItemMeta(meta2);
            RemoveEnchantmentLore(items.get(2));
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
            case "Damage+":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 5000 && !((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill1")) && (int) Main.Load(player.getDisplayName() + "_MonsterHunterLevel") <= 3) {
                    Main.Save(player.getDisplayName() + "_MonsterHunterSkill1", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 5000);
                    player.sendMessage("You obtained a new Skill:" + ChatColor.DARK_RED + " " + name + ChatColor.RESET);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill1")) {
                    Main.Save(player.getDisplayName() + "_MonsterHunterAbility1", !(boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility1"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "5000 Coins" + ChatColor.RESET + " and " + ChatColor.DARK_RED + "MonsterHunter Level 3" + ChatColor.RESET);
                }
                MonsterHunter.openMonsterHunterMenu(player);
                break;
            case "Defense+":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 10000 && !((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill2")) && (int) Main.Load(player.getDisplayName() + "_MonsterHunterLevel") <= 5) {
                    Main.Save(player.getDisplayName() + "_MonsterHunterSkill2", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 10000);
                    player.sendMessage("You obtained a new Skill:" + ChatColor.DARK_RED + " " + name + ChatColor.RESET);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill2")) {
                    Main.Save(player.getDisplayName() + "_MonsterHunterAbility2", !(boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility2"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "10000 Coins" + ChatColor.RESET + " and " + ChatColor.DARK_RED + "MonsterHunter Level 5" + ChatColor.RESET);
                }
                MonsterHunter.openMonsterHunterMenu(player);
                break;
            case "HeadHunter":
                if ((int) Main.Load(player.getDisplayName() + "_Amount") >= 25000 && !((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill3")) && (int) Main.Load(player.getDisplayName() + "_MonsterHunterLevel") <= 9) {
                    Main.Save(player.getDisplayName() + "_MonsterHunterSkill3", true);
                    Main.Save(player.getDisplayName() + "_Amount", (int) Main.Load(player.getDisplayName() + "_Amount") - 25000);
                    player.sendMessage("You obtained a new Skill:" + ChatColor.DARK_RED + " " + name + ChatColor.RESET);
                    Scoreboards.createScoreboard(Main.getConfigFile(), player);
                } else if ((boolean) Main.Load(player.getDisplayName() + "_MonsterHunterSkill3")) {
                    Main.Save(player.getDisplayName() + "_MonsterHunterAbility3", !(boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility3"));
                }
                else{
                    player.sendMessage("You need " + ChatColor.GOLD + "25000 Coins" + ChatColor.RESET + " and " + ChatColor.DARK_RED + "MonsterHunter Level 9" + ChatColor.RESET);
                }
                MonsterHunter.openMonsterHunterMenu(player);
                break;
            case "Close":
                Menu.openMenu(player);
                break;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void DamageModifications(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player) {
            Entity ent = event.getEntity();
            Player player = (Player) event.getDamager();
            if (!(NonMonsters().contains(ent)) && (boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility1")) {
                event.setDamage(event.getDamage() * 1.5);
            }
        }
        if(event.getEntity() instanceof Player) {
            Entity ent = event.getDamager();
            Player player = (Player) event.getEntity();
            if (!(NonMonsters().contains(ent)) && (boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility2")) {
                event.setDamage(event.getDamage() * 0.8);
            }
        }
    }

    @EventHandler
    public void HeadDropChance(EntityDeathEvent event){
        if(!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent)) return;
        if(event.getEntity().getKiller() == null) return;
        Entity ent = event.getEntity();
        Player player = event.getEntity().getKiller();
        Random random = new Random();

        if(!(NonMonsters().contains(ent)) && (boolean) Main.Load(player.getDisplayName() + "_MonsterHunterAbility3")){
            if(random.nextInt(100) < 3){
                switch (ent.getType()){
                    case ZOMBIE:
                        event.getDrops().add(new ItemStack(Material.ZOMBIE_HEAD, 1,(short) 0,null));
                        break;
                    case SKELETON:
                        event.getDrops().add(new ItemStack(Material.SKELETON_SKULL, 1,(short) 0,null));
                        break;
                    case CREEPER:
                        event.getDrops().add(new ItemStack(Material.CREEPER_HEAD, 1,(short) 0,null));
                        break;
                    case WITHER_SKELETON:
                        event.getDrops().add(new ItemStack(Material.WITHER_SKELETON_SKULL, 1,(short) 0,null));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}