package at.ahit.server.commands;

import at.ahit.server.overlays.Menu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SkillShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player)commandSender;
        Menu.openMenu(player);
        return false;
    }

    private void createJobMenu(){
    }

    private void createCookMenu(){

    }

    private void createFarmerMenu(){

    }

    private void createHunterMenu(){

    }

    private void createLumberjackMenu(){

    }

    private void createMinerMenu(){

    }

    private void createMonsterHunterMenu(){

    }
}
