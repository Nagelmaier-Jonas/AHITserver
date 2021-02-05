package at.ahit.server.villagerShop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class protectCommand implements CommandExecutor {
    public static protectConfig config = protectConfig.getConfig("protectedBlocks");

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;

        if(config.get("Loc1x") == null)
            config.Save("Loc1x", "not set");
        if(config.get("Loc1y") == null)
            config.Save("Loc1y", "not set");
        if(config.get("Loc2x") == null)
            config.Save("Loc2x", "not set");
        if(config.get("Loc2y") == null)
            config.Save("Loc2y", "not set");
        if(strings.length == 0) {
            ItemStack item = new ItemStack(Material.WOODEN_AXE, 1);
            ItemMeta im = item.getItemMeta();
            //im.setLore(Arrays.asList("Protect an area"));
            im.setDisplayName("Protector");
            item.setItemMeta(im);
            ((Player) commandSender).getInventory().addItem(item);
        }


        if(strings.length == 1)
            if(strings[0].equals("reload"))
                useProtector.initList(((Player) commandSender).getLocation());

        return false;
    }
}
