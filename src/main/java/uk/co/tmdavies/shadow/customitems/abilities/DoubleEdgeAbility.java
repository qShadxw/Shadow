package uk.co.tmdavies.shadow.customitems.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import uk.co.tmdavies.shadow.customitems.ShadowItemAbility;
import uk.co.tmdavies.shadow.utils.ItemUtils;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class DoubleEdgeAbility implements ShadowItemAbility, Listener {

    int abilityLevel;
    boolean active;

    public DoubleEdgeAbility(int abilityLevel) {
        this.abilityLevel = abilityLevel;
        this.active = false;
    }

    @Override
    public String getName() {
        return "Double Edged";
    }

    @Override
    public int getLevel() {
        return this.abilityLevel;
    }

    @Override
    public String[] getLore() {
        return ItemUtils.generateAbilityLore(this,
                "&8&oYour bloodthirsty rage",
                "&8&osharpens the edges of your sword",
                "&8&owhich causes it to do double damage for one swing."
        );
    }

    @Override
    public int getCooldown() {
        return 60;
    }

    @Override
    public void runAbility(Player player, EntityEvent event) {
        this.active = true;
        player.sendMessage(ShadowUtils.Colour("&4&lBloodthirsty &8» &cYou're feeling the taste for blood."));
        onHit((EntityDamageByEntityEvent) event);
    }

    @Override
    public void runArmourAbility(Player player) {

    }

    @Override
    public void stopAbility(Player player) {
        this.active = false;
    }

    public void onHit(EntityDamageByEntityEvent event) {
        if (!this.active) {
            return;
        }

        double damage = event.getDamage();
        damage *= 2;

        event.setDamage(damage);

        this.stopAbility((Player) event.getDamager());
    }

}
