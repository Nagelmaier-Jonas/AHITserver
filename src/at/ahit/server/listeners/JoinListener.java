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

    public static void createScoreboard(FileConfiguration config,Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Scoreboard","dummy","§6§l>>Info<<");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = objective.getScore(ChatColor.DARK_RED + "-=-=-=-=-=-=-=-");
        score1.setScore(6);
        Score score2 = objective.getScore(ChatColor.BLUE + "");
        score2.setScore(5);
        Score score3 = objective.getScore(ChatColor.BLUE + "Player Name: " + ChatColor.AQUA + player.getDisplayName());
        score3.setScore(4);
        Score score4 = objective.getScore(ChatColor.BLUE + "Online Players: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size());
        score4.setScore(3);
        Score score5 = objective.getScore(ChatColor.BLUE + "Coins: " + ChatColor.AQUA + config.get(player.getDisplayName() + "_Coins"));
        score5.setScore(2);
        Score score6 = objective.getScore(ChatColor.RED + "");
        score6.setScore(1);
        Score score7 = objective.getScore(ChatColor.DARK_RED + "-=-=-=-=-=-=-=- ");
        score7.setScore(0);
        player.setScoreboard(scoreboard);
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
            Bukkit.broadcastMessage(ChatColor.GRAY + "The " + ChatColor.DARK_BLUE + "BIRDKING" + ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals(new String("Gaduso11"))){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GOLD +       "ONE" +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }


    }


}
