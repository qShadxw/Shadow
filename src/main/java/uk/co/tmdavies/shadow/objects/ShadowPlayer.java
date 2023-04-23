package uk.co.tmdavies.shadow.objects;

import org.bukkit.entity.Player;
import uk.co.tmdavies.shadow.objects.managers.ManaManager;
import uk.co.tmdavies.shadow.objects.managers.SkillsManager;

public class ShadowPlayer {

    private final Player player;
    private int level;
    private final SkillsManager skillsManager;
    private final ManaManager manaManager;

    public ShadowPlayer(Player player) {
        this.player = player;
        this.level = 1;
        this.skillsManager = new SkillsManager(this);
        this.manaManager = new ManaManager(this);
    }

    // Getters
    public Player getPlayer() {
        return this.player;
    }

    public int getLevel() {
        return this.level;
    }

    public SkillsManager getSkillsManager() {
        return this.skillsManager;
    }

    public ManaManager getManaManager() {
        return this.manaManager;
    }

    // Setters
    public void setLevel(int level) {
        this.level = level;
    }

    // Adders
    public void addLevel(int level) {
        this.level += level;
    }

    // Removes
    public void removeLevel(int level) {
        this.level -= level;
    }

    // toString
    @Override
    public String toString() {
        return "ShadowPlayer["
                + "Player='" + this.player.getName()
                + "', UUID='" + this.player.getUniqueId().toString()
                + "', Level='" + this.level
                + "', " + this.skillsManager.toString()
                + ", " + this.manaManager.toString()
                + "]";
    }

}
