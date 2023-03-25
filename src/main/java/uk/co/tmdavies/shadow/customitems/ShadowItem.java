package uk.co.tmdavies.shadow.customitems;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.builders.RPGItemBuilder;
import uk.co.tmdavies.shadow.customitems.abilities.EmpowerAbility;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public enum ShadowItem {

    EXCALIBUR("&eExcalibur", new String[]{
            " ",
            "&7The holy sword &eExcalibur&7.",
            "&7Once said the mighty &fKing Arthur &7once",
            "&7held this majestic sword."
    }, Material.GOLDEN_SWORD, "shadow_weapon_excalibur", new EmpowerAbility(2), getDefaultFlags());

    private final String name;
    private final String[] lore;
    private final Material material;
    private final NamespacedKey key;
    private final ShadowItemAbility ability;
    private final ItemFlag[] flags;

    ShadowItem(String name, String[] lore, Material material, String key, ShadowItemAbility ability, ItemFlag... flags) {
        this.name = ShadowUtils.Colour(name);
        this.lore = lore;
        this.material = material;
        this.key = new NamespacedKey(JavaPlugin.getPlugin(Shadow.class), key);
        this.ability = ability;
        this.flags = flags;
    }

    public String getName() {
        return this.name;
    }

    public String[] getLore() {
        return this.lore;
    }

    public Material getMaterial() {
        return this.material;
    }

    public NamespacedKey getKey() {
        return this.key;
    }

    public ShadowItemAbility getAbility() {
        return this.ability;
    }

    public ItemFlag[] getFlags() {
        return this.flags;
    }

    public ItemStack getBuiltItem() {

        return new RPGItemBuilder(this.material)
                .setName(this.name)
                .setLore(this.lore)
                .setUnbreakable()
                .addKey(this.key, PersistentDataType.STRING, this.key.getKey())
                .addFlags(this.flags)
                .build();

    }

    public ItemStack getBuiltItemWithCustomKey(NamespacedKey key) {

        return new RPGItemBuilder(this.material)
                .setName(this.name)
                .setLore(this.lore)
                .setUnbreakable()
                .addKey(key, PersistentDataType.STRING, key.getKey())
                .addFlags(this.flags)
                .build();

    }

    public static ShadowItem getByKey(String key) {

        for (ShadowItem item : ShadowItem.values())
            if (item.getKey().getKey().equals(key)) return item;

        return null;

    }

    private static ItemFlag[] getDefaultFlags() {
        return new ItemFlag[]{ ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE };
    }

}
