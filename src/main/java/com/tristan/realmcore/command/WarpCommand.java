package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.gui.MenuFactory;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends BaseCommand implements CommandExecutor {
    public WarpCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;

        if (args.length == 0) {
            player.openInventory(MenuFactory.createWarpMenu(plugin));
            return true;
        }

        Location warp = plugin.getWarpManager().getWarp(args[0]);
        if (warp == null) {
            Chat.send(player, "&cWarp not found.");
            return true;
        }
        player.teleport(warp);
        Chat.send(player, "&aTeleported to warp &f" + args[0] + "&a.");
        return true;
    }
}
