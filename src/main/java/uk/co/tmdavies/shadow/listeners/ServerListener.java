package uk.co.tmdavies.shadow.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;

public class ServerListener implements Listener {

    public ServerListener(Shadow plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!Shadow.playerCache.containsKey(player)) {
            Shadow.playerCache.put(player, new ShadowPlayer(player));
        }
    }

}
