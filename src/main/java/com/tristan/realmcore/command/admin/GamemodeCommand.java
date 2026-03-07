package com.tristan.realmcore.command.admin;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.command.BaseCommand;
import com.tristan.realmcore.util.Chat;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand extends BaseCommand implements CommandExecutor {
    public GamemodeCommand(RealmCorePlugin plugin) { super(plugin); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = requirePlayer(sender);
        if (player == null) return true;
        if (args.length == 0) {
            Chat.send(player, "&cUsage: /gm <0|1|2|3>");
            return true;
        }
        GameMode mode;
        switch (args[0]) {
            case "0" -> mode = GameMode.SURVIVAL;
            case "1" -> mode = GameMode.CREATIVE;
            case "2" -> mode = GameMode.ADVENTURE;
            case "3" -> mode = GameMode.SPECTATOR;
            default -> {
                Chat.send(player, "&cInvalid gamemode.");
                return true;
            }
        }
        player.setGameMode(mode);
        Chat.send(player, "&aGamemode updated to &f" + mode.name());
        return true;
    }
}
