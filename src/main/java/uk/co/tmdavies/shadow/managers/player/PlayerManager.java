package uk.co.tmdavies.shadow.managers.player;

import org.bukkit.entity.Player;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;

import java.util.HashMap;

public class PlayerManager {

    private final HashMap<Player, ShadowPlayer> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    public ShadowPlayer getShadowPlayer(Player player) {
        return players.get(player);
    }

    public HashMap<Player, ShadowPlayer> getShadowPlayers() {
        return players;
    }

    public void addShadowPlayer(Player player) {
        if (players.containsKey(player)) {
            throw new RuntimeException(String.format("Player %s already exists.", player.getName()));
        }

        this.players.put(player, new ShadowPlayer(player));
    }

    public void addShadowPlayer(ShadowPlayer player) {
        if (players.containsKey(player.getPlayer())) {
            throw new RuntimeException(String.format("Player %s already exists.", player.getPlayer().getName()));
        }

        this.players.put(player.getPlayer(), player);
    }

    public void removeShadowPlayer(Player player) {
        if (!players.containsKey(player.getPlayer())) {
            throw new RuntimeException(String.format("Player %s does not exist.", player.getName()));
        }

        this.players.remove(player);
    }

    public void removeShadowPlayer(ShadowPlayer player) {
        if (!players.containsKey(player.getPlayer())) {
            throw new RuntimeException(String.format("Player %s does not exist.", player.getPlayer().getName()));
        }

        this.players.remove(player.getPlayer());
    }

    public void saveShadowPlayer(Player player) {
        saveShadowPlayer(getShadowPlayer(player));
    }

    public void saveShadowPlayer(ShadowPlayer player) {
        // Save into ConfigManager.getConfigurationFile("playerdata")

        removeShadowPlayer(player);
    }

    public void saveShadowPlayers() {
        for (ShadowPlayer player : players.values()) {
            saveShadowPlayer(player);
        }
    }

}
