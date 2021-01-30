package at.ahit.server.listeners;

import at.ahit.server.jobs.*;
import at.ahit.server.overlays.Auction;
import at.ahit.server.overlays.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        String name = event.getView().getTitle();
        switch (name){
            case "Job":
                Menu.onJobsUse(event);
                break;
            case "Miner":
                Miner.onMinerJobsUse(event);
                break;
            case "Farmer":
                Farmer.onFarmerJobsUse(event);
                break;
            case "Hunter":
                Hunter.onHunterJobsUse(event);
                break;
            case "Lumberjack":
                Lumberjack.onLumberjackJobsUse(event);
                break;
            case "MonsterHunter":
                MonsterHunter.onMonsterHunterJobsUse(event);
                break;
            case "Wizard":
                Wizard.onWizardJobsUse(event);
                break;
            case "§4Auction":
                Auction.onAuctionUse(event);
                break;
            case "§4Select_Price":
                Auction.sellItem(event);
                break;
        }
    }
}
