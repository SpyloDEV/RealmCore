package com.tristan.realmcore.manager;

import com.tristan.realmcore.RealmCorePlugin;
import com.tristan.realmcore.util.Chat;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportRequestManager {
    public enum RequestType { TO_TARGET, TARGET_TO_REQUESTER }

    public record TeleportRequest(UUID requester, UUID target, RequestType type, long createdAt) {}

    private final RealmCorePlugin plugin;
    private final Map<UUID, TeleportRequest> incomingRequests = new HashMap<>();

    public TeleportRequestManager(RealmCorePlugin plugin) {
        this.plugin = plugin;
    }

    public void createRequest(Player requester, Player target, RequestType type) {
        TeleportRequest request = new TeleportRequest(requester.getUniqueId(), target.getUniqueId(), type, System.currentTimeMillis());
        incomingRequests.put(target.getUniqueId(), request);
    }

    public TeleportRequest getIncoming(UUID target) {
        TeleportRequest request = incomingRequests.get(target);
        if (request == null) return null;

        long maxAge = plugin.getConfig().getLong("teleport.request-expire-seconds", 60) * 1000L;
        if (System.currentTimeMillis() - request.createdAt() > maxAge) {
            incomingRequests.remove(target);
            return null;
        }
        return request;
    }

    public void remove(UUID target) {
        incomingRequests.remove(target);
    }

    public void accept(Player target) {
        TeleportRequest request = getIncoming(target.getUniqueId());
        if (request == null) {
            Chat.send(target, "&cYou have no pending teleport request.");
            return;
        }

        Player requester = plugin.getServer().getPlayer(request.requester());
        if (requester == null) {
            remove(target.getUniqueId());
            Chat.send(target, "&cThe requester is no longer online.");
            return;
        }

        if (request.type() == RequestType.TO_TARGET) {
            requester.teleport(target.getLocation());
            Chat.send(requester, "&aTeleport request accepted.");
            Chat.send(target, "&aYou accepted the teleport request.");
        } else {
            target.teleport(requester.getLocation());
            Chat.send(requester, "&aTeleport-here request accepted.");
            Chat.send(target, "&aYou accepted the teleport-here request.");
        }
        remove(target.getUniqueId());
    }

    public void deny(Player target) {
        TeleportRequest request = getIncoming(target.getUniqueId());
        if (request == null) {
            Chat.send(target, "&cYou have no pending teleport request.");
            return;
        }

        Player requester = plugin.getServer().getPlayer(request.requester());
        if (requester != null) {
            Chat.send(requester, "&cYour teleport request was denied.");
        }
        Chat.send(target, "&eTeleport request denied.");
        remove(target.getUniqueId());
    }
}
