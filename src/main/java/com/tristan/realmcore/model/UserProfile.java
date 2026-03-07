package com.tristan.realmcore.model;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserProfile {
    private final UUID uuid;
    private final Map<String, Location> homes = new HashMap<>();
    private long lastDailyClaim;
    private boolean starterClaimed;

    public UserProfile(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<String, Location> getHomes() {
        return homes;
    }

    public long getLastDailyClaim() {
        return lastDailyClaim;
    }

    public void setLastDailyClaim(long lastDailyClaim) {
        this.lastDailyClaim = lastDailyClaim;
    }

    public boolean isStarterClaimed() {
        return starterClaimed;
    }

    public void setStarterClaimed(boolean starterClaimed) {
        this.starterClaimed = starterClaimed;
    }
}
