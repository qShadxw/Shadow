package uk.co.tmdavies.shadow;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;
import uk.co.tmdavies.shadow.utils.CooldownUtils;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

import java.util.HashMap;

public final class Shadow extends JavaPlugin {

    public static HashMap<Player, ShadowPlayer> playerCache;

    @Override
    public void onLoad() {
        // Creates Cache
        playerCache = new HashMap<>();

        // Loads Cooldowns
        CooldownUtils.createCooldowns();
    }

    @Override
    public void onEnable() {
        // Loads Files
        ShadowUtils.loadFiles(this);
    }

    @Override
    public void onDisable() {
        // Unloads Cooldowns
        CooldownUtils.removeCooldowns();
    }

}
