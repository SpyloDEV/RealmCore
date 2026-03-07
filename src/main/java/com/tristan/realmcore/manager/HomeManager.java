package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.model.UserProfile;
import org.bukkit.Location;

import java.util.Map;
import java.util.UUID;

public class HomeManager {
    private final RealmCorePlugin plugin;
    private final UserDataManager userDataManager;

    public HomeManager(RealmCorePlugin plugin, UserDataManager userDataManager) {
        this.plugin = plugin;
        this.userDataManager = userDataManager;
    }

    public boolean setHome(UUID uuid, String name, Location location) {
        int maxHomes = plugin.getConfig().getInt("homes.default-max", 3);
        UserProfile profile = userDataManager.getOrCreate(uuid);
        if (!profile.getHomes().containsKey(name.toLowerCase()) && profile.getHomes().size() >= maxHomes) {
            return false;
        }
        profile.getHomes().put(name.toLowerCase(), location);
        return true;
    }

    public Location getHome(UUID uuid, String name) {
        return userDataManager.getOrCreate(uuid).getHomes().get(name.toLowerCase());
    }

    public Map<String, Location> getHomes(UUID uuid) {
        return userDataManager.getOrCreate(uuid).getHomes();
    }

    public void save() {
        userDataManager.saveAll();
    }

    public void reload() {
        userDataManager.reload();
    }
}
