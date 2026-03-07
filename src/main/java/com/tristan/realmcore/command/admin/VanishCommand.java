package com.tristan.realmcore.command.admin;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.command.BaseCommand;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand extends BaseCommand implements CommandExecutor {
    public VanishCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        plugin.getVanishManager().toggle(player);
        boolean vanished = plugin.getVanishManager().isVanished(player.getUniqueId());
        Chat.send(player, vanished ? "&aVanish enabled." : "&cVanish disabled.");
        return true;
    }
}
