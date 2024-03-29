package at.ahit.server.villagerShop;

import at.ahit.server.main.Main;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class setVillagerShop implements Listener {

    @EventHandler
    public void checkForVillager(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        ItemMeta im = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();

        if (villagerShop.isVillagerStick(item)){
            event.setCancelled(true);

            NamespacedKey key = new NamespacedKey(Main.getPlugin(), "villager-shop-id");
            int ID = im.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);

            Location l = event.getClickedBlock().getLocation();
            Location loc = new Location(l.getWorld(), l.getX() + 0.5, l.getY() + 1, l.getZ() + 0.5);
            Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
            // villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 500));

            //
            // villager.getAttribute(Attribute.)
            //villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(100);

            villager.setCollidable(false);
            villager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);

            // TODO SET SPEED 0

            PersistentDataContainer container = villager.getPersistentDataContainer();
            key = new NamespacedKey(Main.getPlugin(), "shop-type");
            container.set(key, PersistentDataType.BYTE, (byte) ID); // TODO Change byte to int

            /*ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "summon villager " + loc.getX() + " " + loc.getY() + " " + loc.getZ() + " {Team:shop,VillagerData:{profession:nitwit,level:1,type:snow},Invulnerable:1,PersistenceRequired:1,Silent:1,ActiveEffects:[{Id:2,Amplifier:10,Duration:999999}]}";
            Bukkit.dispatchCommand(console, command);*/

            event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        }


    }

    @EventHandler
    public void entity(EntityDamageEvent event) {
        if (event.getEntity().getPersistentDataContainer() != null) {
            Entity e = event.getEntity();

            NamespacedKey key = new NamespacedKey(Main.getPlugin(), "shop-type");
            if (e.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {


                if (event instanceof EntityDamageByEntityEvent)
                    if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player) {

                        Player p = ((Player) ((EntityDamageByEntityEvent) event).getDamager());
                        Vector v1 = e.getLocation().toVector();
                        Vector v2 = p.getEyeLocation().toVector();

                        Vector v = v2.subtract(v1);

                        if (villagerShop.isVillagerStick(p.getInventory().getItemInMainHand())) {
                            event.getEntity().remove();

                            key = new NamespacedKey(Main.getPlugin(), "villager-shop-id");
                            int ID = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER);

                            ((Player) ((EntityDamageByEntityEvent) event).getDamager()).getInventory().remove(villagerShop.villagerStick(ID));
                                return;
                            }

                        if (p.getInventory().getChestplate() != null) {
                            if (p.getInventory().getChestplate().getType() == Material.ELYTRA)
                                p.setVelocity(p.getVelocity().add(v.normalize().multiply(20)));
                            else
                                p.setVelocity(p.getVelocity().add(v.normalize().multiply(0.5)));
                        } else
                            p.setVelocity(p.getVelocity().add(v.normalize().multiply(0.5)));

                        p.sendMessage("You shall NOT!");
                    }

                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void openVillagerEvent(PlayerInteractAtEntityEvent event) { // TODO if villager has profession the shop doesn't open
        if (event.getRightClicked() != null)
            if (event.getRightClicked().getPersistentDataContainer() != null) {
                Entity e = event.getRightClicked();

                NamespacedKey key = new NamespacedKey(Main.getPlugin(), "shop-type");

                if (e.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                    byte type = e.getPersistentDataContainer().get(key, PersistentDataType.BYTE);

                    switch (type)
                    {
                        case 0:
                            ShopEngine.getShop("server-shop").openShopGUI(event.getPlayer(), 0);
                            // event.getPlayer().sendMessage("I opened the inventory yey.");
                            break;
                        case 1:
                            ShopEngine.getShop("random-shop").openShopGUI(event.getPlayer(), 0);
                            // event.getPlayer().sendMessage("I opened the inventory yey.");
                        break;
                    }
                }
            }
    }

}
