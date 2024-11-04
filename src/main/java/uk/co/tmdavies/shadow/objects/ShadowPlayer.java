package uk.co.tmdavies.shadow.objects;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uk.co.tmdavies.shadow.managers.PluginManager;
import uk.co.tmdavies.shadow.managers.player.ManaManager;
import uk.co.tmdavies.shadow.managers.player.SkillManager;
import uk.co.tmdavies.shadow.utils.ShadowUtils;

public class ShadowPlayer {

    private final Player player;
    private final double level;
    private final double xp;
    private final ManaManager manaManager;
    private final SkillManager skillManager;
    private BossBar manaBar;
    private final BukkitRunnable manaRegen;

    public ShadowPlayer(Player player) {
        this.player = player;
        this.level = 1;
        this.xp = 0;
        manaManager = new ManaManager();
        skillManager = new SkillManager(this);
        createManaBossBar("&bMana: %s", this.manaManager.getMana());
        updateManaBossBar();
        sendManaInfo();
        this.manaRegen = this.manaManager.regenerateMana();
    }

    public Player getPlayer() {
        return player;
    }

    public void sendMessage(String message, Object... args) {
        player.sendMessage(ShadowUtils.Chat(message, args));
    }

    public void sendCenteredMessage(String[] message) {
        for (String s : message) {
            sendMessage(ShadowUtils.getCenteredMessage(s));
        }
    }

    public void sendActionBar(String message, Object... args) {
        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ShadowUtils.Colour(message, args)));
    }

    public void createManaBossBar(String text, Object... args) {
        manaBar = Bukkit.createBossBar(ShadowUtils.Colour(text, args), BarColor.BLUE, BarStyle.SEGMENTED_10);

        manaBar.setProgress(ShadowUtils.map(0, this.manaManager.getMaxMana(), 0, 1, this.manaManager.getMana()));
        manaBar.addPlayer(this.player);
    }

    public void updateManaBossBar() {
        manaBar.setTitle(ShadowUtils.Colour("&bMana: %s", this.manaManager.getMana()));
        manaBar.setProgress(ShadowUtils.map(0, this.manaManager.getMaxMana(), 0, 1, this.manaManager.getMana()));
    }

    public double getLevel() {
        return level;
    }

    public double getExperience() {
        return xp;
    }

    public ManaManager getManaManager() {
        return manaManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public BukkitRunnable getManaRegen() {
        return manaRegen;
    }

    public void sendManaInfo() {
        new BukkitRunnable() {
            @Override
            public void run() {
                updateManaBossBar();
            }
        }.runTaskTimerAsynchronously(PluginManager.getPluginInstance(), 1, 1);
    }

    @Override
    public String toString() {
        return String.format("ShadowPlayer[player='%s', level='%s', xp='%s', manaManager='%s', skillManager='%s']", player.getName(), level, xp, manaManager, skillManager);
    }

}
