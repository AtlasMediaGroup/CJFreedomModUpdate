package me.RyanWild.CJFreedomMod.Commands;

import java.util.Random;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "You get a potato, you get a potato, EVERYONE gets a potato!", usage = "/<command>")
public class Command_potato extends CJFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        Random rand = new Random();

        String message = ChatColor.YELLOW + "It is dangerous to go alone. Here, take this!\n"
                + ChatColor.RED + "** potato +1 **\n"
                + ChatColor.GREEN + "" + ChatColor.ITALIC + "Use it wisely. #" + rand.nextInt(10000);

        for (Player player : server.getOnlinePlayers())
        {
            ItemStack heldItem = new ItemStack(Material.POTATO_ITEM, 1);
            player.getInventory().setItem(player.getInventory().firstEmpty(), heldItem);
        }

        TFM_Util.bcastMsg(message);
        return true;
    }
}
