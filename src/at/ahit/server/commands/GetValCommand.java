package at.ahit.server.commands;

import at.ahit.server.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetValCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1)
            ((Player) commandSender).sendMessage((String) Main.Load(strings[0]));

        return false;
    }
}
