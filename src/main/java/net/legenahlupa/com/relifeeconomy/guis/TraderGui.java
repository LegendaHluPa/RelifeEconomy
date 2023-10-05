package net.legenahlupa.com.relifeeconomy.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TraderGui {
    private PaginatedGui traderGui;

    public TraderGui() {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text("Торговец"))
                .rows(6)
                .pageSize(28)
                .create();
        this.traderGui = gui;
        new DefaultSettings(gui);
        itemAddLogic();

    }

    public PaginatedGui getTraderGui() {
        return traderGui;
    }

    int amount_item = 1;
    int amount_state = 1;

    private void itemAddLogic() {
        File items = new File("plugins/RelifeEconomy/Items.yml");
        FileConfiguration itemsConfig = YamlConfiguration.loadConfiguration(items);
        File messages = new File("plugins/RelifeEconomy/messages.yml");
        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messages);
        Objects.requireNonNull(itemsConfig.getConfigurationSection("Items")).getKeys(false).forEach(key -> {
            String material = itemsConfig.getString("Items." + key + ".material");
            int slot = itemsConfig.getInt("Items." + key + ".slot");
            String itemName = itemsConfig.getString("Items." + key + ".item_name");
            int amount = itemsConfig.getInt("Items." + key + ".amount");
            int sell = itemsConfig.getInt("Items." + key + ".sell");
            int buy = itemsConfig.getInt("Items." + key + ".buy");

            ItemStack itemStack = new ItemStack(Material.valueOf(material), amount);
            ItemMeta itemMeta = itemStack.getItemMeta();


            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.translateAlternateColorCodes('&', messagesConfig.getString("sell-message").replace("%sell-price%", String.valueOf(sell))));
            lore.add(ChatColor.translateAlternateColorCodes('&', messagesConfig.getString("buy-message").replace("%buy-price%", String.valueOf(buy))));


            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);


            ItemStack itemStackAmount = new ItemStack(Material.SUNFLOWER);
            ItemMeta itemMetaAmount = itemStackAmount.getItemMeta();
            List<String> loreAmount = new ArrayList<>();
            loreAmount.add(0, ChatColor.GREEN + "1");
            loreAmount.add(1, ChatColor.GRAY + "16");
            loreAmount.add(2, ChatColor.GRAY + "32");
            loreAmount.add(3, ChatColor.GRAY + "64");
            loreAmount.add(4, ChatColor.GRAY + "Все");
            itemMetaAmount.setLore(loreAmount);
            itemStackAmount.setItemMeta(itemMetaAmount);
            traderGui.setItem(6, 5, ItemBuilder.from(itemStackAmount).setName("Количество предмета").asGuiItem(event -> {
                event.setResult(Event.Result.DENY);
                itemClickLogic(event);
            }));

            if (slot == 0) {
                traderGui.addItem(ItemBuilder.from(itemStack).asGuiItem(inventoryClickEvent -> {
                    inventoryClickEvent.setResult(Event.Result.DENY);
                    itemClickLogic(inventoryClickEvent);

                }));
            } else {
                traderGui.setItem(slot, ItemBuilder.from(itemStack).asGuiItem(inventoryClickEvent -> {
                    inventoryClickEvent.setResult(Event.Result.DENY);
                    itemClickLogic(inventoryClickEvent);
                }));

            }
        });


    }

    public void itemClickLogic(InventoryClickEvent event) {
        Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
        List<String> lore = new ArrayList<>();
        lore.add(0, ChatColor.GREEN + "1");
        lore.add(1, ChatColor.GRAY + "16");
        lore.add(2, ChatColor.GRAY + "32");
        lore.add(3, ChatColor.GRAY + "64");
        lore.add(4, ChatColor.GRAY + "Все");
        if (event.isLeftClick() && !event.isShiftClick()) {
            player.sendMessage(amount_state + "+");
        }
        if (event.isRightClick() && !event.isShiftClick()) {
            event.getWhoClicked().sendMessage("Правая кнопка");
        }

        if (event.getCurrentItem().getType() == Material.SUNFLOWER) {
            if (event.isLeftClick() && event.isShiftClick()) {
                if (amount_state == 5) {
                    amount_state = 1;
                } else {
                    amount_state++;
                }

                ItemStack itemStack = Objects.requireNonNull(traderGui.getGuiItem(49)).getItemStack();
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLore(changeColor(amount_state, lore));
                itemStack.setItemMeta(itemMeta);
                traderGui.updatePageItem(6, 5, itemStack);
            }
        }

    }


    private List changeColor(int amount_state, List<String> lore) {
        switch (amount_state) {
            case 1:
                lore.set(0, ChatColor.GREEN + "1");
                lore.set(1, ChatColor.GRAY + "16");
                lore.set(2, ChatColor.GRAY + "32");
                lore.set(3, ChatColor.GRAY + "64");
                lore.set(4, ChatColor.GRAY + "Все");
                return lore;
            case 2:
                lore.set(0, ChatColor.GRAY + "1");
                lore.set(1, ChatColor.GREEN + "16");
                lore.set(2, ChatColor.GRAY + "32");
                lore.set(3, ChatColor.GRAY + "64");
                lore.set(4, ChatColor.GRAY + "Все");
                return lore;
            case 3:
                lore.set(0, ChatColor.GRAY + "1");
                lore.set(1, ChatColor.GRAY + "16");
                lore.set(2, ChatColor.GREEN + "32");
                lore.set(3, ChatColor.GRAY + "64");
                lore.set(4, ChatColor.GRAY + "Все");
                return lore;
            case 4:
                lore.set(0, ChatColor.GRAY + "1");
                lore.set(1, ChatColor.GRAY + "16");
                lore.set(2, ChatColor.GRAY + "32");
                lore.set(3, ChatColor.GREEN + "64");
                lore.set(4, ChatColor.GRAY + "Все");
                return lore;
            case 5:
                lore.set(0, ChatColor.GRAY + "1");
                lore.set(1, ChatColor.GRAY + "16");
                lore.set(2, ChatColor.GRAY + "32");
                lore.set(3, ChatColor.GRAY + "64");
                lore.set(4, ChatColor.GREEN + "Все");
                return lore;
        }
        return lore;
    }

    private int getAmount_item(int amount_state) {


        switch (amount_state) {
            case 1:
                return amount_item = 1;
            case 2:
                return amount_item = 16;
            case 3:
                return amount_item = 32;
            case 4:
                return amount_item = 64;
            case 5:
                return -1;
        }
        return amount_state;
    }

}
