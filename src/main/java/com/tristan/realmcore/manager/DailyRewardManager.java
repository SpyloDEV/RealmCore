package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.model.UserProfile;
import com.tristan.realmcore.util.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.time.Instant;

public class DailyRewardManager {
    private final RealmCorePlugin plugin;
    private final UserDataManager userDataManager;

    public DailyRewardManager(RealmCorePlugin plugin, UserDataManager userDataManager) {
        this.plugin = plugin;
        this.userDataManager = userDataManager;
    }

    public boolean canClaim(Player player) {
        UserProfile profile = userDataManager.getOrCreate(player.getUniqueId());
        long cooldown = plugin.getConfig().getLong("daily.cooldown-hours", 24) * 3600_000L;
        return System.currentTimeMillis() - profile.getLastDailyClaim() >= cooldown;
    }

    public String getRemaining(Player player) {
        UserProfile profile = userDataManager.getOrCreate(player.getUniqueId());
        long cooldown = plugin.getConfig().getLong("daily.cooldown-hours", 24) * 3600_000L;
        long millis = cooldown - (System.currentTimeMillis() - profile.getLastDailyClaim());
        if (millis <= 0) return "ready";
        Duration d = Duration.between(Instant.now(), Instant.now().plusMillis(millis));
        long hours = d.toHours();
        long minutes = d.toMinutesPart();
        return hours + "h " + minutes + "m";
    }

    public void claim(Player player) {
        if (!canClaim(player)) {
            Chat.send(player, "&cYou already claimed your daily reward. Remaining: &f" + getRemaining(player));
            return;
        }
        UserProfile profile = userDataManager.getOrCreate(player.getUniqueId());
        int diamonds = plugin.getConfig().getInt("daily.reward.diamonds", 3);
        int food = plugin.getConfig().getInt("daily.reward.golden-apples", 2);

        player.getInventory().addItem(new ItemStack(Material.DIAMOND, diamonds));
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, food));
        profile.setLastDailyClaim(System.currentTimeMillis());

        Chat.send(player, "&aDaily reward claimed successfully.");
    }
}
