package at.ahit.server.commands;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (strings.length == 1){
                switch (strings[0]){
                    case "on":
                        Main.getConfigFile().set(player.getDisplayName() + "_Scoreboard",true);
                        Main.getPlugin().saveConfig();
                        Scoreboards.createScoreboard(Main.getConfigFile(),player);
                        break;
                    case "off":
                        Main.getConfigFile().set(player.getDisplayName() + "_Scoreboard",false);
                        Main.getPlugin().saveConfig();
                        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard());
                        break;
                    default:
                        player.sendMessage("§7Bitte benutze §c/stats §2<Option> §b{on,off}");
                }
            }else{
                player.sendMessage("§7Bitte benutze §c/stats §2<Option> §b {on,off}");
            }
        }else{
            commandSender.sendMessage("§4Du hast keine Berechtigung dafür");
        }
        return false;
    }
}
