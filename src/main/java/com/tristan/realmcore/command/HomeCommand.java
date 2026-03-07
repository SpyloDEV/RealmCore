package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.gui.MenuFactory;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand extends BaseCommand implements CommandExecutor {
    public HomeCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;

        if (args.length == 0) {
            player.openInventory(MenuFactory.createHomesMenu(plugin, player));
            return true;
        }

        Location home = plugin.getHomeManager().getHome(player.getUniqueId(), args[0]);
        if (home == null) {
            Chat.send(player, "&cHome not found.");
            return true;
        }
        player.teleport(home);
        Chat.send(player, "&aTeleported to home &f" + args[0] + "&a.");
        return true;
    }
}
