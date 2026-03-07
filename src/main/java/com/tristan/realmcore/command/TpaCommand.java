package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.manager.TeleportRequestManager.RequestType;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand extends BaseCommand implements CommandExecutor {
    public TpaCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        if (args.length == 0) {
            Chat.send(player, "&cUsage: /tpa <player>");
            return true;
        }
        Player target = plugin.getServer().getPlayerExact(args[0]);
        if (target == null || target.equals(player)) {
            Chat.send(player, "&cTarget player not found.");
            return true;
        }
        plugin.getTeleportRequestManager().createRequest(player, target, RequestType.TO_TARGET);
        Chat.send(player, "&aTeleport request sent to &f" + target.getName() + "&a.");
        Chat.send(target, "&e" + player.getName() + " wants to teleport to you. Use /tpaccept or /tpdeny.");
        return true;
    }
}
