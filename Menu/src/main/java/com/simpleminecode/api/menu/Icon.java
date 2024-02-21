package com.simpleminecode.api.menu;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public abstract class Icon {
    private final ItemStack item;
    private final Consumer<Player> action;
    private final Menu menu;

    public Icon(Menu menu, ItemStack item, String displayName, List<String> lore, boolean glowing) {
        this(menu, item, displayName, lore, player -> {}, glowing);
    }

    public Icon(Menu menu, ItemStack item, String displayName, List<String> lore, Consumer<Player> action, boolean glowing) {
        final ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            lore.forEach(line -> ChatColor.translateAlternateColorCodes('&', line));

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName.trim()));
            meta.setLore(lore);

            if (glowing) {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            item.setItemMeta(meta);
        }

        this.item = item;
        this.action = action;
        this.menu = menu;
    }

    public final ItemStack getItem() {
        return item;
    }

    public final void click(Player player) {
        action.accept(player);
    }
}
