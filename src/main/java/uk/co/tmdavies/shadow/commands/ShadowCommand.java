package uk.co.tmdavies.shadow.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public interface ShadowCommand extends CommandExecutor, TabCompleter {

    String getName();

    @Override
    boolean onCommand(CommandSender sender, Command cmd, String string, String[] args);

    @Override
    List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args);

}
