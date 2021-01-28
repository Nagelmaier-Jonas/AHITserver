package at.ahit.server.listeners;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        firstJoin(Main.getConfigFile(),player,event);
        Scoreboards.createScoreboard(Main.getConfigFile(),player);
        customjoinmessage(event);
        event.setJoinMessage("");
    }

    public static void firstJoin(FileConfiguration config, Player player,PlayerJoinEvent event){
        String displayName = player.getDisplayName();
        if (config.get(displayName + "_HasPlayedBefore") == null || !(boolean) config.get(displayName + "_HasPlayedBefore")){
            config.set(displayName + "_HasPlayedBefore", true);
            config.set(displayName + "_Amount", 0);
            config.set(displayName + "_Overlay", true);
            config.set(displayName + "_MinerXp", 0);
            config.set(displayName + "_MinerLevel", 1);
        }
        config.set(displayName + "_MinerAbiliti", false);
        Main.getPlugin().saveConfig();
    }

    public void customjoinmessage(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.getDisplayName().equals(new String("Symo_TMS"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The " + ChatColor.RED + "G" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "Y" + ChatColor.GREEN + "L"
                    + ChatColor.BLUE + "O" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.RED + "D " + ChatColor.GRAY + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("Joni04"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The " + ChatColor.RED +       "WILD " +    ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("Katoka47"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "Dome dick, aber Domes dick dünn. Ach ja, " + ChatColor.DARK_BLUE + "Niki " + ChatColor.GRAY + "ist da!" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("Gaduso11"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GOLD +       "ONE " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("guzms"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GREEN +       "SEW-GUY " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }


    }

}
