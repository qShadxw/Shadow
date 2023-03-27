package uk.co.tmdavies.shadow.builders;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import uk.co.tmdavies.shadow.customitems.ShadowItemAbility;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

import java.util.ArrayList;
import java.util.List;

public class RPGItemBuilder {

    private ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final Material material;
    private final int amount;
    private ShadowItemAbility ability = null;

    public RPGItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = this.itemStack.getItemMeta();
        this.material = this.itemStack.getType();
        this.amount = this.itemStack.getAmount();
    }

    public RPGItemBuilder(Material material) {
        this.material = material;
        this.amount = 1;
        this.itemStack = new ItemStack(this.material, this.amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public RPGItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public RPGItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    public RPGItemBuilder setLore(String... lore) {
        List<String> newLore = new ArrayList<>();
        List.of(lore).forEach(string -> newLore.add(ShadowUtils.Colour(string)));
        this.itemMeta.setLore(newLore);
        return this;
    }

    public RPGItemBuilder setUnbreakable() {
        this.itemMeta.setUnbreakable(true);
        return this;
    }

    public RPGItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public RPGItemBuilder addFlags(ItemFlag... flags) {
        this.itemMeta.addItemFlags(flags);
        return this;
    }

    public RPGItemBuilder addKey(NamespacedKey key, PersistentDataType type, Object value) {
        this.itemMeta.getPersistentDataContainer().set(key, type, value);
        return this;
    }

    public RPGItemBuilder addAbility(ShadowItemAbility ability) {
        this.ability = ability;
        return this;
    }

    public RPGItemBuilder addCustomData(int data) {
        this.itemMeta.setCustomModelData(data);
        return this;
    }

    public ItemStack build() {
        if (this.ability != null) {
            List<String> lore = this.itemMeta.getLore() != null ? this.itemMeta.getLore() : new ArrayList<>();
            lore.addAll(List.of(this.ability.getLore()));
            this.itemMeta.setLore(lore);
        }

        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

}
