package com.tristan.realmcore.gui;

import com.tristan.realmcore.RealmCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

public final class MenuFactory {
    private MenuFactory() {}

    public static Inventory createHomesMenu(RealmCorePlugin plugin, Player player) {
        MenuHolder holder = new MenuHolder(MenuType.HOMES);
        Inventory inv = Bukkit.createInventory(holder, 27, "Your Homes");
        holder.setInventory(inv);

        for (Map.Entry<String, Location> entry : plugin.getHomeManager().getHomes(player.getUniqueId()).entrySet()) {
            ItemStack item = new ItemStack(Material.RED_BED);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§a" + entry.getKey());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7World: " + entry.getValue().getWorld().getName());
            lore.add("§7Click to teleport");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.addItem(item);
        }
        return inv;
    }

    public static Inventory createWarpMenu(RealmCorePlugin plugin) {
        MenuHolder holder = new MenuHolder(MenuType.WARPS);
        Inventory inv = Bukkit.createInventory(holder, 27, "Server Warps");
        holder.setInventory(inv);

        for (Map.Entry<String, Location> entry : plugin.getWarpManager().getWarps().entrySet()) {
            ItemStack item = new ItemStack(Material.ENDER_PEARL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§b" + entry.getKey());
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7World: " + entry.getValue().getWorld().getName());
            lore.add("§7Click to teleport");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.addItem(item);
        }
        return inv;
    }
}
