package uk.co.tmdavies.shadow.commands.playercommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.commands.ShadowCommand;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class DumpCommand extends ShadowCommand {
    public DumpCommand() {
        super("dump", (customCommand) -> {
            CommandSender sender = customCommand.getSender();

            if (!sender.hasPermission("shadow.admin")) {
                sender.sendMessage(ShadowUtils.Chat("&cYou do not have permission to execute tihs command."));
                return;
            }

            String[] args = customCommand.getArgs();
            boolean isConsole = !(sender instanceof Player);

            switch (args.length) {
                case 0:
                    if (isConsole) {
                        sender.sendMessage(ShadowUtils.Chat("&cOnly players may execute this command with no args."));
                        return;
                    }

                    Player player = (Player) sender;

                    if (!Shadow.playerCache.containsKey(player)) {
                        player.sendMessage(ShadowUtils.Chat("&cERROR Your player profile has a fatal error. Please contact an administrator immediately!"));
                        return;
                    }

                    sender.sendMessage(ShadowUtils.Colour("&a" + Shadow.playerCache.get(player).toString()));

                    break;
                case 1:
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target == null || !target.isOnline()) {
                        sender.sendMessage(ShadowUtils.Chat("&cPlayer is offline."));
                        return;
                    }

                    if (!Shadow.playerCache.containsKey(target)) {
                        sender.sendMessage(ShadowUtils.Chat("&cERROR " + target.getName() + "'s profile has a fatal error. Please contact an administrator immediately!"));
                        return;
                    }

                    sender.sendMessage(ShadowUtils.Colour("&a" + Shadow.playerCache.get(target).toString()));

                    break;
                default:
                    sender.sendMessage(ShadowUtils.Chat("&cUsage: /dump <player>"));

                    break;
            }

        });
    }
}
