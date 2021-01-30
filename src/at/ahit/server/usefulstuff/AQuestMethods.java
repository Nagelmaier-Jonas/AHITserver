package at.ahit.server.usefulstuff;

import at.ahit.server.main.Main;
import at.ahit.server.overlays.Scoreboards;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import static at.ahit.server.main.Main.config;



public class AQuestMethods {

    public static void getInventoryLocation(Material material, Integer amount, Player player, Integer reward, Boolean removeitem, Boolean addnewitem, Material additem, Integer addamount, String rewarditemname){


        for (ItemStack i : player.getInventory()) {
            if (i != null){

                if (i.getType() == material){
                    if (i.getAmount() >= amount) {

                        if (removeitem) {
                            i.setAmount(i.getAmount() - amount);
                        }
                        if (addnewitem){
                            player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(additem, addamount));
                            player.sendMessage(ChatColor.GREEN + "You got " + addamount + " " + ChatColor.AQUA + rewarditemname + " in reward");
                        }
                        Main.Save(player.getDisplayName() + "_Amount", (Integer) Main.Load(player.getDisplayName() + "_Amount") + reward);
                        player.sendMessage(ChatColor.GREEN + "You got " + ChatColor.AQUA + reward + " Coins!");
                        Scoreboards.createScoreboard(config, player);
                        return;
                    }

                }
            }
        }
        player.sendMessage(ChatColor.RED + "You don´t have the Quest Item in your Inventory!");

    }
}
