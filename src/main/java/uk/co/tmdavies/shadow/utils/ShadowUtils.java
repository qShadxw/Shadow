package uk.co.tmdavies.shadow.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import uk.co.tmdavies.shadow.enums.DefaultFontInfo;
import uk.co.tmdavies.shadow.managers.PluginManager;

public class ShadowUtils {

    public static String Chat(String message, Object... args) {
        return ChatColor.translateAlternateColorCodes('&', String.format(getStringFromLang("Prefix") + message, args));
    }

    public static String Colour(String message, Object... args) {
        return ChatColor.translateAlternateColorCodes('&', String.format(message, args));
    }

    public static String ChatRaw(String message) {
        return ChatColor.translateAlternateColorCodes('&', getStringFromLang("Prefix") + message);
    }

    public static String getStringFromLang(String path) {
        return PluginManager.getPluginInstance().getConfigManager().getColouredTextFromLang(path);
    }

    public static String colourRaw(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getCenteredMessage(@NotNull String message) {
        message = Colour(message);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = DefaultFontInfo.getCenterPx() - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder builder = new StringBuilder();

        while (compensated < toCompensate) {
            builder.append(" ");
            compensated += spaceLength;
        }

        return builder.toString() + message;
    }

    public static double map(double inStart, double inEnd, double outStart, double outEnd, double value) {
        if (value < inStart) {
            return outStart;
        }

        if (value > inEnd) {
            return outEnd;
        }

        return (value - inStart) / (inEnd - inStart) * (outEnd - outStart) + outStart;
    }

}
