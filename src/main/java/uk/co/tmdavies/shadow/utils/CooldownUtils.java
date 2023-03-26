package uk.co.tmdavies.shadow.utils;

import uk.co.tmdavies.shadow.apis.CooldownAPI;
import uk.co.tmdavies.shadow.customitems.ShadowItem;

public class CooldownUtils {

    public static void createCooldowns() {

        for (ShadowItem item : ShadowItem.values()) {

            if (CooldownAPI.cooldownExists(item.getAbility().getName())) continue;

            CooldownAPI.createCooldown(item.getAbility().getName(), item.getAbility().getCooldown());

        }

    }

    public static void removeCooldowns() {

        for (ShadowItem item : ShadowItem.values()) {

            if (!CooldownAPI.cooldownExists(item.getAbility().getName())) continue;

            CooldownAPI.removeCooldown(item.getAbility().getName());

        }

    }

}
