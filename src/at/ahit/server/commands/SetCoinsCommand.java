package at.ahit.server.commands;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetCoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("coinsystem.bonus")) {
                if (strings.length == 2){
                    Player target = Bukkit.getPlayer(strings[0]);
                    if (target != null){
                        FileConfiguration config = Main.getConfigFile();
                        if (strings[1].matches("[+-]?\\d*(\\.\\d+)?")){
                            if ((int)config.get(target.getDisplayName() + "_Amount") + Integer.parseInt(strings[1]) >= 0){
                                config.set(target.getDisplayName() + "_Amount", (int)config.get(target.getDisplayName() + "_Amount") + Integer.parseInt(strings[1]));
                                Main.getPlugin().saveConfig();
                                if ((boolean) Main.Load(player.getDisplayName() + "_Overlay")){
                                    Scoreboards.createScoreboard(config,player);
                                }else{
                                    player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                                }
                                //Save Server
                                target.sendMessage("§aDir wurden§6 " + strings[1] + " §aCoins hinzugefügt! Dein Guthaben beträgt:§6 " + config.get(player.getDisplayName() + "_Amount"));
                            }else{
                                target.sendMessage("§4Du hast zu wenig Geld!");
                            }
                        }else{
                            String[] split = new String[2];
                            split[0] = strings[1].substring(0,1);
                            split[1] = strings[1].substring(1);
                            if (split[0].equals("=") && split[1].matches("[+]?\\d*(\\.\\d+)?")){
                                config.set(target.getDisplayName() + "_Amount", Integer.parseInt(split[1]));
                                Main.getPlugin().saveConfig();
                                if ((boolean) Main.Load(player.getDisplayName() + "_Overlay")){
                                    Scoreboards.createScoreboard(config,player);
                                }else{
                                    player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                                }
                                //Save Server
                                target.sendMessage("§aDein Geld wurde auf§6 " + split[1] + " §agesetzt!");
                            }else{
                                target.sendMessage("§4Es wird nur ein '=' vor dem Amount akzeptiert");
                            }
                        }
                    }else{
                        player.sendMessage("§4Spieler§a " + strings[0] + " §4ist nicht online");
                    }
                }else{
                    player.sendMessage("§7Bitte benutze §c/setcoins §2<Player> §b<Amount>");
                }
            } else {
                player.sendMessage("§4Du musst Operator sein um diesen Befehl nutzen zu können!");
            }
        } else {
            commandSender.sendMessage("§4Du hast keine Berechtigung dafür");
        }
        return false;
    }
}
