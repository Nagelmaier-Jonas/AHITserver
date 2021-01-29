package at.ahit.server.commands;

import at.ahit.server.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetValCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2)
            Main.Save(strings[0], (Object) strings[1]); // TODO: Ohne ANFÜHRUNGSZEICHEN speichern!!!

        return false;
    }

}
