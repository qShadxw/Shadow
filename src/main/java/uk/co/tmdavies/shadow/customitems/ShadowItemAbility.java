package uk.co.tmdavies.shadow.customitems;

import org.bukkit.entity.Player;

public interface ShadowItemAbility {

    String getName();

    int getLevel();

    String[] getLore();

    int getCooldown();

    void runAbility(Player player);

}
