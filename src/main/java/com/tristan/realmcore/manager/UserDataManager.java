package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.model.UserProfile;
import com.tristan.realmcore.storage.YamlFile;
import com.tristan.realmcore.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserDataManager {
    private final RealmCorePlugin plugin;
    private final YamlFile file;
    private final Map<UUID, UserProfile> profiles = new HashMap<>();

    public UserDataManager(RealmCorePlugin plugin) {
        this.plugin = plugin;
        this.file = new YamlFile(plugin, "userdata.yml");
        load();
    }

    public UserProfile getOrCreate(UUID uuid) {
        return profiles.computeIfAbsent(uuid, UserProfile::new);
    }

    private void load() {
        profiles.clear();
        ConfigurationSection root = file.getConfig().getConfigurationSection("players");
        if (root == null) return;

        for (String key : root.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            UserProfile profile = new UserProfile(uuid);
            profile.setLastDailyClaim(root.getLong(key + ".lastDailyClaim"));
            profile.setStarterClaimed(root.getBoolean(key + ".starterClaimed"));

            ConfigurationSection homeSection = root.getConfigurationSection(key + ".homes");
            if (homeSection != null) {
                for (String homeName : homeSection.getKeys(false)) {
                    Location location = LocationUtil.readLocation(homeSection.getConfigurationSection(homeName));
                    if (location != null) {
                        profile.getHomes().put(homeName.toLowerCase(), location);
                    }
                }
            }
            profiles.put(uuid, profile);
        }
    }

    public void saveAll() {
        file.getConfig().set("players", null);
        for (Map.Entry<UUID, UserProfile> entry : profiles.entrySet()) {
            String path = "players." + entry.getKey();
            UserProfile profile = entry.getValue();
            file.getConfig().set(path + ".lastDailyClaim", profile.getLastDailyClaim());
            file.getConfig().set(path + ".starterClaimed", profile.isStarterClaimed());

            for (Map.Entry<String, Location> homeEntry : profile.getHomes().entrySet()) {
                ConfigurationSection section = file.getConfig().createSection(path + ".homes." + homeEntry.getKey());
                LocationUtil.saveLocation(section, homeEntry.getValue());
            }
        }
        file.save();
    }

    public void reload() {
        file.reload();
        load();
    }
}
