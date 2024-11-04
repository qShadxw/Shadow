package uk.co.tmdavies.shadow.enums;

import org.bukkit.entity.EntityType;

public enum MobInfo {

    ENDERMAN(EntityType.ENDERMAN, 10);

    private final EntityType type;
    private final double amount;

    MobInfo(EntityType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public EntityType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public static double findExperience(EntityType type) {
        for (MobInfo mobInfo : MobInfo.values()) {
            if (mobInfo.getType() == type) {
                return mobInfo.getAmount();
            }
        }

        return 0;
    }

}
