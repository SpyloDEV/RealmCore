package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends BaseCommand implements CommandExecutor {
    public SpawnCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        Location spawn = plugin.getSpawnManager().getSpawn();
        if (spawn == null) {
            Chat.send(player, "&cSpawn is not set yet.");
            return true;
        }
        player.teleport(spawn);
        Chat.send(player, "&aTeleported to spawn.");
        return true;
    }
}
