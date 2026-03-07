package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends BaseCommand implements CommandExecutor {
    public SetSpawnCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        if (!player.hasPermission("realmcore.spawn.set")) return true;
        plugin.getSpawnManager().setSpawn(player.getLocation());
        plugin.getSpawnManager().save();
        Chat.send(player, "&aServer spawn updated.");
        return true;
    }
}
