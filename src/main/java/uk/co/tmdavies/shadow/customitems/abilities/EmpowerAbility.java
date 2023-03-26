package uk.co.tmdavies.shadow.customitems.abilities;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import uk.co.tmdavies.shadow.apis.CooldownAPI;
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
                "&8&oEmpower yourself with your might,",
                "&8&ogain strength equal to the power",
                "&8&oof this weapon."
        );
    }

    @Override
    public int getCooldown() {
        return 30;
    }

    @Override
    public void runAbility(Player player) {
        if (CooldownAPI.isOnCooldown(getName(), player)) {
            player.sendMessage(
                    ShadowUtils.Colour("&d" + getName() + " &8» &cAbility still on cooldown. ("
                            + CooldownAPI.getCooldownForPlayerInt(getName(), player)
                            + "s)")
            );
            return;
        }

        // Duration = abilityLevel * 10 second (in ticks, 30 ticks = 1 second)
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (10   * 30), this.abilityLevel-1));
        player.sendMessage(ShadowUtils.Colour("&d" + getName() + " &8» &7Your body has been empowered."));

        CooldownAPI.addCooldown(getName(), player, getCooldown());
    }

    @Override
    public void runArmourAbility(Player player) {

    }

    @Override
    public void stopAbility(Player player) {
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }

}
