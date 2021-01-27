package at.ahit.server.bounty;

import com.google.common.base.Joiner;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bountysettings {

    private static int minBounty = 50;
    private static boolean globalBroadcast = true;
    private static boolean allowAnonymous = true;
    private static boolean enableInAllWorlds = true;
    private static String anonymousName = "anonymous";


    public static int getMin() {
        return minBounty;
    }

    public static boolean allowAnonymous() {
        return allowAnonymous;
    }

    public static boolean shouldBroadcast() {
        return globalBroadcast;
    }

    public static String getAnonymousName() {
        return anonymousName;
    }

    public static void readFromConfig(FileConfiguration config) {
        minBounty = config.getInt("minBounty");
        globalBroadcast = config.getBoolean("globalBroadcast");
        allowAnonymous = config.getBoolean("allowAnonymous");
        anonymousName = config.getString("anonymousName");
        enableInAllWorlds = config.getBoolean("enableInAllWorlds");
        ConfigurationSection colors = config.createSection("colors");
        for (String key : colors.getKeys(false)) {
            ((HashMap) colors).put(key, colors.getString(key));
        }
    }

    public static void writeToConfig(FileConfiguration config) {
        config.set("minBounty", Integer.valueOf(minBounty));
        config.set("globalBroadcast", Boolean.valueOf(globalBroadcast));
        config.set("allowAnonymous", Boolean.valueOf(allowAnonymous));
        config.set("anonymousName", anonymousName);
        config.set("enableInAllWorlds", Boolean.valueOf(enableInAllWorlds));

    }


}
