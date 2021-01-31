package at.ahit.server.commands;

import at.ahit.server.main.Main;
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
import org.jetbrains.annotations.NotNull;

import static at.ahit.server.usefulstuff.AQuestMethods.getInventoryLocation;


public class AQuestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (commandSender instanceof Player){

            Player player = (Player) commandSender;

            String wizardquest = (String) Main.Load(player.getDisplayName() + "_QuestsWizard");
            String farmerquest = (String) Main.Load(player.getDisplayName() + "_QuestsFarmer");
            String hunterquest = (String) Main.Load(player.getDisplayName() + "_QuestsHunter");
            String lumberquest = (String) Main.Load(player.getDisplayName() + "_QuestsLumberjack");
            String minerquest = (String) Main.Load(player.getDisplayName() + "_QuestsMiner");
            String monsterhunterquest = (String) Main.Load(player.getDisplayName() + "_QuestsMonsterHunter");

            if (strings.length == 0){

                TextComponent wizard = new TextComponent("Do you want to view your Wizard quests?");                wizard.setColor(ChatColor.YELLOW);
                TextComponent farmer = new TextComponent("Do you want to view your Farmer quests?");                farmer.setColor(ChatColor.YELLOW);
                TextComponent hunter = new TextComponent("Do you want to view your Hunter quests?");                hunter.setColor(ChatColor.YELLOW);
                TextComponent lumber = new TextComponent("Do you want to view your Lumber quests?");                lumber.setColor(ChatColor.YELLOW);
                TextComponent miner = new TextComponent("Do you want to view your Miner quests?");                  miner.setColor(ChatColor.YELLOW);
                TextComponent monsterhunter = new TextComponent("Do you want to view your Monsterhunter quests?");  monsterhunter.setColor(ChatColor.YELLOW);

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

                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
                player.spigot().sendMessage(wizard);player.spigot().sendMessage(farmer);
                player.spigot().sendMessage(hunter);player.spigot().sendMessage(lumber);
                player.spigot().sendMessage(miner);player.spigot().sendMessage(monsterhunter);
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");



                return true;
            }

