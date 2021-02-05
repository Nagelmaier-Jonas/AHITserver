package at.ahit.server.villagerShop;

import at.ahit.server.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class protectConfig extends YamlConfiguration {
    private static protectConfig config;

    public static protectConfig getConfig(String name) {
        if (config == null) {
            config = new protectConfig(name);
        }
        return config;
    }

    private Main plugin;
    private File configFile;

    public protectConfig(String name) {
        plugin = Main.getPlugin(Main.class);
        configFile = new File(plugin.getDataFolder(), name+".yml");
        reload();
    }

    // this method is alternative to the super method to avoid having to place try/catches in your code.
    public void reload() {
        try {
            super.load(configFile);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    // this method is alternative to the super method to avoid having to place try/catches in your code.
    public void save() {
        try {
            super.save(configFile);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void Save(String path, Object obj) {
        config.set(path, obj);
        config.save();
    }

    // this method works in the same way plugin.saveDefaultConfig() does
    //public void saveDefault() {
    //plugin.saveResource("test.yml", true);
    //}
}