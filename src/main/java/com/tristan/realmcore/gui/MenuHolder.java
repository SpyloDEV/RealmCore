package com.tristan.realmcore.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuHolder implements InventoryHolder {
    private final MenuType type;
    private Inventory inventory;

    public MenuHolder(MenuType type) {
        this.type = type;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public MenuType getType() {
        return type;
    }
}
