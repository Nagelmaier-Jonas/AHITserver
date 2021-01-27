package at.ahit.server.overlays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class Scoreboard {
    public static void createScoreboard(FileConfiguration config, Player player){
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
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
}