            if (strings[0].equals("wizard") && strings.length == 1){
                TextComponent wizardq1 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");wizardq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q1"));
                TextComponent wizardq2 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");wizardq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q2"));
                TextComponent wizardq3 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");wizardq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q3"));
                TextComponent wizardq4 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");wizardq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q4"));
                TextComponent wizardq5 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");wizardq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q5"));player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
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
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
                return true;
            }// QUESTS ARE MISSING

            if (strings[0].equals("wizard")){
                switch (strings[1]){
                    case "q1":
                        if (wizardquest.equals("0, 0, 0, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 0, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q2":
                        if (wizardquest.equals("1, 0, 0, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q3":
                        if (wizardquest.equals("1, 1, 0, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 1, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q4":
                        if (wizardquest.equals("1, 1, 1, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 1, 1, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q5":
                        if (wizardquest.equals("1, 1, 1, 1, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsWizard", "1, 1, 1, 1, 1");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }// QUESTS ARE MISSING

            if (strings[0].equals("farmer") && strings.length == 1){
                TextComponent farmerq1 = new TextComponent(ChatColor.AQUA + "Collect you first Seeds " + ChatColor.GREEN + "(4x Seeds required)");farmerq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q1"));
                TextComponent farmerq2 = new TextComponent(ChatColor.AQUA + "Craft a Compostor " + ChatColor.GREEN +  "(1x Compostor required)");farmerq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q2"));
                TextComponent farmerq3 = new TextComponent(ChatColor.AQUA + "Craft a Diamond Hoe " + ChatColor.GREEN + "(1x Diamond Hoe)");farmerq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q3"));
                TextComponent farmerq4 = new TextComponent(ChatColor.AQUA + "Build a Big Farm " + ChatColor.GREEN + "(64x Pumpkin Pies required)");farmerq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q4"));
                TextComponent farmerq5 = new TextComponent(ChatColor.AQUA + "Get The Ultimate Hoe "+ ChatColor.GREEN + "(1x Netherite Hoe)");farmerq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest farmer q5"));
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
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
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");

                return true;
            }// TODO DO THIS

            if (strings[0].equals("farmer")){
                switch (strings[1]){
                    case "q1":
                        if (farmerquest.equals("0, 0, 0, 0, 0")){
                            getInventoryLocation(Material.WHEAT_SEEDS , 4, player, 12, true, true, Material.WHEAT, 6, "Wheat");
                            Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 0, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q2":
                        if (farmerquest.equals("1, 0, 0, 0, 0")){
                            getInventoryLocation(Material.COMPOSTER , 1, player, 17, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q3":
                        if (farmerquest.equals("1, 1, 0, 0, 0")){
                            getInventoryLocation(Material.DIAMOND_HOE , 1, player, 100, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 1, 0, 0");
                            player.sendMessage(ChatColor.GOLD + "WTF IS WRONG WITH YOU YOU CRAFTET A DIAMOND HOE");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q4":
                        if (farmerquest.equals("1, 1, 1, 0, 0")){
                            getInventoryLocation(Material.PUMPKIN_PIE , 64, player, 400, true, true, Material.ENCHANTED_GOLDEN_APPLE, 1, "God Apple");
                            Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 1, 1, 0");

                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q5":
                        if (farmerquest.equals("1, 1, 1, 1, 0")){
                            getInventoryLocation(Material.NETHERITE_HOE , 1, player, 1500, false, true, Material.DIAMOND, 2, "DIAMOND");
                            Main.Save(player.getDisplayName() + "_QuestsFarmer", "1, 1, 1, 1, 1");
                            player.sendMessage(ChatColor.GOLD + "WTF IS WRONG WITH YOU YOU CRAFTET A NETHERITE HOE");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }// TODO DO THIS

            if (strings[0].equals("hunter") && strings.length == 1){
                TextComponent hunterq1 = new TextComponent(ChatColor.AQUA + "Get your first food " + ChatColor.GREEN + "(3x Raw Beef required)");hunterq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q1"));
                TextComponent hunterq2 = new TextComponent(ChatColor.AQUA + "Craft some Arrows " + ChatColor.GREEN + "(64x Arrows required)");hunterq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q2"));
                TextComponent hunterq3 = new TextComponent(ChatColor.AQUA + "Get some Pink Wool " + ChatColor.GREEN + "(15x Pink Wool required)");hunterq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q3"));
                TextComponent hunterq4 = new TextComponent(ChatColor.AQUA + "Get a Saddle " + ChatColor.GREEN + "(1x Saddle required required)");hunterq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q4"));
                TextComponent hunterq5 = new TextComponent(ChatColor.AQUA + "Get some Leather " + ChatColor.GREEN + "(64x Leather required)");hunterq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest hunter q5"));
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");

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
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");

                return true;
            }// TODO BALANCE STUFF

            if (strings[0].equals("hunter")){
                switch (strings[1]){
                    case "q1":
                        if (hunterquest.equals("0, 0, 0, 0, 0")){
                            getInventoryLocation(Material.BEEF , 3,  player, 12, true, true, Material.COOKED_BEEF, 3, "Steaks");
                            Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 0, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q2":
                        if (hunterquest.equals("1, 0, 0, 0, 0")){
                            getInventoryLocation(Material.ARROW , 64,  player, 45, false, false, Material.COOKED_BEEF, 3, "Steaks");

                            Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q3":
                        if (hunterquest.equals("1, 1, 0, 0, 0")){
                            getInventoryLocation(Material.PINK_WOOL , 15,  player, 50, false, false, Material.COOKED_BEEF, 3, "Steaks");
                            Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 1, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q4":
                        if (hunterquest.equals("1, 1, 1, 0, 0")){
                            getInventoryLocation(Material.SADDLE , 1,  player, 300, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 1, 1, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q5":
                        if (hunterquest.equals("1, 1, 1, 1, 0")){
                            getInventoryLocation(Material.LEATHER , 64,  player, 500, true, true, Material.BOOKSHELF, 16, "Bookshelf");

                            Main.Save(player.getDisplayName() + "_QuestsHunter", "1, 1, 1, 1, 1");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }// TODO BALANCE STUFF

            if (strings[0].equals("lumber") && strings.length == 1){
                TextComponent lumberq1 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");lumberq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q1"));
                TextComponent lumberq2 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");lumberq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q2"));
                TextComponent lumberq3 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");lumberq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q3"));
                TextComponent lumberq4 = new TextComponent(ChatColor.AQUA + "Not Implemented " + ChatColor.GREEN + "");lumberq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q4"));
                TextComponent lumberq5 = new TextComponent(ChatColor.AQUA + "Get The Ultimate Axe "+ ChatColor.GREEN + "(1x Netherite Axe)");lumberq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest lumber q5"));
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
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
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
                return true;
            }// TODO DO THIS

            if (strings[0].equals("lumber")){
                switch (strings[1]){
                    case "q1":
                        if (lumberquest.equals("0, 0, 0, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 0, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q2":
                        if (lumberquest.equals("1, 0, 0, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q3":
                        if (lumberquest.equals("1, 1, 0, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 1, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q4":
                        if (lumberquest.equals("1, 1, 1, 0, 0")){
                            Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 1, 1, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q5":
                        if (lumberquest.equals("1, 1, 1, 1, 0")){
                            getInventoryLocation(Material.NETHERITE_AXE , 1,  player, 150, false, true, Material.DIAMOND, 3, "Diamonds");
                            Main.Save(player.getDisplayName() + "_QuestsLumberjack", "1, 1, 1, 1, 1");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }// TODO DO THIS

            if (strings[0].equals("miner") && strings.length == 1){
                TextComponent minerq1 = new TextComponent(ChatColor.AQUA + "Get some Clean Stone " + ChatColor.GREEN + "(64x Clean Stone required)");minerq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q1"));
                TextComponent minerq2 = new TextComponent(ChatColor.AQUA + "Get some Lapis Lazuli " + ChatColor.GREEN + "(32x Lapis Lazuli required)");minerq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q2"));
                TextComponent minerq3 = new TextComponent(ChatColor.AQUA + "Get some Emeralds " + ChatColor.GREEN + "(2x Emeralds required)");minerq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q3"));
                TextComponent minerq4 = new TextComponent(ChatColor.AQUA + "Get some Diamonds " + ChatColor.GREEN + "(16x Diamonds required)");minerq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q4"));
                TextComponent minerq5 = new TextComponent(ChatColor.AQUA + "Get the Ultimate Pickaxe " + ChatColor.GREEN + "(1x Netherite Pickaxe required)");minerq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest miner q5"));
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
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
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
                return true;
            }// TODO BALANCE STUFF

            if (strings[0].equals("miner")){
                switch (strings[1]){
                    case "q1":
                        if (minerquest.equals("0, 0, 0, 0, 0")){
                            getInventoryLocation(Material.STONE , 64,  player, 50, false, true, Material.TORCH, 32, "Torches");
                            Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 0, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q2":
                        if (minerquest.equals("1, 0, 0, 0, 0")){
                            getInventoryLocation(Material.LAPIS_LAZULI , 32,  player, 200, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q3":
                        if (minerquest.equals("1, 1, 0, 0, 0")){
                            getInventoryLocation(Material.EMERALD , 2,  player, 500, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 1, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q4":
                        if (minerquest.equals("1, 1, 1, 0, 0")){
                            getInventoryLocation(Material.DIAMOND , 16,  player, 800, false, true, Material.DIAMOND_PICKAXE, 1, "Diamond Pickaxe");
                            Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 1, 1, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q5":
                        if (minerquest.equals("1, 1, 1, 1, 0")){
                            getInventoryLocation(Material.NETHERITE_PICKAXE , 1,  player, 1500, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMiner", "1, 1, 1, 1, 1");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }// TODO BALANCE STUFF

            if (strings[0].equals("monsterhunter") && strings.length == 1){
                TextComponent monsterhunterq1 = new TextComponent(ChatColor.AQUA + "Get some Ender Pearls " + ChatColor.GREEN + "(12x Ender Pearls required)");monsterhunterq1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q1"));
                TextComponent monsterhunterq2 = new TextComponent(ChatColor.AQUA + "Fight the Darkness " + ChatColor.GREEN + "(1x Wither Skull required)");monsterhunterq2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q2"));
                TextComponent monsterhunterq3 = new TextComponent(ChatColor.AQUA + "Kill the Screaming Thing " + ChatColor.GREEN + "(1x Netherstar required)");monsterhunterq3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q3"));
                TextComponent monsterhunterq4 = new TextComponent(ChatColor.AQUA + "Bring me his Head " + ChatColor.GREEN + "(1x Dragon Head required)");monsterhunterq4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q4"));
                TextComponent monsterhunterq5 = new TextComponent(ChatColor.AQUA + "Get the Ultimate Sword " + ChatColor.GREEN + "(1x Netherite Sword required)");monsterhunterq5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aquest monsterhunter q5"));
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
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
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.MAGIC +  "--=========----=========----=========--");
                return true;
            }// TODO BALANCE STUFF

            if (strings[0].equals("monsterhunter")){
                switch (strings[1]){
                    case "q1":
                        if (monsterhunterquest.equals("0, 0, 0, 0, 0")){
                            getInventoryLocation(Material.ENDER_PEARL , 12,  player, 50, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 0, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q2":
                        if (monsterhunterquest.equals("1, 0, 0, 0, 0")){
                            getInventoryLocation(Material.WITHER_SKELETON_SKULL , 1,  player, 200, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 0, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q3":
                        if (monsterhunterquest.equals("1, 1, 0, 0, 0")){
                            getInventoryLocation(Material.NETHER_STAR , 1,  player, 700, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 1, 0, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q4":
                        if (monsterhunterquest.equals("1, 1, 1, 0, 0")){
                            getInventoryLocation(Material.DRAGON_HEAD , 1,  player, 1500, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 1, 1, 0");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    case "q5":
                        if (monsterhunterquest.equals("1, 1, 1, 1, 0")){
                            getInventoryLocation(Material.NETHERITE_SWORD , 1,  player, 1500, false, false, Material.ACACIA_BOAT, 0, "none");
                            Main.Save(player.getDisplayName() + "_QuestsMonsterHunter", "1, 1, 1, 1, 1");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You are not allowed to do this Quest");
                        }
                        break;
                    default:
                        return true;

                }
                return true;
            }// TODO BALANCE STUFF

            return true;

        }
        else {
            commandSender.sendMessage("Console mach was anderes sonst Faust");
        }
        return true;
    }


}
