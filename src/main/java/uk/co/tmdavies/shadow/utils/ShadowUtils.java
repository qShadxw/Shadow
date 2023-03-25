package uk.co.tmdavies.shadow.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.reflections.Reflections;
import uk.co.tmdavies.shadow.annotations.ToBeChanged;

import java.util.*;

public class ShadowUtils {

    private static final String prefix = "&8[&7Shadow&8] &f";

    public static String Colour(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String Chat(String message) {
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    @ToBeChanged
    public static void loadFiles(String packagePath) {

        Reflections reflections = new Reflections(packagePath);
        Set<Class<? extends CommandExecutor>> allClasses =
                reflections.getSubTypesOf(CommandExecutor.class);

        for (Class clazz : allClasses) {
            try {
                clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
