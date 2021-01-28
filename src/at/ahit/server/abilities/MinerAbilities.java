package at.ahit.server.abilities;

import at.ahit.server.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class MinerAbilities implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        int level = (int) Main.Load(commandSender.getName() + "_MinerLevel");

        if(level > 2)
            Main.Save(commandSender.getName() + "_MinerAbility", !(boolean)Main.Load(commandSender.getName() + "_MinerAbility"));
        return false;
    }
}
