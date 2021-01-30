package at.ahit.server.usefulstuff;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AQuestMethods {

    public static void getInventoryLocation(Material material, Integer amount, Inventory inventory, Player player, Integer reward, Boolean removeitem){

        ItemStack[] itemstack = inventory.getContents();

        /*for (ItemStack i : itemstack) {

            if (i.getType() == material && i.getAmount() >= amount){

                if (removeitem){
                    i.setAmount(i.getAmount() -amount);
                }
                 player.sendMessage(ChatColor.GREEN + "You got " + reward + " Coins!");

            }

        }*/
        if (inventory.getItem(7).getType() == material){
            if (inventory.getItem(7).getAmount() >= amount){

                if (removeitem) {
                    inventory.getItem(7).setAmount(inventory.getItem(7).getAmount() - amount);
                }

                player.sendMessage(ChatColor.GREEN + "You got " + reward + " Coins!");
            }
            else {
                player.sendMessage(ChatColor.RED +  "You don´t have enough of the requested Item!");
            }
        }
        else {
            player.sendMessage(ChatColor.RED +  "You don´t have the quest item on Slot 7!");
        }



    }
}
