package at.ahit.server.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class AQuestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (strings.length == 0){

                TextComponent cook = new TextComponent("Do you want to view your cook quests?");
                TextComponent farmer = new TextComponent("Do you want to view your miner quests?");
                TextComponent hunter = new TextComponent("Do you want to view your miner quests?");
                TextComponent lumber = new TextComponent("Do you want to view your miner quests?");
                TextComponent miner = new TextComponent("Do you want to view your miner quests?");
                TextComponent monsterhunter = new TextComponent("Do you want to view your miner quests?");

                cook.setColor(ChatColor.GOLD);farmer.setColor(ChatColor.GOLD);hunter.setColor(ChatColor.GOLD);
                lumber.setColor(ChatColor.GOLD);miner.setColor(ChatColor.GOLD);monsterhunter.setColor(ChatColor.GOLD);

                cook.setBold(true);farmer.setBold(true);hunter.setBold(true);
                lumber.setBold(true);miner.setBold(true);monsterhunter.setBold(true);

                cook.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest cook"));
                farmer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer"));
                hunter.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter"));
                lumber.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber"));
                miner.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner"));
                monsterhunter.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter"));

                player.spigot().sendMessage(cook);player.spigot().sendMessage(farmer);
                player.spigot().sendMessage(hunter);player.spigot().sendMessage(lumber);
                player.spigot().sendMessage(miner);player.spigot().sendMessage(monsterhunter);

                return true;
            }

            if (strings[0].equals("cook")){
                player.sendMessage("Cook you first Food");
            }

            if (strings[0].equals("farmer")){
                player.sendMessage("Collect you first seeds");
            }

            if (strings[0].equals("hunter")){
                player.sendMessage("Kill you first Animals");
            }

            if (strings[0].equals("lumber")){
                player.sendMessage("Get some Wood");
            }

            if (strings[0].equals("miner")){
                player.sendMessage("Mine some cobblestone");
            }

            if (strings[0].equals("monsterhunter")){
                player.sendMessage("Kill your first Monsters");
            }
            return true;


        }
        else {
            commandSender.sendMessage("Console mach was anderes sonst Faust");
        }
        return false;
    }


}
