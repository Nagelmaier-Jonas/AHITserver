package at.ahit.server.main;

import at.ahit.server.abilities.MinerAbilities;
import at.ahit.server.commands.*;
import at.ahit.server.jobs.Hunter;
import at.ahit.server.jobs.Lumberjack;
import at.ahit.server.jobs.Miner;
import at.ahit.server.listeners.InventoryClickListener;
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
    }

    public void onDisable(){

    }

    public void registerListener(){
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new InventoryClickListener(), this);
        manager.registerEvents(new Miner(), this);
        manager.registerEvents(new Hunter(),this);
        manager.registerEvents(new Lumberjack(),this);
    }

    public void registerCommands(){
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("questinfo").setExecutor(new QuestInfoCommand());
        getCommand("aquest").setExecutor(new AQuestCommand());
        getCommand("mine").setExecutor(new MinerAbilities());
        getCommand("skillshop").setExecutor(new SkillShopCommand());
    }

    public static FileConfiguration getConfigFile() {
        return config;
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static void Save(String path, Object obj) {
        config.set(path,obj);
        plugin.saveConfig();
    }

    public static Object Load(String path) {
        return config.get(path);
    }

}
