package uk.co.tmdavies.shadow.utils;

import uk.co.tmdavies.shadow.customitems.ShadowItemAbility;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static String[] generateAbilityLore(ShadowItemAbility ability, String... description) {

        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ShadowUtils.Colour("&7Ability: &d" + ability.getName() + " Lv." + ability.getLevel()));
        for (String string : description) lore.add(ShadowUtils.Colour(string));

        return lore.toArray(new String[0]);

    }

}
