package uk.co.tmdavies.shadow;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.managers.ConfigManager;
import uk.co.tmdavies.shadow.managers.PluginManager;
import uk.co.tmdavies.shadow.managers.player.PlayerManager;
import uk.co.tmdavies.shadow.utils.ShadowLogger;

public final class Shadow extends JavaPlugin {

    public static ShadowLogger logger;

    // Managers
    private PlayerManager playerManager;
    private ConfigManager configManager;

    @Override
    public void onLoad() {
        // Init Instance
        PluginManager.setInstance(this);

        // Init Logger
        logger = new ShadowLogger();

        // Init Configs
        configManager = new ConfigManager();

        // Init PlayerManager
        playerManager = new PlayerManager();
    }

    @Override
    public void onEnable() {
        // Load Players Already In Server
        loadExistingPlayers();

        // Loading Commands
        PluginManager.registerCommands();

        // Loading Listeners
        PluginManager.registerListeners();

        // Startup Message
        logger.startUp(getDescription().getVersion(), getDescription().getAuthors());
    }

    @Override
    public void onDisable() {
        // Saves Players On Server
        saveExistingPlayers();
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void loadExistingPlayers() {
        for (Player player : getServer().getOnlinePlayers()) {
            getPlayerManager().addShadowPlayer(player);
        }
    }

    public void saveExistingPlayers() {
        for (Player player : getServer().getOnlinePlayers()) {
            getPlayerManager().saveShadowPlayer(player);
        }
    }

}
