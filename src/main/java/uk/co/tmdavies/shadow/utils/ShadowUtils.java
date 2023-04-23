package uk.co.tmdavies.shadow.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import uk.co.tmdavies.shadow.Shadow;
import uk.co.tmdavies.shadow.commands.playercommands.DumpCommand;
import uk.co.tmdavies.shadow.commands.playercommands.ItemCommand;
import uk.co.tmdavies.shadow.commands.playercommands.MainCommand;
import uk.co.tmdavies.shadow.enums.DefaultFontInfo;
import uk.co.tmdavies.shadow.listeners.AbilityListener;
import uk.co.tmdavies.shadow.listeners.ServerListener;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShadowUtils {

    private static final String prefix = "&8[&7Shadow&8] &f";

    public static String Colour(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String Chat(String message) {
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }

    public static void loadFiles(Shadow plugin) {
        // Commands
        new MainCommand();
        new ItemCommand();
        new DumpCommand();

        // Listeners
        new AbilityListener(plugin);
        new ServerListener(plugin);
    }

    public static String toRomanNumeral(int number) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[number / 1000] + hundreds[(number % 1000) / 100] + tens[(number % 100) / 10] + units[number % 10];
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

    public static void sendCenteredMessage(@NotNull Player player, @NotNull String message) {
        player.sendMessage(getCenteredMessage(message));
    }

    public static void broadcastCenteredMessage(@NotNull String message) {
        Bukkit.broadcastMessage(getCenteredMessage(message));
    }

    public static String colorCodesHex(String message) {
        final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})#");
        Matcher matcher = hexPattern.matcher(message);
        StringBuilder builder = new StringBuilder(message.length() + 4 * 8);

        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(builder, ChatColor.COLOR_CHAR + "x"
                    + ChatColor.COLOR_CHAR + group.charAt(0) + ChatColor.COLOR_CHAR + group.charAt(1)
                    + ChatColor.COLOR_CHAR + group.charAt(2) + ChatColor.COLOR_CHAR + group.charAt(3)
                    + ChatColor.COLOR_CHAR + group.charAt(4) + ChatColor.COLOR_CHAR + group.charAt(5)
            );
        }

        return matcher.appendTail(builder).toString();
    }

    public static String colorRainbow(String message) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            double hue = map(0, message.length(), 0, 1, i);
            if (hue > 1) hue = 1;
            if (hue < 0) hue = 0;
            int rgb = Color.HSBtoRGB((float)hue, 1, 1);
            int red = (rgb>>16)&0xFF;
            int green = (rgb>>8)&0xFF;
            int blue = rgb&0xFF;

            output.append("&#")
                    .append(extendStringPrefix(Integer.toHexString(red), 2, "0"))
                    .append(extendStringPrefix(Integer.toHexString(green), 2, "0"))
                    .append(extendStringPrefix(Integer.toHexString(blue), 2, "0"))
                    .append("#").append(message.charAt(i));
        }

        return colorCodesHex(output.toString());
    }

    public static String extendStringPrefix(String string, int minLength, String filler) {
        StringBuilder output = new StringBuilder(string);
        while (output.length() < minLength) output.insert(0, filler);

        return output.toString();
    }

    public static double map(double inStart, double inEnd, double outStart, double outEnd, double value) {
        if (value < inStart) return outStart;
        if (value > inEnd) return outEnd;

        return (value - inStart) / (inEnd - inStart) * (outEnd - outStart) + outStart;
    }

}
