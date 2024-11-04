package uk.co.tmdavies.shadow.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.tmdavies.shadow.managers.PluginManager;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

import java.util.List;

public class MainCommand implements ShadowCommand {
    @Override
    public String getName() {
        return "shadow";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!sender.hasPermission("shadow.use")) {
            sender.sendMessage(ShadowUtils.Chat("&cYou do not have permission to execute this command."));
            return true;
        }

        switch (args.length) {
            case 1:
                if (args[0].equalsIgnoreCase("reload")) {
                    PluginManager.getPluginInstance().getConfigManager().reloadConfigs();

                    sender.sendMessage(ShadowUtils.Chat("&aConfig(s) reloaded."));
                    break;
                }
                if (args[0].equalsIgnoreCase("dump")) {
                    if (!(sender instanceof Player player)) {
                        sender.sendMessage(ShadowUtils.Chat("&cOnly players may execute this command."));

                        break;
                    }

                    ShadowPlayer shadowPlayer = PluginManager.getPluginInstance().getPlayerManager().getShadowPlayer(player);

                    Bukkit.getConsoleSender().sendMessage(shadowPlayer.toString());

                    player.sendMessage(ShadowUtils.Colour("&a%s's Stats:", player.getName()));
                    player.sendMessage(ShadowUtils.Colour("&7Level: %s", shadowPlayer.getLevel()));
                    player.sendMessage(ShadowUtils.Colour("&7XP: %s", shadowPlayer.getExperience()));
                    player.sendMessage(ShadowUtils.Colour("&7Mana: %s", shadowPlayer.getManaManager().getMana()));
                    player.sendMessage(ShadowUtils.Colour("&7Combat Level: %s", shadowPlayer.getSkillManager().getCombat()));
                    player.sendMessage(ShadowUtils.Colour("&7Combat Experience: %s", shadowPlayer.getSkillManager().getCombatXp()));
                    player.sendMessage(ShadowUtils.Colour("&7Foraging Level: %s", shadowPlayer.getSkillManager().getForaging()));
                    player.sendMessage(ShadowUtils.Colour("&7Foraging Experience: %s", shadowPlayer.getSkillManager().getForagingXp()));


                    break;
                }

            case 2:
                if (args[0].equalsIgnoreCase("mana")) {
                    if (!(sender instanceof Player player)) {
                        sender.sendMessage(ShadowUtils.Chat("&cOnly players may execute this command."));
                        break;
                    }

                    ShadowPlayer shadowPlayer = PluginManager.getPluginInstance().getPlayerManager().getShadowPlayer(player);

                    if (shadowPlayer == null) {
                        break;
                    }

                    shadowPlayer.getManaManager().setMana(Integer.parseInt(args[1]));

                    shadowPlayer.sendMessage("&aSet %s's Mana to %s.", player.getName(), args[1]);

                    break;
                }

            default:
                sender.sendMessage(" ");
                sender.sendMessage(ShadowUtils.getCenteredMessage("&7&lShadow &8- &7&lV0.1.0"));
                sender.sendMessage(ShadowUtils.getCenteredMessage("&8&oby Carbonate"));
                sender.sendMessage(" ");
                sender.sendMessage(ShadowUtils.getCenteredMessage("&7/shadow reload - Reloads the configs."));
                sender.sendMessage(" ");
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
        return switch (args.length) {
            case 1 -> List.of("reload", "dump", "mana");
            case 2 -> List.of("<amount>");
            default -> null;
        };
    }
}
