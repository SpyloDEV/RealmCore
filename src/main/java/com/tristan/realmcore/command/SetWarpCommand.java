package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand extends BaseCommand implements CommandExecutor {
    public SetWarpCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        if (args.length == 0) {
            Chat.send(player, "&cUsage: /setwarp <name>");
            return true;
        }
        plugin.getWarpManager().setWarp(args[0], player.getLocation());
        plugin.getWarpManager().save();
        Chat.send(player, "&aWarp &f" + args[0] + " &aset.");
        return true;
    }
}
