package uk.co.tmdavies.shadow.commands.playercommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.tmdavies.shadow.commands.ShadowCommand;
import uk.co.tmdavies.shadow.customitems.ShadowItem;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class ItemCommand extends ShadowCommand {
    public ItemCommand() {
        super("item", (customCommand) -> {

            CommandSender sender = customCommand.getSender();
            String[] args = customCommand.getArgs();

            if (!(sender instanceof Player)) return;
            if (!sender.hasPermission("shadow.admin.item")) {
                sender.sendMessage(ShadowUtils.Chat("&cYou do not have permission to execute this command."));
                return;
            }

            Player player = (Player) sender;

            if (args.length != 1) {
                player.sendMessage(ShadowUtils.Chat("&cUsage: /item <ITEM_NAME>"));
                return;
            }

            String itemName = args[0].replace(" ", "_");
            ShadowItem shadowWeapon = null;

            for (ShadowItem weapon : ShadowItem.values()) {
                if (weapon.name().equals(itemName.toUpperCase())) shadowWeapon = weapon;
            }

            if (shadowWeapon == null) {
                player.sendMessage(ShadowUtils.Chat("&cInvalid Item Name."));
                return;
            }

            player.getInventory().addItem(shadowWeapon.getBuiltItem());
            player.sendMessage(ShadowUtils.Chat("&aGave " + player.getName() + " item &e" + shadowWeapon.name() + "&7."));

        });
    }

}
