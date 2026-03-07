package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DailyCommand extends BaseCommand implements CommandExecutor {
    public DailyCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        plugin.getDailyRewardManager().claim(player);
        return true;
    }
}
