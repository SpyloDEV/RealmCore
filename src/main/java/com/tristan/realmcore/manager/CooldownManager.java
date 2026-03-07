package com.tristan.realmcore.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    public boolean isOnCooldown(String key, UUID uuid, long cooldownMillis) {
        long remaining = getRemaining(key, uuid, cooldownMillis);
        return remaining > 0;
    }

    public long getRemaining(String key, UUID uuid, long cooldownMillis) {
        long last = cooldowns.computeIfAbsent(key, ignored -> new HashMap<>())
                .getOrDefault(uuid, 0L);
        long now = System.currentTimeMillis();
        long end = last + cooldownMillis;
        return Math.max(0, end - now);
    }

    public void set(String key, UUID uuid) {
        cooldowns.computeIfAbsent(key, ignored -> new HashMap<>()).put(uuid, System.currentTimeMillis());
    }
}
