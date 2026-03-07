package com.tristan.realmcore.command;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class RtpCommand extends BaseCommand implements CommandExecutor {
    public RtpCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;

        long cooldown = plugin.getConfig().getLong("rtp.cooldown-seconds", 30) * 1000L;
        if (plugin.getCooldownManager().isOnCooldown("rtp", player.getUniqueId(), cooldown)) {
            long left = plugin.getCooldownManager().getRemaining("rtp", player.getUniqueId(), cooldown) / 1000L;
            Chat.send(player, "&cRTP cooldown active: &f" + left + "s");
            return true;
        }

        World world = player.getWorld();
        int radius = plugin.getConfig().getInt("rtp.radius", 1500);
        int x = ThreadLocalRandom.current().nextInt(-radius, radius + 1);
        int z = ThreadLocalRandom.current().nextInt(-radius, radius + 1);
        int y = world.getHighestBlockYAt(x, z) + 1;
        Location target = new Location(world, x + 0.5, y, z + 0.5);

        player.teleport(target);
        plugin.getCooldownManager().set("rtp", player.getUniqueId());
        Chat.send(player, "&aRandomly teleported.");
        return true;
    }
}
