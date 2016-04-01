package me.StevenLawson.TotalFreedomMod.Commands;

import java.util.Arrays;
import java.util.List;
import me.RyanWild.CJFreedomMod.CJFM_Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Run any command on all users, username placeholder = ?.", usage = "/<command> [fluff] ? [fluff] ?")
public class Command_wildcard extends TFM_Command
{
    private final List<String> blocked = Arrays.asList("wildcard", "gtfo", "saaddme", "saadd", "saconfig", "kick", "doom", "doomhammer", "sys", "smite", "noise", "deafen", "stop", "nope", "glist", "impl");

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        String base_command = StringUtils.join(args, " ");

        for (String blw : blocked)
        {
            if (base_command.contains(blw) && !CJFM_Util.SYSADMINS.contains(sender.getName().toLowerCase()) && !CJFM_Util.EXECUTIVES.contains(sender.getName()))
            {
                playerMsg("You used a forbidden command within your wildcard, try again, ommitting said command.", ChatColor.RED);
                return true;
            }
        }

        for (Player player : server.getOnlinePlayers())
        {
            String out_command = base_command.replaceAll("\\x3f", player.getName());
            playerMsg("Running Command: " + out_command);
            server.dispatchCommand(sender, out_command);
        }
        return true;
    }
}
