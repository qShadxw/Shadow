package uk.co.tmdavies.shadow.objects.managers;

import uk.co.tmdavies.shadow.objects.ShadowPlayer;

public class SkillsManager {

    private final ShadowPlayer player;
    private int miningLevel;
    private int foragingLevel;
    private int farmingLevel;
    private int combatLevel;

    public SkillsManager(ShadowPlayer player) {
        this.player = player;
        this.miningLevel = 1;
        this.foragingLevel = 1;
        this.farmingLevel = 1;
        this.combatLevel = 1;
    }

    // Getters
    private ShadowPlayer getPlayer() {
        return this.player;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getForagingLevel() {
        return this.foragingLevel;
    }

    public int getFarmingLevel() {
        return this.farmingLevel;
    }

    public int getCombatLevel() {
        return this.combatLevel;
    }

    // Setters
    public void setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
    }

    public void setForagingLevel(int foragingLevel) {
        this.foragingLevel = foragingLevel;
    }

    public void setFarmingLevel(int farmingLevel) {
        this.farmingLevel = farmingLevel;
    }

    public void setCombatLevel(int combatLevel) {
        this.combatLevel = combatLevel;
    }

    // Adders
    public void addMiningLevel(int amount) {
        this.miningLevel += amount;
    }

    public void addForagingLevel(int amount) {
        this.foragingLevel += amount;
    }

    public void addFarmingLevel(int amount) {
        this.farmingLevel += amount;
    }

    public void addCombatLevel(int amount) {
        this.combatLevel += amount;
    }

    // Removers
    public void removeMiningLevel(int amount) {
        this.miningLevel -= amount;
    }

    public void removeForagingLevel(int amount) {
        this.foragingLevel -= amount;
    }

    public void removeFarmingLevel(int amount) {
        this.farmingLevel -= amount;
    }

    public void removeCombatLevel(int amount) {
        this.combatLevel -= amount;
    }

    // toString
    @Override
    public String toString() {
        return "SkillsManager["
                + "UUID='" + this.player.getPlayer().getUniqueId().toString()
                + "', MiningLevel='" + this.miningLevel
                + "', ForagingLevel='" + this.foragingLevel
                + "', FarmingLevel='" + this.farmingLevel
                + "', CombatLevel='" + this.combatLevel
                + "']";
    }
}
