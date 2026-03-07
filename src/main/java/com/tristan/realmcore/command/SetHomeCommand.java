package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand extends BaseCommand implements CommandExecutor {
    public SetHomeCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        String name = args.length > 0 ? args[0] : "home";
        boolean success = plugin.getHomeManager().setHome(player.getUniqueId(), name, player.getLocation());
        if (!success) {
            Chat.send(player, "&cYou reached your maximum homes.");
            return true;
        }
        plugin.getHomeManager().save();
        Chat.send(player, "&aHome &f" + name + " &aset.");
        return true;
    }
}
