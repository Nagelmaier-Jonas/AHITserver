package at.ahit.server.main;

import at.ahit.server.main.Tobi.Listeners.RightClick;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("Hallo");

        PluginManager manager = Bukkit.getPluginManager();
        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for (Player online : Bukkit.getOnlinePlayers())
                manager.registerEvents(new RightClick(),this);
        }
    }
    public static Main getPlugin() { return plugin; }

}
