package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.storage.YamlFile;
import com.tristan.realmcore.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class WarpManager {
    private final YamlFile file;
    private final Map<String, Location> warps = new HashMap<>();

    public WarpManager(RealmCorePlugin plugin) {
        this.file = new YamlFile(plugin, "warps.yml");
        load();
    }

    public void setWarp(String name, Location location) {
        warps.put(name.toLowerCase(), location);
    }

    public Location getWarp(String name) {
        return warps.get(name.toLowerCase());
    }

    public Map<String, Location> getWarps() {
        return warps;
    }

    private void load() {
        warps.clear();
        ConfigurationSection root = file.getConfig().getConfigurationSection("warps");
        if (root == null) return;

        for (String key : root.getKeys(false)) {
            Location location = LocationUtil.readLocation(root.getConfigurationSection(key));
            if (location != null) {
                warps.put(key.toLowerCase(), location);
            }
        }
    }

    public void save() {
        file.getConfig().set("warps", null);
        for (Map.Entry<String, Location> entry : warps.entrySet()) {
            ConfigurationSection section = file.getConfig().createSection("warps." + entry.getKey());
            LocationUtil.saveLocation(section, entry.getValue());
        }
        file.save();
    }

    public void reload() {
        file.reload();
        load();
    }
}
