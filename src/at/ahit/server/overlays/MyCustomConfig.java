package at.ahit.server.overlays;

import at.ahit.server.main.Main;
import at.ahit.server.enums.Color;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MyCustomConfig extends YamlConfiguration {
    private static MyCustomConfig config1;

    public static MyCustomConfig getConfig() {
        if (config1 == null) {
            //System.out.println(Color.RED + "is null" + Color.RESET);
            config1 = new MyCustomConfig();
        }
        //System.out.println(Color.RED + "is not null" + Color.RESET);
        return config1;
    }

    private Main plugin;
    private File configFile;

    public MyCustomConfig() {
        plugin = Main.getPlugin(Main.class);
        configFile = new File(plugin.getDataFolder(), "test.yml");
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

    // this method works in the same way plugin.saveDefaultConfig() does
    //public void saveDefault() {
        //plugin.saveResource("test.yml", true);
    //}
}