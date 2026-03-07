package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.manager.TeleportRequestManager.RequestType;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaHereCommand extends BaseCommand implements CommandExecutor {
    public TpaHereCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        if (args.length == 0) {
            Chat.send(player, "&cUsage: /tpahere <player>");
            return true;
        }
        Player target = plugin.getServer().getPlayerExact(args[0]);
        if (target == null || target.equals(player)) {
            Chat.send(player, "&cTarget player not found.");
            return true;
        }
        plugin.getTeleportRequestManager().createRequest(player, target, RequestType.TARGET_TO_REQUESTER);
        Chat.send(player, "&aTeleport-here request sent to &f" + target.getName() + "&a.");
        Chat.send(target, "&e" + player.getName() + " wants you to teleport to them. Use /tpaccept or /tpdeny.");
        return true;
    }
}
