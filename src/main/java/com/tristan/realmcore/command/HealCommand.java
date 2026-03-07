package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand extends BaseCommand implements CommandExecutor {
    public HealCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getValue());
        Chat.send(player, "&aYou have been healed.");
        return true;
    }
}
