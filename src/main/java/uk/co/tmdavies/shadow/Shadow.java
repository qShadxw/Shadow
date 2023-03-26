package uk.co.tmdavies.shadow;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import uk.co.tmdavies.shadow.apis.CooldownAPI;
import uk.co.tmdavies.shadow.customitems.ShadowItemAbility;
import uk.co.tmdavies.shadow.utils.CooldownUtils;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

import java.util.HashMap;
import java.util.List;

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
