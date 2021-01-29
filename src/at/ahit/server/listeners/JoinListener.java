package at.ahit.server.listeners;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        firstJoin(Main.getConfigFile(),player);
        Scoreboards.createScoreboard(Main.getConfigFile(),player);
        customjoinmessage(event);
        event.setJoinMessage("");
    }

    public static void firstJoin(FileConfiguration config, Player player){
        String displayName = player.getDisplayName();
        if (config.get(displayName + "_HasPlayedBefore") == null) {
            config.set(displayName + "_HasPlayedBefore", true);
            config.set(displayName + "_Amount", 0);
            config.set(displayName + "_Overlay", true);
            config.set(displayName + "_MinerXp", 0);
            config.set(displayName + "_MinerLevel", 1);
            config.set(displayName + "_LumberjackXp", 0);
            config.set(displayName + "_LumberjackLevel", 1);

            Main.Save(displayName + "_MinerSkill1", false);
            Main.Save(displayName + "_MinerSkill2", false);
            Main.Save(displayName + "_MinerSkill3", false);

            Main.Save(displayName + "_FarmerSkill1", false);
            Main.Save(displayName + "_FarmerSkill2", false);
            Main.Save(displayName + "_FarmerSkill3", false);

            Main.Save(displayName + "_HunterSkill1", false);
            Main.Save(displayName + "_HunterSkill2", false);
            Main.Save(displayName + "_HunterSkill3", false);

            Main.Save(displayName + "_LumberjackSkill1", false);
            Main.Save(displayName + "_LumberjackSkill2", false);
            Main.Save(displayName + "_LumberjackSkill3", false);

            Main.Save(displayName + "_MonsterHunterSkill1", false);
            Main.Save(displayName + "_MonsterHunterSkill2", false);
            Main.Save(displayName + "_MonsterHunterSkill3", false);

        }

        Main.Save(displayName + "_MinerAbility1",false);
        Main.Save(displayName + "_MinerAbility2",false);
        Main.Save(displayName + "_MinerAbility3",false);

        Main.getPlugin().saveConfig();
    }

    public void customjoinmessage(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.getDisplayName().equals("Symo_TMS")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The " + ChatColor.RED + "G" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "Y" + ChatColor.GREEN + "L"
                    + ChatColor.BLUE + "O" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.RED + "D " + ChatColor.GRAY + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Joni04")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The " + ChatColor.RED +       "WILD " +    ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Katoka47")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "Dome dick, aber Domes dick dünn. Ach ja, " + ChatColor.DARK_BLUE + "Niki " + ChatColor.GRAY + "ist da!" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Gaduso11")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GOLD +       "ONE " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("guzms")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.GREEN +       "SEW-GUY " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Marcl_Gengsch")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.AQUA +       "NON-ALCOHOLIC " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("siegfried55b")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.MAGIC + ChatColor.RED +      "HIGH IQ GYMNASIUM GUY " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("SnoobyTV")){
            Bukkit.broadcastMessage(ChatColor.GRAY + "The "+ ChatColor.MAGIC +       "DRUGS " +      ChatColor.GRAY + "appears out of thin air" + ChatColor.RESET);
        }

    }

}
