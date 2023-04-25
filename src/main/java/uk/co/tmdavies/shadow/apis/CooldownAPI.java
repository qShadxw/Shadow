package uk.co.tmdavies.shadow.apis;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*

    DECOMPILED FROM PREVIOUS PROJECT WHICH I NEVER SAVED.
    FUNCTIONS MAY BE READABLE DUE TO NEED OF REFACTORING.

 */

public class CooldownAPI {

    private static HashMap<String, Cache<UUID, Long>> cooldown = new HashMap<>();

    public static void createCooldown(String key, long defaultTime) {
        if (cooldown.containsKey(key)) throw new IllegalArgumentException("Cooldown already exists.");

        cooldown.put(key, CacheBuilder.newBuilder().expireAfterWrite(defaultTime, TimeUnit.SECONDS).build());
    }

    public static @Nullable Cache<UUID, Long> getCooldownMap(String uuid) {
        return cooldown.getOrDefault(uuid, null);
    }

    public static void addCooldown(String key, Player player, int seconds) {
        if (!cooldown.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist");
        }

        long next = System.currentTimeMillis() + seconds * 1000L;
        cooldown.get(key).put(player.getUniqueId(), next);
    }

    public static boolean isOnCooldown(String key, Player player, long now) {
        return isOnCooldown(key, player) && now <= cooldown.get(key).getIfPresent(player.getUniqueId());
    }

    public static boolean isOnCooldown(String key, Player player) {
        return cooldown.get(key).getIfPresent(player.getUniqueId()) != null;
    }

    public static int getCooldownForPlayerInt(String key, Player player, long now) {
        return (int) ((now - (Objects.requireNonNull(cooldown.get(key).getIfPresent(player.getUniqueId())).intValue())) / 1000);
    }

    public static int getCooldownForPlayerInt(String key, Player player) {
        return Math.abs((int) ((System.currentTimeMillis() - (Objects.requireNonNull(cooldown.get(key).getIfPresent(player.getUniqueId())).intValue()))))/1000;
    }

    public static long getCooldownForPlayerLong(String key, Player player) {
        return (System.currentTimeMillis() - (Objects.requireNonNull(cooldown.get(key).getIfPresent(player.getUniqueId())))) / 1000;
    }

    public static long getCooldownForPlayerLong(String key, Player player, long now) {
        return (now - (Objects.requireNonNull(cooldown.get(key).getIfPresent(player.getUniqueId())))) / 1000;
    }

    public static void removeCooldown(String key, Player player) {
        if (!cooldown.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist");
        }

        cooldown.get(key).invalidate(player.getUniqueId());
    }

    public static void removeCooldown(String key) {
        if (!cooldown.containsKey(key)) {
            throw new IllegalArgumentException(key + " does not exist");
        }

        cooldown.remove(key);
    }

    public static boolean cooldownExists(String key) {
        return cooldown.containsKey(key);
    }

}
