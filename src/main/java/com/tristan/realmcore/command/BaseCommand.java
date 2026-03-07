package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand {
    protected final RealmCorePlugin plugin;

    protected BaseCommand(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    protected Player requirePlayer(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            Chat.send(sender, "&cOnly players can use this command.");
            return null;
        }
        return player;
    }
}
