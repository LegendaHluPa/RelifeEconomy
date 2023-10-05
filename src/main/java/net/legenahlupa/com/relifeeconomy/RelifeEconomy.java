package net.legenahlupa.com.relifeeconomy;

import net.legenahlupa.com.relifeeconomy.commands.EconomyCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class RelifeEconomy extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("economy").setExecutor(new EconomyCommand());
        createFiles();
    }

    @Override
    public void onDisable() {

    }

    private void createFiles(){
        if (!new File(getDataFolder(), "Items.yml").exists()) {
            saveResource("Items.yml", false);
        }
        if (!new File(getDataFolder(), "messages.yml").exists()) {
            saveResource("messages.yml", false);
        }
    }
}
