package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAcceptCommand extends BaseCommand implements CommandExecutor {
    public TpAcceptCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        plugin.getTeleportRequestManager().accept(player);
        return true;
    }
}
