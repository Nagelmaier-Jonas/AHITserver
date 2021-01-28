package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Lumberjack implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = (Player) event.getPlayer();
        int level = (int) Main.getConfigFile().get(player.getDisplayName() + "_LumberjackLevel");
        int playerXp = (int)Main.getConfigFile().get(player.getDisplayName() + "_LumberjackXp");

        switch (event.getBlock().getType()){
            case OAK_WOOD:
                playerXp += 5;
                break;
            case DARK_OAK_WOOD:
                playerXp += 7;
                break;
            case BIRCH_WOOD:
                playerXp += 7;
                break;
            case JUNGLE_WOOD:
                playerXp += 3;
                break;
            case SPRUCE_WOOD:
                playerXp += 5;
                break;
            case ACACIA_WOOD:
                playerXp += 7;
                break;
            case CRIMSON_STEM:
                playerXp += 10;
                break;
            case WARPED_STEM:
                playerXp += 10;
                break;
            default:
                player.sendMessage("ka Hoiz");
        }
        if(100 * level <= playerXp) {
            player.sendMessage("You are now lumberjack level " + ChatColor.AQUA +  ++level + ChatColor.RESET + "!");
            Main.getConfigFile().set(player.getDisplayName() + "_LumberjackLevel", level);
            Main.getConfigFile().set(player.getDisplayName() + "_LumberjackXp", 0);
        }else{
            Main.getConfigFile().set(player.getDisplayName() + "_LumberjackXp", playerXp);
        }
        Main.getPlugin().saveConfig();
    }
}
