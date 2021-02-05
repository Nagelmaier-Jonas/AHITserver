package at.ahit.server.main;

import at.ahit.server.abilities.MinerAbilities;
import at.ahit.server.commands.*;
import at.ahit.server.enums.Color;
import at.ahit.server.jobs.*;
import at.ahit.server.listeners.InventoryClickListener;
import at.ahit.server.listeners.JoinListener;
import at.ahit.server.overlays.Auction;
import at.ahit.server.recepies.Wand;
import at.ahit.server.usefulstuff.ChristophsTests;
import at.ahit.server.villagerShop.protectCommand;
import at.ahit.server.villagerShop.setVillagerShop;
import at.ahit.server.villagerShop.useProtector;
import at.ahit.server.villagerShop.villagerShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        registerRecipes();
    }

    public void registerRecipes(){
        Wand.addToServer();
    }

    public void registerListInit() {
        if(Miner.config1.get("world") == null)
            Miner.config1.Save("world", Miner.world);
        if(Miner.config1.get("world_nether") == null)
            Miner.config1.Save("world_nether", Miner.world_nether);
        if(Miner.config1.get("world_the_end") == null)
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
        manager.registerEvents(new Auction(),this);

        manager.registerEvents(new setVillagerShop(), this);

        manager.registerEvents(new ChristophsTests(),this);

        manager.registerEvents(new useProtector(), this);
    }
    public void registerCommands(){
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand());
        Objects.requireNonNull(getCommand("coins")).setExecutor(new CoinsCommand());
        Objects.requireNonNull(getCommand("questinfo")).setExecutor(new QuestInfoCommand());
        Objects.requireNonNull(getCommand("quest")).setExecutor(new AQuestCommand());
        Objects.requireNonNull(getCommand("mine")).setExecutor(new MinerAbilities());
        Objects.requireNonNull(getCommand("skillshop")).setExecutor(new SkillShopCommand());
        Objects.requireNonNull(getCommand("setcoins")).setExecutor(new SetCoinsCommand());
        Objects.requireNonNull(getCommand("auction")).setExecutor(new AuctionCommand());
        Objects.requireNonNull(getCommand("setVal")).setExecutor(new SetValCommand());
        Objects.requireNonNull(getCommand("getVal")).setExecutor(new GetValCommand());

        Objects.requireNonNull(getCommand("shop")).setExecutor(new villagerShop());
        Objects.requireNonNull(getCommand("protect")).setExecutor(new protectCommand());
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
