package uk.co.tmdavies.shadow.utils;

import org.bukkit.ChatColor;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.commands.playercommands.ItemCommand;
import uk.co.tmdavies.shadow.listeners.AbilityListener;

public class ShadowUtils {

    private static final String prefix = "&8[&7Shadow&8] &f";

    public static String Colour(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String Chat(String message) {
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    public static void loadFiles(Shadow plugin) {

        // Commands
        new ItemCommand();

        // Listeners
        new AbilityListener(plugin);

    }

}
