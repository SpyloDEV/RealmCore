package com.tristan.realmcore.storage;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlFile {
    private final RealmCorePlugin plugin;
    private final String fileName;
    private File file;
    private FileConfiguration config;

    public YamlFile(RealmCorePlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        setup();
    }

    private void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create file: " + fileName);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save file: " + fileName);
        }
    }
}
