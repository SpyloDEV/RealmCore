package com.tristan.realmcore.listener;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerProtectionListener implements Listener {
    private final RealmCorePlugin plugin;

    public PlayerProtectionListener(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (plugin.getSpawnManager().getSpawn() != null) {
            event.setRespawnLocation(plugin.getSpawnManager().getSpawn());
        }
    }

    @EventHandler
    public void onVoidDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID && event.getEntity() instanceof org.bukkit.entity.Player player) {
            if (plugin.getSpawnManager().getSpawn() != null) {
                event.setCancelled(true);
                player.teleport(plugin.getSpawnManager().getSpawn());
            }
        }
    }
}
