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
        if (strings.length == 1) {
            if (strings[0].equals("autosmelt"))
                if ((boolean) Main.Load(commandSender.getName() + "_MinerSkill1"))
                    Main.Save(commandSender.getName() + "_MinerAbility1", !(boolean) Main.Load(commandSender.getName() + "_MinerAbility1"));
            if (strings[0].equals("big"))
                if ((boolean) Main.Load(commandSender.getName() + "_MinerSkill2"))
                    Main.Save(commandSender.getName() + "_MinerAbility2", !(boolean) Main.Load(commandSender.getName() + "_MinerAbility2"));
        }
        return false;
    }
}
