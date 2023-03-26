package uk.co.tmdavies.shadow;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.utils.CooldownUtils;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public final class Shadow extends JavaPlugin {

    @Override
    public void onLoad() {
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
