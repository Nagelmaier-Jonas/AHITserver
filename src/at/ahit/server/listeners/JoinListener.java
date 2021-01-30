package at.ahit.server.listeners;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import net.minecraft.server.v1_16_R3.ChatMessage;
import net.minecraft.server.v1_16_R3.ChatModifier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Objects;


public class JoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        firstJoin(Main.getConfigFile(),player);
        reloadScoreboard();
        customjoinmessage(event);
        event.setJoinMessage("");
    }

    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent event) {
        reloadScoreboardOffline();
    }


    public static void reloadScoreboard() {
        List<Player> pList = (List<Player>) Bukkit.getOnlinePlayers();
        for (Player p : pList) {
            Scoreboards.createScoreboard(Main.getConfigFile(), p);
        }
    }
    public static void reloadScoreboardOffline() {
        List<Player> pList = (List<Player>) Bukkit.getOnlinePlayers();
        for (Player p : pList) {
            Scoreboards.createScoreboardOnLeave(Main.getConfigFile(), p);
        }
    }

    public static void firstJoin(FileConfiguration config, Player player){
        String displayName = player.getDisplayName();
        if (config.get(displayName + "_HasPlayedBefore") == null) {
            setConfig(displayName);
            setSkills(displayName);
        }
        setAbilities(displayName);
        Main.getPlugin().saveConfig();
    }
    
    public static void setConfig(String displayName){
        Main.Save(displayName + "_HasPlayedBefore",true);
        Main.Save(displayName + "_Amount",0);
        Main.Save(displayName + "_Overlay",true);
        Main.Save(displayName + "_QuestsFarmer",        "0, 0, 0, 0, 0");
        Main.Save(displayName + "_QuestsHunter",        "0, 0, 0, 0, 0");
        Main.Save(displayName + "_QuestsLumberjack",    "0, 0, 0, 0, 0");
        Main.Save(displayName + "_QuestsMiner",         "0, 0, 0, 0, 0");
        Main.Save(displayName + "_QuestsMonsterHunter", "0, 0, 0, 0, 0");
        Main.Save(displayName + "_QuestsWizard",        "0, 0, 0, 0, 0");

        Main.Save(displayName + "_FarmerXp", 0);
        Main.Save(displayName + "_FarmerLevel", 1);

        Main.Save(displayName + "_HunterXp", 0);
        Main.Save(displayName + "_HunterLevel", 1);

        Main.Save(displayName + "_LumberjackXp", 0);
        Main.Save(displayName + "_LumberjackLevel", 1);

        Main.Save(displayName + "_MinerXp", 0);
        Main.Save(displayName + "_MinerLevel", 1);

        Main.Save(displayName + "_MonsterHunterXp", 0);
        Main.Save(displayName + "_MonsterHunterLevel", 1);

        Main.Save(displayName + "_WizardXp", 0);
        Main.Save(displayName + "_WizardLevel", 1);
    }
    
    public static void setSkills(String displayName){
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

        Main.Save(displayName + "_WizardSkill1", false);
        Main.Save(displayName + "_WizardSkill2", false);
        Main.Save(displayName + "_WizardSkill3", false);
    }
    
    public static void setAbilities(String displayName){
        Main.Save(displayName + "_MinerAbility1", false);
        Main.Save(displayName + "_MinerAbility2", false);
        Main.Save(displayName + "_MinerAbility3", false);

        Main.Save(displayName + "_FarmerAbility1", false);
        Main.Save(displayName + "_FarmerAbility2", false);
        Main.Save(displayName + "_FarmerAbility3", false);

        Main.Save(displayName + "_HunterAbility1", false);
        Main.Save(displayName + "_HunterAbility2", false);
        Main.Save(displayName + "_HunterAbility3", false);

        Main.Save(displayName + "_LumberjackAbility1", false);
        Main.Save(displayName + "_LumberjackAbility2", false);
        Main.Save(displayName + "_LumberjackAbility3", false);

        Main.Save(displayName + "_MonsterHunterAbility1", false);
        Main.Save(displayName + "_MonsterHunterAbility2", false);
        Main.Save(displayName + "_MonsterHunterAbility3", false);

        Main.Save(displayName + "_WizardAbility1", false);
        Main.Save(displayName + "_WizardAbility2", false);
        Main.Save(displayName + "_WizardAbility3", false);
    }

    public void customjoinmessage(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.getDisplayName().equals("Symo_TMS")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.BLUE + "Symo " + ChatColor.YELLOW + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Joni04")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.BLUE + "Joni " + ChatColor.YELLOW + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Katoka47")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.YELLOW + "Dome dick, aber Domes dick dünn. Ach ja, " + ChatColor.DARK_BLUE + "Niki " + ChatColor.GRAY + "ist da!" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Gaduso11")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.BLUE + "Gaduso " + ChatColor.YELLOW + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("guzms")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.YELLOW + "Versteckts eichre Kinder, da guzms is da!" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("Marcl_Gengsch")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.BLUE + "Marcl " + ChatColor.YELLOW + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("siegfried55b")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.MAGIC + "HHH " + ChatColor.BLUE + ". " + ChatColor.YELLOW + "appears out off thin air" + ChatColor.RESET);
        }
        if (player.getDisplayName().equals("SnoobyTV")){
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "HHH " + ChatColor.BLUE + "Snooby " + ChatColor.YELLOW + "appears out off thin air" + ChatColor.RESET);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        switch (victim.getName()) { //SPIELERSPEZIEFISCHE
            case "Symo_TMS":
            case "Joni04":
            case "Gaduso11":

            case "Marcl_Gengsch":
            case "siegfried55b":
            case "SnoobyTV":
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " Got Fucked up" + ChatColor.RESET);
                break;
            case "Katoka47":
                Bukkit.broadcastMessage(ChatColor.BLUE + "Das find ich jetzt echt scheiße von dir!" + ChatColor.RESET);
                break;
            case "guzms":
                Bukkit.broadcastMessage(ChatColor.RED + "guzms just fucking died" + ChatColor.RESET);
                break;
            default:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " hat noch keinen Spezifischen Tod ausgewählt" + ChatColor.RESET);
                break;
        }

        /*switch (Objects.requireNonNull(victim.getLastDamageCause()).getCause()) { // CAUSE TODE
            case ENTITY_ATTACK:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " got fucked in the ass by " + victim.getLastDamageCause().getEntity().getName() + ChatColor.RESET);
                break;
            case LAVA:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " ist zerwchmolzen" + ChatColor.RESET);
                break;
            case ENTITY_EXPLOSION:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde in Fetzen zerissen" + ChatColor.RESET);
                break;
            case BLOCK_EXPLOSION:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " wurde von einem Block in Fetzen zerissen" + ChatColor.RESET);
                break;
            case ENTITY_SWEEP_ATTACK:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " ist Colateralschaden" + ChatColor.RESET);
                break;
            case FIRE:
                Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " ist jetzt Holzkohle" + ChatColor.RESET);
                break;
        }*/

    }
}
