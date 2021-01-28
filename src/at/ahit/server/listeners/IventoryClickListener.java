package at.ahit.server.listeners;

import at.ahit.server.overlays.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        String name = event.getView().getTitle();
        if (name == "Jobs"){
            Menu.onJobsUse(event);
        }
    }
}
