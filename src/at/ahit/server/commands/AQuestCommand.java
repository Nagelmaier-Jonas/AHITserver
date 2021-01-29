package at.ahit.server.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class AQuestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 0) {

                TextComponent wizard = new TextComponent("Do you want to view your Wizard quests?");
                wizard.setColor(ChatColor.GOLD);
                wizard.setBold(true);
                TextComponent farmer = new TextComponent("Do you want to view your Farmer quests?");
                farmer.setColor(ChatColor.GOLD);
                farmer.setBold(true);
                TextComponent hunter = new TextComponent("Do you want to view your Hunter quests?");
                hunter.setColor(ChatColor.GOLD);
                hunter.setBold(true);
                TextComponent lumber = new TextComponent("Do you want to view your Lumber quests?");
                lumber.setColor(ChatColor.GOLD);
                lumber.setBold(true);
                TextComponent miner = new TextComponent("Do you want to view your Miner quests?");
                miner.setColor(ChatColor.GOLD);
                miner.setBold(true);
                TextComponent monsterhunter = new TextComponent("Do you want to view your Monsterhunter quests?");
                monsterhunter.setColor(ChatColor.GOLD);
                monsterhunter.setBold(true);

                wizard.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard"));
                farmer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer"));
                hunter.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter"));
                lumber.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber"));
                miner.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner"));
                monsterhunter.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter"));

                wizard.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to view you Wizard Quests").color(ChatColor.GOLD).italic(true).create()));
                farmer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to view you Farmer Quests").color(ChatColor.GOLD).italic(true).create()));
                hunter.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to view you Hunter Quests").color(ChatColor.GOLD).italic(true).create()));
                lumber.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to view you Lumber Quests").color(ChatColor.GOLD).italic(true).create()));
                miner.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to view you Miner Quests").color(ChatColor.GOLD).italic(true).create()));
                monsterhunter.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to view you Monsterhunter Quests").color(ChatColor.GOLD).italic(true).create()));

                player.spigot().sendMessage(wizard);
                player.spigot().sendMessage(farmer);
                player.spigot().sendMessage(hunter);
                player.spigot().sendMessage(lumber);
                player.spigot().sendMessage(miner);
                player.spigot().sendMessage(monsterhunter);

                return true;
            }

            if (strings[0].equals("wizard") && strings[1].equals("")) {
                TextComponent wizardq1 = new TextComponent("Do a magic trick");
                wizardq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q1"));
                TextComponent wizardq2 = new TextComponent("Shit someone in their house");
                wizardq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q2"));
                TextComponent wizardq3 = new TextComponent("Summon the Weathergod");
                wizardq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q3"));
                TextComponent wizardq4 = new TextComponent("Get kicked for Flying");
                wizardq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q4"));
                TextComponent wizardq5 = new TextComponent("Get hit by lightning");
                wizardq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q5"));
                wizardq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest").color(ChatColor.GOLD).italic(true).create()));
                player.spigot().sendMessage(wizardq1);
                wizardq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest").color(ChatColor.GOLD).italic(true).create()));
                player.spigot().sendMessage(wizardq2);
                wizardq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest").color(ChatColor.GOLD).italic(true).create()));
                player.spigot().sendMessage(wizardq3);
                wizardq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest").color(ChatColor.GOLD).italic(true).create()));
                player.spigot().sendMessage(wizardq4);
                wizardq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest").color(ChatColor.GOLD).italic(true).create()));
                player.spigot().sendMessage(wizardq5);


            }
            if (strings[0].equals("wizard")) {
                switch (strings[1]) {
                    case "q1":
                        break;
                    case "q2":
                        break;
                    case "q3":
                        break;
                    case "q4":
                        break;
                    case "q5":
                        break;

                }
            }

            if (strings[0].equals("farmer")) {
                switch (strings[1]) {
                    case "q1":
                        break;
                    case "q2":
                        break;
                    case "q3":
                        break;
                    case "q4":
                        break;
                    case "q5":
                        break;

                }
            }


            if (strings[0].equals("farmer")) {
                player.sendMessage(ChatColor.GREEN + "Collect you first seeds");
            }

            if (strings[0].equals("hunter")) {
                player.sendMessage(ChatColor.GREEN + "Kill you first Animals");
            }

            if (strings[0].equals("lumber")) {
                player.sendMessage(ChatColor.GREEN + "Get some Wood");
            }

            if (strings[0].equals("miner")) {
                player.sendMessage(ChatColor.GREEN + "Mine some cobblestone");
            }

            if (strings[0].equals("monsterhunter")) {
                player.sendMessage(ChatColor.GREEN + "Kill your first Monsters");
            }
            return true;


        } else {
            commandSender.sendMessage("Console mach was anderes sonst Faust");
        }
        return false;
    }


}
