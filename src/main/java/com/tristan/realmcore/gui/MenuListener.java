package com.tristan.realmcore.gui;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {
    private final RealmCorePlugin plugin;

    public MenuListener(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof MenuHolder holder)) return;
        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;

        String name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§a", "").replace("§b", "");
        Location target;

        if (holder.getType() == MenuType.HOMES) {
            target = plugin.getHomeManager().getHome(player.getUniqueId(), name);
        } else {
            target = plugin.getWarpManager().getWarp(name);
        }

        if (target == null) {
            Chat.send(player, "&cTarget location no longer exists.");
            return;
        }

        player.closeInventory();
        player.teleport(target);
        Chat.send(player, "&aTeleported successfully.");
    }
}
