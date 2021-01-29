package at.ahit.server.commands;

import at.ahit.server.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings){
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 0) {
                FileConfiguration config = Main.getPlugin().getConfig();
                player.sendMessage("§aDu hast§6 " + config.get(player.getDisplayName() + "_Coins") + " §aCoins");
            } else {
                player.sendMessage("§5Bitte benutze nur §6/coins §5!");
            }
            return true;
        }
        return false;
    }
}


