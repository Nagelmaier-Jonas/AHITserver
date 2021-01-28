package at.ahit.server.jobs;

import at.ahit.server.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Lumberjack implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        int level = (int) Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_LumberjackLevel");
        int playerXp = (int)Main.getConfigFile().get(event.getPlayer().getDisplayName() + "_LumberjackXp");
    }
}
