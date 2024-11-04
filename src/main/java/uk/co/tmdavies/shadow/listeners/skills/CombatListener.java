package uk.co.tmdavies.shadow.listeners.skills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import uk.co.tmdavies.shadow.enums.MobInfo;
import uk.co.tmdavies.shadow.managers.PluginManager;
import uk.co.tmdavies.shadow.objects.ShadowPlayer;

public class CombatListener implements Listener {

    @EventHandler
    public void onCombat(final EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }
        if (!event.getEntity().isDead()) {
            return;
        }

        Player player = event.getEntity().getKiller();
        ShadowPlayer shadowPlayer = PluginManager.getPluginInstance().getPlayerManager().getShadowPlayer(player);

        if (shadowPlayer == null) {
            return;
        }

        double xpAmount = MobInfo.findExperience(event.getEntityType());

        shadowPlayer.getSkillManager().addCombatXp(xpAmount);
    }

}
