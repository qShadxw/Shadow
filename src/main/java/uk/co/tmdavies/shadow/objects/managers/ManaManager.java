package uk.co.tmdavies.shadow.objects.managers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;

public class ManaManager {

    private final ShadowPlayer player;
    private int maxMana;
    private int currentMana;

    public ManaManager(ShadowPlayer player) {
        this.player = player;
        this.maxMana = 100;
        this.currentMana = this.maxMana;
        runManaRegeneration();
    }

    // Getters
    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getManaRegeneration() {
        return this.maxMana / 10;
    }

    public int getCurrentToMax() {
        return this.maxMana - this.currentMana;
    }

    // Setters
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    // Adders
    public void addMaxMana(int amount) {
        this.maxMana += amount;
    }

    public void addCurrentMana(int amount) {
        this.currentMana += amount;
    }

    // Removers
    public void removeMaxMana(int amount) {
        this.maxMana -= amount;
    }

    public void removeCurrentMana(int amount) {
        this.currentMana -= amount;
    }

    // Runnables
    public void runManaRegeneration() {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Check if CurrentMana is higher than MaxMana
                if (getCurrentMana() >= getMaxMana()) {
                    return;
                }

                int manaRegen = getManaRegeneration();

                // Checks if the mana left to regen is less than the current manaRegen
                if (getCurrentToMax() < manaRegen) {
                    setCurrentMana(getMaxMana());
                    return;
                }

                // Regenerates mana
                addCurrentMana(manaRegen);
            }
        }.runTaskTimer(JavaPlugin.getPlugin(Shadow.class), 0, 30L);
    }

    // toString
    @Override
    public String toString() {
        return "ManaManager["
                + "UUID='" + this.player.getPlayer().getUniqueId().toString()
                + "', MaxMana='" + this.maxMana
                + "', CurrentMana='" + this.currentMana
                + "']";
    }

}
