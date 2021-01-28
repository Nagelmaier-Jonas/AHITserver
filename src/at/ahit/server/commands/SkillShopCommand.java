package at.ahit.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

public class SkillShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        createJobMenu();
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
