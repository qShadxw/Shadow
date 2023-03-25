package uk.co.tmdavies.shadow;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public final class Shadow extends JavaPlugin {

    @Override
    public void onEnable() {
        // Loads Commands
        ShadowUtils.loadCommands();

    }

}
