package at.ahit.server.overlays;

import at.ahit.server.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MyCustomConfig extends YamlConfiguration {
    private static MyCustomConfig config;

    public static MyCustomConfig getConfig() {
        if (config == null) {
            config = new MyCustomConfig();
        }
        return config;
    }

    private Main plugin;
    private File configFile;

    public MyCustomConfig() {
        plugin = Main.getPlugin(Main.class);
        configFile = new File(plugin.getDataFolder(), "custom.yml");
        saveDefault();
        reload();
    }

    // this method is alternative to the super method to avoid having to place try/catches in your code.
    public void reload() {
        try {
            super.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // this method is alternative to the super method to avoid having to place try/catches in your code.
    public void save() {
        try {
            super.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // this method works in the same way plugin.saveDefaultConfig() does
    public void saveDefault() {
        plugin.saveResource("custom.yml", false);
    }
}