package com.tristan.realmcore.util;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Chat {
    private Chat() {}

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public static String msg(RealmCorePlugin plugin, String path, String fallback) {
        YamlConfiguration messages = YamlConfiguration.loadConfiguration(
                plugin.getDataFolder().toPath().resolve("messages.yml").toFile()
        );
        return color(messages.getString(path, fallback));
    }
}
