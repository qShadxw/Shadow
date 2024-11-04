package uk.co.tmdavies.shadow.managers.player;

import org.bukkit.scheduler.BukkitRunnable;
import uk.co.tmdavies.shadow.managers.PluginManager;

public class ManaManager {

    private double maxMana;
    private double mana;

    public ManaManager() {
        this.maxMana = 100;
        this.mana = maxMana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public BukkitRunnable regenerateMana() {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (mana >= maxMana) {
                    return;
                }

                mana += maxMana/10;
            }
        };

        runnable.runTaskTimerAsynchronously(PluginManager.getPluginInstance(), 1, 20);

        return runnable;
    }

    @Override
    public String toString() {
        return String.format("ManaManager[maxMana='%s', mana='%s']", maxMana, mana);
    }
}
