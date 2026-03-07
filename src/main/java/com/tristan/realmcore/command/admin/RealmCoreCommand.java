package com.tristan.realmcore.command.admin;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RealmCoreCommand implements CommandExecutor {
    private final RealmCorePlugin plugin;

    public RealmCoreCommand(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadAll();
            Chat.send(sender, "&aRealmCore configuration reloaded.");
            return true;
        }

        Chat.send(sender, "&6RealmCore &7- Commands:");
        Chat.send(sender, "&e/realmcore reload &7- Reload configs");
        Chat.send(sender, "&e/home [name] &7- Open homes or teleport");
        Chat.send(sender, "&e/warp [name] &7- Open warps or teleport");
        Chat.send(sender, "&e/tpa <player> &7- Send request");
        Chat.send(sender, "&e/daily &7- Claim daily reward");
        return true;
    }
}
