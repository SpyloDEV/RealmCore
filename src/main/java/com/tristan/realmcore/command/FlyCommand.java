package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends BaseCommand implements CommandExecutor {
    public FlyCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        boolean enabled = !player.getAllowFlight();
        player.setAllowFlight(enabled);
        player.setFlying(enabled);
        Chat.send(player, enabled ? "&aFlight enabled." : "&cFlight disabled.");
        return true;
    }
}
