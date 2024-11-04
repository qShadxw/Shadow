package uk.co.tmdavies.shadow.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.managers.PluginManager;
import uk.co.tmdavies.shadow.utils.ShadowLogger;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Shadow.logger.log(ShadowLogger.Reason.API, "&aConstructing %s...", event.getPlayer().getName());
        try {
            PluginManager.getPluginInstance().getPlayerManager().addShadowPlayer(event.getPlayer());
            Shadow.logger.log(ShadowLogger.Reason.API, "&aConstructed %s.", event.getPlayer().getName());
        } catch (RuntimeException exception) {
            Shadow.logger.error(ShadowLogger.Reason.API, "&cIssue constructing %s. %s", event.getPlayer().getName(), exception.toString());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Shadow.logger.log(ShadowLogger.Reason.API, "&aRemoving %s...", event.getPlayer().getName());
        try {
            PluginManager.getPluginInstance().getPlayerManager().saveShadowPlayer(event.getPlayer());
            Shadow.logger.log(ShadowLogger.Reason.API, "&aSaved %s.", event.getPlayer().getName());
            } catch (RuntimeException exception) {
            Shadow.logger.error(ShadowLogger.Reason.API, "&cIssue removing %s. %s", event.getPlayer().getName(), exception.toString());
        }
    }

}
