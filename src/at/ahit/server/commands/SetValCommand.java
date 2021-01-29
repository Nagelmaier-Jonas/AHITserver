package at.ahit.server.commands;

import at.ahit.server.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetValCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 2)
            Main.Save(strings[0], strings[1]); // TODO: Ohne ANFÜHRUNGSZEICHEN speichern!!!

        return false;
    }

}
