package uk.co.tmdavies.shadow.managers;

import uk.co.tmdavies.shadow.utils.ShadowConfig;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

import java.util.HashMap;

public class ConfigManager {

    private HashMap<String, ShadowConfig> configurationFiles;

    public ConfigManager() {
        initFiles();
    }

    public void initFiles() {
        // Init HashMap
        configurationFiles = new HashMap<>();

        // Init Configs
        configurationFiles.put("lang", new ShadowConfig("sample_lang.yml", false, true, true, "lang.yml"));
        configurationFiles.put("config", new ShadowConfig("sample_config.yml", false, true, true, "config.yml"));
        configurationFiles.put("playerdata", new ShadowConfig("playerdata.yml", false, true));
    }

    public ShadowConfig getConfigurationFile(String configFile) {
        return configurationFiles.get(configFile);
    }

    public String getColouredTextFromLang(String path) {
        ShadowConfig config = getConfigurationFile("lang");

        return ShadowUtils.colourRaw(config.getString(path)
                .replace("%prefix%", config.getString("Prefix")));
    }

    public void reloadConfigs() {
        configurationFiles.values().forEach(ShadowConfig::load);
    }

}
