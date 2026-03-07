package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishManager {
    private final RealmCorePlugin plugin;
    private final Set<UUID> vanished = new HashSet<>();

    public VanishManager(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isVanished(UUID uuid) {
        return vanished.contains(uuid);
    }

    public void toggle(Player player) {
        if (isVanished(player.getUniqueId())) {
            vanished.remove(player.getUniqueId());
            for (Player online : plugin.getServer().getOnlinePlayers()) {
                online.showPlayer(plugin, player);
            }
        } else {
            vanished.add(player.getUniqueId());
            for (Player online : plugin.getServer().getOnlinePlayers()) {
                if (!online.hasPermission("realmcore.vanish.see")) {
                    online.hidePlayer(plugin, player);
                }
            }
        }
    }

    public void applyJoinVisibility(Player joined) {
        for (UUID uuid : vanished) {
            Player vanishedPlayer = plugin.getServer().getPlayer(uuid);
            if (vanishedPlayer != null && !joined.hasPermission("realmcore.vanish.see")) {
                joined.hidePlayer(plugin, vanishedPlayer);
            }
        }
    }
}
