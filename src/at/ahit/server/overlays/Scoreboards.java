package at.ahit.server.overlays;

import at.ahit.server.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Objects;

public class Scoreboards {

    public static void createScoreboard(FileConfiguration config, Player player){
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Scoreboard","dummy","§6§l>>Info<<");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score1 = objective.getScore(ChatColor.BLUE + "");
        score1.setScore(5);
        Score score2 = objective.getScore(ChatColor.BLUE + "Player Name: " + ChatColor.AQUA + player.getDisplayName());
        score2.setScore(4);
        Score score3 = objective.getScore(ChatColor.BLUE + "Online Players: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size());
        score3.setScore(3);
        Score score4 = objective.getScore(ChatColor.BLUE + "Coins: " + ChatColor.AQUA + config.get(player.getDisplayName() + "_Amount"));
        score4.setScore(2);
        Score score5 = objective.getScore(ChatColor.BLUE + "Latest Job: " + Main.Load(player.getDisplayName() +"_LatestJob") + ":" + Main.Load(player.getDisplayName() + "_" + Main.Load(player.getDisplayName()) + "_LatestJob") + "Level");
        score5.setScore(2);
        Score score6 = objective.getScore(ChatColor.RED + "");
        score6.setScore(1);
        player.setScoreboard(scoreboard);
    }

}
