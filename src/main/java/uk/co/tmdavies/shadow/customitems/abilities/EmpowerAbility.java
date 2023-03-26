package uk.co.tmdavies.shadow.customitems.abilities;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.tmdavies.shadow.customitems.ShadowItemAbility;
import uk.co.tmdavies.shadow.utils.ItemUtils;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class EmpowerAbility implements ShadowItemAbility {

    int abilityLevel;

    public EmpowerAbility(int abilityLevel) {

        this.abilityLevel = abilityLevel;

    }

    @Override
    public String getName() {
        return "Empower";
    }

    @Override
    public int getLevel() {
        return this.abilityLevel;
    }

    @Override
    public String[] getLore() {
        return ItemUtils.generateAbilityLore(this,
                "&7Empower yourself with your might,",
                "&7gain strength equal to the power of",
                "&7this weapon."
        );
    }

    @Override
    public int getCooldown() {
        return 30;
    }

    @Override
    public void runAbility(Player player) {

        // Duration = abilityLevel * 10 second (in ticks, 30 ticks = 1 second)
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (this.abilityLevel * 10) * 30, this.abilityLevel));
        player.sendMessage(ShadowUtils.Colour("&d" + getName() + " &8» &7Your body has been empowered."));

    }

}
