package at.ahit.server.main;

import at.ahit.server.abilities.MinerAbilities;
import at.ahit.server.commands.CoinsCommand;
import at.ahit.server.commands.NeverCommand;
import at.ahit.server.commands.QuestInfoCommand;
import at.ahit.server.commands.StatsCommand;
import at.ahit.server.jobs.Hunter;
import at.ahit.server.jobs.Miner;
import at.ahit.server.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static FileConfiguration config;

    public Main() {
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = plugin.getConfig();
        registerListener();
        registerCommands();
        System.out.println("Hallo, ich lebe");
    }

    public void onDisable(){

    }

    public void registerListener(){
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new Miner(), this);

        manager.registerEvents(new Hunter(),this);
    }

    public void registerCommands(){
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("never").setExecutor(new NeverCommand());
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("questinfo").setExecutor(new QuestInfoCommand());
        getCommand("mine").setExecutor(new MinerAbilities());
    }

    public static FileConfiguration getConfigFile() {
        return config;
    }

    public static Main getPlugin() {
        return plugin;
    }
    /*
    public void Save(String path, Object obj) {
        config.set(path,obj);
        plugin.saveConfig();
    }

    private Object Load(String path, FileConfiguration config) {
        return config.get(path);
    }*/

}
