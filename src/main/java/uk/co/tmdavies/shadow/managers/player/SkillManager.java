package uk.co.tmdavies.shadow.managers.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class SkillManager {

    private final ShadowPlayer player;
    private double combat;
    private double combatXp;
    private double foraging;
    private double foragingXp;

    public SkillManager(ShadowPlayer player) {
        this.player = player;
        combat = 1;
        combatXp = 0;
        foraging = 1;
        foragingXp = 0;
    }

    public double getCombat() {
        return combat;
    }

    public void setCombat(double combat) {
        this.combat = combat;
    }

    public void addCombat(double combat) {
        this.combat += combat;
    }

    public double getCombatXp() {
        return combatXp;
    }

    public void setCombatXp(double combatXp) {
        this.combatXp = combatXp;
    }

    public void addCombatXp(double combatXp) {
        this.combatXp += combatXp;
        announceStatChange("CombatXP", combatXp);
    }

    public double getForaging() {
        return foraging;
    }

    public void setForaging(double foraging) {
        this.foraging = foraging;
    }

    public void addForaging(double foraging) {
        this.foraging += foraging;
    }

    public double getForagingXp() {
        return foragingXp;
    }

    public void setForagingXp(double foragingXp) {
        this.foragingXp = foragingXp;
    }

    public void addForagingXp(double foragingXp) {
        this.foragingXp += foragingXp;
        announceStatChange("Foraging", foragingXp);
    }

    public void announceStatChange(String skill, double amount) {
        this.player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ShadowUtils.Colour("&d%s &8» &a+%s", skill, amount)));
        this.player.getPlayer().playSound(this.player.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    @Override
    public String toString() {
        return String.format("SkillManager[combat='%s', combatXp='%s', foraging='%s', foragingXp='%s']", combat, combatXp, foraging, foragingXp);
    }
}
