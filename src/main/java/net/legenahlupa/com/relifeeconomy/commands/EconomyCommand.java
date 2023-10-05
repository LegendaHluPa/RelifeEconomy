package net.legenahlupa.com.relifeeconomy.commands;

import net.legenahlupa.com.relifeeconomy.guis.TraderGui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EconomyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        assert player != null;
        new TraderGui().getTraderGui().open(player);
        return false;
    }
}
