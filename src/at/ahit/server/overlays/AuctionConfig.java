package at.ahit.server.overlays;

import at.ahit.server.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class AuctionConfig extends YamlConfiguration {
    private static AuctionConfig config;

    public static AuctionConfig getConfig(String name) {
        if (config == null) {
            config = new AuctionConfig(name);
        }
        return config;
    }

    private Main plugin;
    private File configFile;

    public AuctionConfig(String name) {
        plugin = Main.getPlugin(Main.class);
        configFile = new File(plugin.getDataFolder(), name+".yml");
        reload();
    }

    public void reload() {
        try {
            super.load(configFile);
        } catch (Exception e) {
        }
    }

    public void save() {
        try {
            super.save(configFile);
        } catch (Exception e) {
        }
    }

    public void Save(String path, Object obj) {
        config.set(path, obj);
        config.save();
    }
}
