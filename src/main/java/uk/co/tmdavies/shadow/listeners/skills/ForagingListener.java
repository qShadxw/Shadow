package uk.co.tmdavies.shadow.listeners.skills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import uk.co.tmdavies.shadow.managers.PluginManager;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;

public class ForagingListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getBlock().getType().toString().contains("LOG")) return;

        Player player = event.getPlayer();
        ShadowPlayer shadowPlayer = PluginManager.getPluginInstance().getPlayerManager().getShadowPlayer(player);

        if (shadowPlayer == null) {
            return;
        }

        shadowPlayer.getSkillManager().addForagingXp(1);
    }

}
