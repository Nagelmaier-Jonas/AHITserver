package at.ahit.server.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class JoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        //event.setJoinMessage("§aHallo§4 " + player.getDisplayName());
        event.setJoinMessage("");
        customjoinmessage(event);
    }

    public static void firstJoin(FileConfiguration config, Player player,PlayerJoinEvent event){

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
            Bukkit.broadcastMessage(ChatColor.GRAY + "The " + ChatColor.DARK_BLUE + "BIRDKING " + ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("Gaduso11"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GOLD +       "ONE " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("guzms"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GREEN +       "SEW-GUY " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }


    }


}
