package at.ahit.server.overlays;

import at.ahit.server.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MyCustomConfig extends YamlConfiguration {
    private static MyCustomConfig config;

    public static MyCustomConfig getConfig(String name) {
        if (config == null) {
            //System.out.println(Color.RED + "is null" + Color.RESET);
            config = new MyCustomConfig(name);
        }
        //System.out.println(Color.RED + "is not null" + Color.RESET);
        return config;
    }

    private Main plugin;
    private File configFile;

    public MyCustomConfig(String name) {
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