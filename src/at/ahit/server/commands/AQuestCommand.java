package at.ahit.server.commands;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AQuestCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (strings.length == 0){

            }else {
                player.sendMessage("Bitte nur /aquest benutzen");
            }
        }
        else {
            commandSender.sendMessage("Die Console hat keine Quests");
        }
        return false;
    }


}
