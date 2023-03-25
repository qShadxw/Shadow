package uk.co.tmdavies.shadow.commands;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.Shadow;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class ShadowCommand {

    public ShadowCommand(String commandName, Consumer<CustomCommand> commandExecute) {

        Objects.requireNonNull(JavaPlugin.getPlugin(Shadow.class).getCommand(commandName))
                .setExecutor((sender, command, string, args) -> {
                    commandExecute.accept(new CustomCommand(sender, command, string, args));
                    return true;
                });

    }

}
