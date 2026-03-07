package com.tristan.realmcore.listener;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.model.UserProfile;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerConnectionListener implements Listener {
    private final RealmCorePlugin plugin;

    public PlayerConnectionListener(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserProfile profile = plugin.getUserDataManager().getOrCreate(player.getUniqueId());
        plugin.getVanishManager().applyJoinVisibility(player);

        event.setJoinMessage(Chat.color("&8[&a+&8] &f" + player.getName()));

        if (plugin.getConfig().getBoolean("starter-kit.enabled", true) && !profile.isStarterClaimed()) {
            player.getInventory().addItem(new ItemStack(Material.STONE_SWORD, 1));
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
            player.getInventory().addItem(new ItemStack(Material.TORCH, 32));
            profile.setStarterClaimed(true);
            Chat.send(player, "&aYou received the starter kit.");
        }
    }
}
