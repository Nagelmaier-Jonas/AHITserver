package at.ahit.server.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static at.ahit.server.bullshit.Bullshit.getInventoryLocation;


public class AQuestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (strings.length == 0){

                TextComponent wizard = new TextComponent("Do you want to view your Wizard quests?");                wizard.setColor(ChatColor.GOLD);wizard.setBold(true);
                TextComponent farmer = new TextComponent("Do you want to view your Farmer quests?");                farmer.setColor(ChatColor.GOLD);farmer.setBold(true);
                TextComponent hunter = new TextComponent("Do you want to view your Hunter quests?");                hunter.setColor(ChatColor.GOLD);hunter.setBold(true);
                TextComponent lumber = new TextComponent("Do you want to view your Lumber quests?");                lumber.setColor(ChatColor.GOLD);lumber.setBold(true);
                TextComponent miner = new TextComponent("Do you want to view your Miner quests?");                  miner.setColor(ChatColor.GOLD);miner.setBold(true);
                TextComponent monsterhunter = new TextComponent("Do you want to view your Monsterhunter quests?");  monsterhunter.setColor(ChatColor.GOLD);monsterhunter.setBold(true);

                wizard.setClickEvent(       new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard"));
                farmer.setClickEvent(       new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer"));
                hunter.setClickEvent(       new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter"));
                lumber.setClickEvent(       new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber"));
                miner.setClickEvent(        new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner"));
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

                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                player.spigot().sendMessage(wizard);player.spigot().sendMessage(farmer);
                player.spigot().sendMessage(hunter);player.spigot().sendMessage(lumber);
                player.spigot().sendMessage(miner);player.spigot().sendMessage(monsterhunter);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");

                return true;
            }

            if (strings[0].equals("wizard") && strings.length == 1){
                TextComponent wizardq1 = new TextComponent("Do a magic trick");wizardq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q1"));
                TextComponent wizardq2 = new TextComponent("Shit someone in their house");wizardq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q2"));
                TextComponent wizardq3 = new TextComponent("Summon the Weathergod");wizardq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q3"));
                TextComponent wizardq4 = new TextComponent("Get kicked for Flying");wizardq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q4"));
                TextComponent wizardq5 = new TextComponent("Get hit by lightning");wizardq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest wizard q5"));
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                wizardq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(wizardq1);
                wizardq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(wizardq2);
                wizardq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(wizardq3);
                wizardq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(wizardq4);
                wizardq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(wizardq5);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                return true;
            }


            if (strings[0].equals("wizard")){
                switch (strings[1]){
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
                    default:
                        return true;
                }
                return true;
            }

            if (strings[0].equals("farmer") && strings.length == 1){
                TextComponent farmerq1 = new TextComponent(ChatColor.AQUA + "Collect you first Seeds " + ChatColor.GREEN + "(4x Seeds required)");farmerq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q1"));
                TextComponent farmerq2 = new TextComponent(ChatColor.AQUA + "Craft a Compostor " + ChatColor.GREEN +  "(1x Compostor required)");farmerq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q2"));
                TextComponent farmerq3 = new TextComponent(ChatColor.AQUA + "Craft a Diamond Hoe " + ChatColor.GREEN + "(1x Netherite Hoe)");farmerq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q3"));
                TextComponent farmerq4 = new TextComponent(ChatColor.AQUA + "Craft a Netherite Hoe "+ ChatColor.GREEN + "(1x Diamond Hoe)");farmerq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q4"));
                TextComponent farmerq5 = new TextComponent(ChatColor.AQUA + "Build a Big Farm \n" +
                        ChatColor.GREEN + "(64x Pumpkin Pies required");farmerq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q5"));
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                farmerq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(farmerq1);
                farmerq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(farmerq2);
                farmerq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(farmerq3);
                farmerq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(farmerq4);
                farmerq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(farmerq5);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                return true;
            }

            if (strings[0].equals("farmer")){
                switch (strings[1]){
                    case "q1":
                        getInventoryLocation(Material.WHEAT_SEEDS , 4, player.getInventory(), player, 12, true);
                        break;
                    case "q2":
                        getInventoryLocation(Material.COMPOSTER , 1, player.getInventory(), player, 17, false);
                        break;
                    case "q3":
                        getInventoryLocation(Material.DIAMOND_HOE , 1, player.getInventory(), player, 100, false);
                        break;
                    case "q4":
                        getInventoryLocation(Material.NETHERITE_HOE , 1, player.getInventory(), player, 250, false);
                        break;
                    case "q5":
                        getInventoryLocation(Material.PUMPKIN_PIE , 64, player.getInventory(), player, 400, true);
                        break;
                    default:
                        return true;
                }
                return true;
            }

            if (strings[0].equals("hunter") && strings.length == 1){
                TextComponent hunterq1 = new TextComponent(ChatColor.AQUA + "Get your first food " + ChatColor.GREEN +
                        "(requires 3x Raw Beef)");hunterq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q1"));
                TextComponent hunterq2 = new TextComponent(ChatColor.AQUA + "Shit someone in their house " + ChatColor.GREEN + "");hunterq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q2"));
                TextComponent hunterq3 = new TextComponent(ChatColor.AQUA + "Summon the Weathergod " + ChatColor.GREEN + "");hunterq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q3"));
                TextComponent hunterq4 = new TextComponent(ChatColor.AQUA + "Get kicked for Flying " + ChatColor.GREEN + "");hunterq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q4"));
                TextComponent hunterq5 = new TextComponent(ChatColor.AQUA + "Get hit by lightning " + ChatColor.GREEN + "");hunterq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q5"));
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                hunterq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(hunterq1);
                hunterq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(hunterq2);
                hunterq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(hunterq3);
                hunterq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(hunterq4);
                hunterq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(hunterq5);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                return true;
            }

            if (strings[0].equals("hunter")){
                switch (strings[1]){
                    case "q1":
                        getInventoryLocation(Material.BEEF , 3, player.getInventory(), player, 12, true);
                        break;
                    case "q2":
                        getInventoryLocation(Material.WHEAT_SEEDS , 4, player.getInventory(), player, 12, true);
                        break;
                    case "q3":
                        getInventoryLocation(Material.WHEAT_SEEDS , 4, player.getInventory(), player, 12, true);
                        break;
                    case "q4":
                        getInventoryLocation(Material.WHEAT_SEEDS , 4, player.getInventory(), player, 12, true);
                        break;
                    case "q5":
                        getInventoryLocation(Material.WHEAT_SEEDS , 4, player.getInventory(), player, 12, true);
                        break;
                    default:
                        return true;
                }
                return true;
            }

            if (strings[0].equals("lumber") && strings.length == 1){
                TextComponent lumberq1 = new TextComponent("Do a magic trick");lumberq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q1"));
                TextComponent lumberq2 = new TextComponent("Shit someone in their house");lumberq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q2"));
                TextComponent lumberq3 = new TextComponent("Summon the Weathergod");lumberq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q3"));
                TextComponent lumberq4 = new TextComponent("Get kicked for Flying");lumberq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q4"));
                TextComponent lumberq5 = new TextComponent("Get hit by lightning");lumberq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q5"));
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                lumberq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(lumberq1);
                lumberq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(lumberq2);
                lumberq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(lumberq3);
                lumberq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(lumberq4);
                lumberq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(lumberq5);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                return true;
            }

            if (strings[0].equals("lumber")){
                switch (strings[1]){
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
                    default:
                        return true;
                }
                return true;
            }

            if (strings[0].equals("miner") && strings.length == 1){
                TextComponent minerq1 = new TextComponent("Do a magic trick");minerq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q1"));
                TextComponent minerq2 = new TextComponent("Shit someone in their house");minerq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q2"));
                TextComponent minerq3 = new TextComponent("Summon the Weathergod");minerq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q3"));
                TextComponent minerq4 = new TextComponent("Get kicked for Flying");minerq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q4"));
                TextComponent minerq5 = new TextComponent("Get hit by lightning");minerq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q5"));
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                minerq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(minerq1);
                minerq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(minerq2);
                minerq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(minerq3);
                minerq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(minerq4);
                minerq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(minerq5);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                return true;
            }

            if (strings[0].equals("miner")){
                switch (strings[1]){
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
                    default:
                        return true;
                }
                return true;
            }

            if (strings[0].equals("monsterhunter") && strings.length == 1){
                TextComponent monsterhunterq1 = new TextComponent("Do a magic trick");monsterhunterq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q1"));
                TextComponent monsterhunterq2 = new TextComponent("Shit someone in their house");monsterhunterq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q2"));
                TextComponent monsterhunterq3 = new TextComponent("Summon the Weathergod");monsterhunterq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q3"));
                TextComponent monsterhunterq4 = new TextComponent("Get kicked for Flying");monsterhunterq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q4"));
                TextComponent monsterhunterq5 = new TextComponent("Get hit by lightning");monsterhunterq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q5"));
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                monsterhunterq1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(monsterhunterq1);
                monsterhunterq2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(monsterhunterq2);
                monsterhunterq3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(monsterhunterq3);
                monsterhunterq4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(monsterhunterq4);
                monsterhunterq5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Click here to complete the Quest \n" +
                                "(you need the required items in your inventory)").color(ChatColor.GOLD).italic(true).create()));player.spigot().sendMessage(monsterhunterq5);
                player.sendMessage(ChatColor.BLUE + "--=========----=========----=========--");
                return true;
            }

            if (strings[0].equals("monsterhunter")){
                switch (strings[1]){
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
                    default:
                        return true;

                }
                return true;
            }

            return true;

        }
        else {
            commandSender.sendMessage("Console mach was anderes sonst Faust");
        }
        return true;
    }


}
