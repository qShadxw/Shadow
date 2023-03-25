package uk.co.tmdavies.shadow.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CustomCommand {

    private CommandSender sender;
    private Command cmd;
    private String string;
    private String[] args;

    public CustomCommand(CommandSender sender, Command cmd, String string, String[] args) {
        this.sender = sender;
        this.cmd = cmd;
        this.string = string;
        this.args = args;
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public Command getCommand() {
        return this.cmd;
    }

    public String getRaw() {
        return this.string;
    }

    public String[] getArgs() {
        return args;
    }

}
