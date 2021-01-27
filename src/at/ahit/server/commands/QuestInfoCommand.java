package at.ahit.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("Quests are orders for your individual professions,\n" +
                " the higher level you have on a profession the harder quests you get.\n" +
                " You get xp from quests for your profession and sometimes additional coins.");

        return false;
    }

}
