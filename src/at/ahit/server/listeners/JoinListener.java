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
        event.setJoinMessage("§aHallo§4 " + player.getDisplayName());
        customjoinmessage(event);
    }

    public static void createScoreboard(FileConfiguration config,Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Scoreboard","dummy","§6§l>>Info<<");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore(ChatColor.DARK_RED + "-=-=-=-=-=-=-=- ");
        score.setScore(0);
        player.setScoreboard(scoreboard);
    }

    public static void firstJoin(FileConfiguration config, Player player,PlayerJoinEvent event){

    }

    public void customjoinmessage(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.getDisplayName() == "Symo_TMS"){
            Bukkit.getServer().broadcastMessage("a Gaylord has appeared");
        }
    }


}
