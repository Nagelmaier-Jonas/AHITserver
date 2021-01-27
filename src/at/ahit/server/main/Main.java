package at.ahit.server.main;

import at.ahit.server.commands.StatsCommand;
import at.ahit.server.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static FileConfiguration config = plugin.getConfig();

    private Main() {
    }

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("stats").setExecutor(new StatsCommand());
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        System.out.println("Hallo");
    }

    public static FileConfiguration getConfigFile() {
        return config;
    }

    public static Main getPlugin() {
        return plugin;
    }

    public void Save(String path, Object obj) {
        config.set(path,obj);
        plugin.saveConfig();
    }

    private Object Load(String path, FileConfiguration config) {
        return config.get(path);
    }

}
