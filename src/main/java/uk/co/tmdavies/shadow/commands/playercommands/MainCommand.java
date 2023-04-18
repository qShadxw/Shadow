package uk.co.tmdavies.shadow.commands.playercommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.tmdavies.shadow.commands.ShadowCommand;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class MainCommand extends ShadowCommand {
    public MainCommand() {
        super("shadow", (customCommand) -> {
            CommandSender sender = customCommand.getSender();

            if (!sender.hasPermission("shadow.admin")) {
                sender.sendMessage(ShadowUtils.Chat("&cYou do not have permission to execute this command."));
                
                return;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage("&7Shadow");
                sender.sendMessage("&7Current Version: 0.1.0-DEV");

                return;
            }

            Player player = (Player) sender;

            ShadowUtils.sendCenteredMessage(player, "&7&lShadow");
            player.sendMessage(" ");
            ShadowUtils.sendCenteredMessage(player, "&7Current Version: " + ShadowUtils.colorRainbow("0.1.0-DEV Alpha") + "&7.");
            ShadowUtils.sendCenteredMessage(player, "&7Only for personal and friend use. By Carbonate/Tyler.");
            ShadowUtils.sendCenteredMessage(player, "&7&ohttps://github.com/qShadxw");
        });
    }
}
