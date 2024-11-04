package uk.co.tmdavies.shadow.managers;

import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.commands.ShadowCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class PluginManager {

    private static Shadow plugin;

    public static void setInstance(Shadow instance) {
        plugin = instance;
    }

    public static Shadow getPluginInstance() {
        return plugin;
    }

    public static void registerCommands() {
        // Gets main class loader.
        ClassLoader classLoader = plugin.getClass().getClassLoader();
        try {
            // Gets the class path to the class loader.
            ClassPath path = ClassPath.from(classLoader);

            // Iterates through all the classes in the commands package.
            for (ClassPath.ClassInfo info : path.getTopLevelClassesRecursive("uk.co.tmdavies.shadow.commands")) {
                // Checking
                Shadow.logger.warning("Checking Command: %s", info.getName());

                // Checks if the class is the ShadowCommand class, if so then continue because it's not an actual command.
                if (info.getSimpleName().equals("ShadowCommand")) {
                    continue;
                }

                // Gets the class from the class path.
                Class<?> clazz = Class.forName(info.getName(), true, classLoader);

                // Checks if the class extends ShadowCommand.
                if (ShadowCommand.class.isAssignableFrom(clazz)) {
                    // Creates a new instance of the class.
                    ShadowCommand shadowCommand = (ShadowCommand) clazz.getConstructors()[0].newInstance();

                    // Registers the command and tab completer.
                    registerCommandAndTab(plugin, shadowCommand.getName(), shadowCommand);
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            Shadow.logger.error("Failed to register commands.");
        }
    }

    public static void registerListeners() {
        // Gets main class loader.
        ClassLoader classLoader = plugin.getClass().getClassLoader();
        try {
            // Gets the class path to the class loader.
            ClassPath path = ClassPath.from(classLoader);

            // Iterates through all the classes in the listeners.game package.
            for (ClassPath.ClassInfo info : path.getTopLevelClassesRecursive("uk.co.tmdavies.shadow.listeners")) {

                // Checking
                Shadow.logger.warning("Checking Listener: %s", info.getName());

                // Gets the class from the class path.
                Class<?> clazz = Class.forName(info.getName(), true, classLoader);

                // Checks if the class extends Listener.
                if (Listener.class.isAssignableFrom(clazz)) {
                    // Creates a new instance of the class.
                    Listener listener = (Listener) clazz.getConstructors()[0].newInstance();

                    // Registers the listener.
                    Bukkit.getPluginManager().registerEvents(listener, plugin);

                    Shadow.logger.log("Registering listener: %s", info.getName());
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            Shadow.logger.error("Failed to register events.");
        }
    }

    private static void registerCommandAndTab(Shadow plugin, String command, ShadowCommand shadowCommand) {
        Shadow.logger.log("Registering command: %s", command);

        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(shadowCommand);
        Objects.requireNonNull(plugin.getCommand(command)).setTabCompleter(shadowCommand);
    }

}
