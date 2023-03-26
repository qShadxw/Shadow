package uk.co.tmdavies.shadow.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.customitems.ShadowItem;
import uk.co.tmdavies.shadow.utils.ItemUtils;

public class AbilityListener implements Listener {

    public AbilityListener(Shadow plugin) {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction().equals(Action.LEFT_CLICK_AIR)
            || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;

        if (player.getInventory().getItemInMainHand() == null
            || player.getInventory().getItemInMainHand().getType() == Material.AIR) return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        if (!container.has(ItemUtils.getDefaultItemKey(), PersistentDataType.STRING)) return;

        ShadowItem shadowItem = ShadowItem.getByKey(container.get(ItemUtils.getDefaultItemKey(), PersistentDataType.STRING));

        if (shadowItem == null) return;
        if (shadowItem.getAbility() == null) return;

        shadowItem.getAbility().runAbility(player);

    }

}
