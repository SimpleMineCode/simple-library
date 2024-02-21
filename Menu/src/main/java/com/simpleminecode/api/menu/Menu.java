package com.simpleminecode.api.menu;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {
    private final Map<Integer, Icon> icons = new HashMap<>();
    private Player player;

    public void open(Player player) {
        getInventory().clear();

        for (Map.Entry<Integer, Icon> inventoryContent : icons.entrySet())
            getInventory().setItem(inventoryContent.getKey(), inventoryContent.getValue().getItem());

        this.player = player;
        player.openInventory(getInventory());
        onOpen(player);
    }

    public void closeForAll() {
        getInventory().getViewers().forEach(HumanEntity::closeInventory);
    }

    public void click(Player player, int slot) {
        if (icons.containsKey(slot))
            icons.get(slot).click(player);
    }

    public void setIcon(int slot, Icon icon) {
        getInventory().setItem(slot, icon.getItem());
        icons.put(slot, icon);
    }

    public Icon getIconAt(int slot) {
        return icons.get(slot);
    }

    public Map<Integer, Icon> getIcons() {
        return icons;
    }

    public Player getPlayer() {
        return player;
    }

    public void onOpen(Player player) {}
}
