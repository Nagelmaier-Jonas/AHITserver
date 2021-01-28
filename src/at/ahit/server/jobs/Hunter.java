package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

public class Hunter implements Listener {

    @EventHandler
    public void killAnimal(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            int level = (int) Main.getConfigFile().get(player.getDisplayName() + "_HunterLevel");
            int playerXp = (int) Main.getConfigFile().get(player.getDisplayName() + "_HunterXp");

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
}
