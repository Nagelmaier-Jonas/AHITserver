package at.ahit.server.abilities;

import at.ahit.server.jobs.Miner;
import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MinerAbilities implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            if (strings.length == 1) {
                if (strings[0].equals("autosmelt") || strings[0].equals("autosmelter") || strings[0].equals("as")) {
                    if ((boolean) Main.Load(commandSender.getName() + "_MinerSkill1")) {
                        Main.Save(commandSender.getName() + "_MinerAbility1", !(boolean) Main.Load(commandSender.getName() + "_MinerAbility1"));
                        commandSender.sendMessage("AutoSmelt toggled to " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility1") + ChatColor.RESET);
                    }
                }
                if (strings[0].equals("big")) {
                    if ((boolean) Main.Load(commandSender.getName() + "_MinerSkill3"))
                        Main.Save(commandSender.getName() + "_MinerAbility3", !(boolean) Main.Load(commandSender.getName() + "_MinerAbility3"));
                    {
                        commandSender.sendMessage("Mine toggled " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility3") + ChatColor.RESET);
                    }
                }
            }
            if (strings.length == 0) {
                commandSender.sendMessage("AutoSmelter is " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility1") + ChatColor.RESET + " Haste is " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility2") + ChatColor.RESET + " and BigMine is " + ChatColor.AQUA + Main.Load(commandSender.getName() + "_MinerAbility3") + ChatColor.RESET);
                Miner.giveEffects((Player) commandSender);
            }
        }
        if (strings.length == 2) {
            if (strings[0].equals("set")) {
                try {
                    Main.Save(commandSender.getName() + "_MinerLevel", Integer.parseInt(strings[1]));
                    commandSender.sendMessage("MinerLevel set to " + Main.Load(commandSender.getName() + "_MinerLevel"));
                    Scoreboards.createScoreboard(Main.getConfigFile(), (Player) commandSender);
                } catch (Exception e) {
                    commandSender.sendMessage("Bist du behindert?");
                }
            }
        }
        if (strings.length != 0)
            if (strings[0].equals("reset")) {
                Main.Save(commandSender.getName() + "_MinerSkill1", false);
                Main.Save(commandSender.getName() + "_MinerSkill2", false);
                Main.Save(commandSender.getName() + "_MinerSkill3", false);
                Main.Save(commandSender.getName() + "_MinerAbility1", false);
                Main.Save(commandSender.getName() + "_MinerAbility2", false);
                Main.Save(commandSender.getName() + "_MinerAbility3", false);
            }
        if (strings.length != 0)
            if (strings[0].equals("carrot")) {
                for (int i = 0; i < Integer.parseInt(strings[2]); i++)
                    Bukkit.getServer().getPlayer(strings[1]).getLocation().getWorld().dropItem(Bukkit.getServer().getPlayer(strings[1]).getLocation(), new ItemStack(Material.CARROT, 64));
            }
        return false;
    }
}
