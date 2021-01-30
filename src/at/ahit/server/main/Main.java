package at.ahit.server.main;

import at.ahit.server.abilities.MinerAbilities;
import at.ahit.server.commands.*;
import at.ahit.server.enums.Color;
import at.ahit.server.jobs.*;
import at.ahit.server.listeners.InventoryClickListener;
import at.ahit.server.listeners.JoinListener;
import at.ahit.server.overlays.MyCustomConfig;
import at.ahit.server.usefulstuff.ChristophsTests;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
        System.out.println(Color.GREEN + "Plugin load successful" + Color.RESET);
        registerListInit();
        Miner.startRunnable();
    }

    public void registerListInit() {
        Miner.config1.Save("world", Miner.world);
        Miner.config1.Save("world_nether", Miner.world_nether);
        Miner.config1.Save("world_the_end", Miner.world_the_end);
    }

    public void registerListener(){
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new InventoryClickListener(), this);

        manager.registerEvents(new Miner(), this);
        manager.registerEvents(new Hunter(),this);
        manager.registerEvents(new Lumberjack(),this);
        manager.registerEvents(new Farmer(),this);
        manager.registerEvents(new MonsterHunter(),this);
        manager.registerEvents(new Wizard(),this);

        manager.registerEvents(new ChristophsTests(),this);
    }
    public void registerCommands(){
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand());
        Objects.requireNonNull(getCommand("coins")).setExecutor(new CoinsCommand());
        Objects.requireNonNull(getCommand("questinfo")).setExecutor(new QuestInfoCommand());
        Objects.requireNonNull(getCommand("aquest")).setExecutor(new AQuestCommand());
        Objects.requireNonNull(getCommand("mine")).setExecutor(new MinerAbilities());
        Objects.requireNonNull(getCommand("skillshop")).setExecutor(new SkillShopCommand());
        Objects.requireNonNull(getCommand("setcoins")).setExecutor(new SetCoinsCommand());
        Objects.requireNonNull(getCommand("auction")).setExecutor(new AuctionCommand());
        Objects.requireNonNull(getCommand("setVal")).setExecutor(new SetValCommand());
        Objects.requireNonNull(getCommand("getVal")).setExecutor(new GetValCommand());
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


    /*public static void MakeNewFile() {
        //Make a new File:
        File f = new File(Main.getPlugin().getDataFolder(), "playerPlacesBlocks.yml");

        //Load a FileConfiguration:
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        //Set anything to the file:
        cfg.set("Gaduso", "this_is_the_string");

        //Save it:
        try {
            cfg.save(f);
        } catch (IOException var2) {
        }
        //Retrieve any String:
        String s = cfg.getString("Gaduso");
    }

    public static void SaveNewFile(String path, Object obj) {
        config.set(path,obj);
        plugin.saveConfig();
    }*/
}
