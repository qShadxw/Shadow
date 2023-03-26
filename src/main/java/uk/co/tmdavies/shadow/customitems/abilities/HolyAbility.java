package uk.co.tmdavies.shadow.customitems.abilities;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.tmdavies.shadow.customitems.ShadowItemAbility;
import uk.co.tmdavies.shadow.utils.ItemUtils;

public class HolyAbility implements ShadowItemAbility {

    @Override
    public String getName() {
        return "Holy";
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public String[] getLore() {
        return ItemUtils.generateAbilityLore(this,
                "&7Fills the wearer with",
                "&egodly &7power."
        );
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public void runAbility(Player player) {

    }

    @Override
    public void runArmourAbility(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 1));
    }

    @Override
    public void stopAbility(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
}
