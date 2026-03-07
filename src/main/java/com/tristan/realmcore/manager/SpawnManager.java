package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.storage.YamlFile;
import com.tristan.realmcore.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class SpawnManager {
    private final YamlFile file;
    private Location spawn;

    public SpawnManager(RealmCorePlugin plugin) {
        this.file = new YamlFile(plugin, "spawn.yml");
        load();
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getSpawn() {
        return spawn;
    }

    private void load() {
        ConfigurationSection section = file.getConfig().getConfigurationSection("spawn");
        spawn = LocationUtil.readLocation(section);
    }

    public void save() {
        file.getConfig().set("spawn", null);
        ConfigurationSection section = file.getConfig().createSection("spawn");
        LocationUtil.saveLocation(section, spawn);
        file.save();
    }

    public void reload() {
        file.reload();
        load();
    }
}
