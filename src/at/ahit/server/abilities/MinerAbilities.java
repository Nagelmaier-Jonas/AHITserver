package at.ahit.server.abilities;

import at.ahit.server.main.Main;
import org.bukkit.ChatColor;
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
            if (strings[0].equals("autosmelt")) {
                if ((boolean) Main.Load(commandSender.getName() + "_MinerSkill1")) {
                    Main.Save(commandSender.getName() + "_MinerAbility1", !(boolean) Main.Load(commandSender.getName() + "_MinerAbility1"));
                    commandSender.sendMessage("AutoSmelt toggled to " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility1") + ChatColor.RESET);
                }
            }
            if (strings[0].equals("big")) {
                if ((boolean) Main.Load(commandSender.getName() + "_MinerSkill3"))
                    Main.Save(commandSender.getName() + "_MinerAbility3", !(boolean) Main.Load(commandSender.getName() + "_MinerAbility3"));
                {
                    commandSender.sendMessage("Mine toggled " + ChatColor.AQUA  + Main.Load(commandSender.getName() + "_MinerAbility3") + ChatColor.RESET);
                }
            }
        }
        if(strings.length == 0) {
            commandSender.sendMessage("AutoSmelter is " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility1") + ChatColor.RESET + " and BigMine is " + ChatColor.AQUA  + Main.Load(commandSender.getName() + "_MinerAbility3") + ChatColor.RESET);
        }

        return false;
    }
}
