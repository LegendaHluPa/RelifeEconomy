package net.legenahlupa.com.relifeeconomy.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DefaultSettings {
    public GuiItem fillerGlass = ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).asGuiItem(inventoryClickEvent -> {
        inventoryClickEvent.setResult(Event.Result.DENY);
    });

    public  DefaultSettings(PaginatedGui gui){
        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).setName("Previous").asGuiItem(event -> {
            gui.previous();
            event.setResult(Event.Result.DENY);
        }));

        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).setName("Next").asGuiItem(event -> {
            gui.next();
            event.setResult(Event.Result.DENY);
        }));

        for (int i = 0; i < 10; i++) {
            if (gui.getGuiItem(i) == null){
                gui.setItem(i, fillerGlass);
            }
        }
        gui.setItem(17, fillerGlass);
        gui.setItem(18, fillerGlass);
        gui.setItem(26, fillerGlass);
        gui.setItem(27, fillerGlass);
        gui.setItem(35, fillerGlass);
        gui.setItem(36, fillerGlass);
        for (int i = 44; i < 54; i++) {
            if (gui.getGuiItem(i) == null){
                gui.setItem(i, fillerGlass);
            }

        }
    }
}
