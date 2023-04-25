package uk.co.tmdavies.shadow.customitems;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityEvent;

import javax.annotation.Nullable;

public interface ShadowItemAbility {

    String getName();

    int getLevel();

    String[] getLore();

    int getCooldown();

    void runAbility(Player player, @Nullable EntityEvent event);

    void runArmourAbility(Player player);

    void stopAbility(Player player);

}
