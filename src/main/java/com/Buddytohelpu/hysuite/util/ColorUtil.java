package com.Buddytohelpu.hysuite.util;

import javax.annotation.Nonnull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Internal color utility for HySuite.
 * Provides color name to hex conversion and formatting support.
 * Integrated from Chat-Customization plugin.
 */
public final class ColorUtil {
    
    // Minecraft/Legacy color codes
    private static final Pattern LEGACY_COLOR_PATTERN = Pattern.compile("&([0-9a-fklmnor])");
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([0-9A-Fa-f]{6})");
    
    // Named color mappings (Minecraft standard + common names)
    private static final String[][] COLOR_MAP = {
        // Minecraft standard colors
        {"BLACK", "#000000"},
        {"DARK_BLUE", "#0000AA"},
        {"DARK_GREEN", "#00AA00"},
        {"DARK_AQUA", "#00AAAA"},
        {"DARK_RED", "#AA0000"},
        {"DARK_PURPLE", "#AA00AA"},
        {"GOLD", "#FFAA00"},
        {"GRAY", "#AAAAAA"},
        {"DARK_GRAY", "#555555"},
        {"BLUE", "#5555FF"},
        {"GREEN", "#55FF55"},
        {"AQUA", "#55FFFF"},
        {"RED", "#FF5555"},
        {"LIGHT_PURPLE", "#FF55FF"},
        {"YELLOW", "#FFFF55"},
        {"WHITE", "#FFFFFF"},
        
        // Common aliases
        {"ORANGE", "#FFAA00"},
        {"PURPLE", "#AA00AA"},
        {"PINK", "#FF55FF"},
        {"LIME", "#55FF55"},
        {"CYAN", "#55FFFF"},
    };
    
    private ColorUtil() {
        // Utility class
    }
    
    /**
     * Converts a color name or code to hex format.
     * 
     * @param color Color name (e.g., "RED", "GOLD") or hex (e.g., "#FF5555")
     * @return Hex color string in format #RRGGBB
     */
    @Nonnull
    public static String toHex(@Nonnull String color) {
        if (color == null || color.isEmpty()) {
            return "#FFFFFF";
        }
        
        // Already hex format
        if (color.startsWith("#") && color.length() == 7) {
            return color.toUpperCase();
        }
        
        // Try to find named color
        String upperColor = color.toUpperCase().replace(" ", "_");
        for (String[] pair : COLOR_MAP) {
            if (pair[0].equals(upperColor)) {
                return pair[1];
            }
        }
        
        // Default to white if unknown
        return "#FFFFFF";
    }
    
    /**
     * Colorizes text with legacy color codes (&a, &b, etc.) and hex codes (&#RRGGBB).
     * This method converts legacy codes to hex format for compatibility.
     * 
     * @param text Text with color codes
     * @return Text with hex colors (implementation depends on Message API)
     */
    @Nonnull
    public static String colorize(@Nonnull String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        
        // Convert hex codes first: &#RRGGBB -> #RRGGBB
        Matcher hexMatcher = HEX_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (hexMatcher.find()) {
            hexMatcher.appendReplacement(sb, "#" + hexMatcher.group(1).toUpperCase());
        }
        hexMatcher.appendTail(sb);
        text = sb.toString();
        
        // Convert legacy color codes to named colors
        Matcher legacyMatcher = LEGACY_COLOR_PATTERN.matcher(text);
        sb = new StringBuffer();
        while (legacyMatcher.find()) {
            String code = legacyMatcher.group(1).toLowerCase();
            String replacement = switch (code) {
                case "0" -> "#000000"; // BLACK
                case "1" -> "#0000AA"; // DARK_BLUE
                case "2" -> "#00AA00"; // DARK_GREEN
                case "3" -> "#00AAAA"; // DARK_AQUA
                case "4" -> "#AA0000"; // DARK_RED
                case "5" -> "#AA00AA"; // DARK_PURPLE
                case "6" -> "#FFAA00"; // GOLD
                case "7" -> "#AAAAAA"; // GRAY
                case "8" -> "#555555"; // DARK_GRAY
                case "9" -> "#5555FF"; // BLUE
                case "a" -> "#55FF55"; // GREEN
                case "b" -> "#55FFFF"; // AQUA
                case "c" -> "#FF5555"; // RED
                case "d" -> "#FF55FF"; // LIGHT_PURPLE
                case "e" -> "#FFFF55"; // YELLOW
                case "f" -> "#FFFFFF"; // WHITE
                case "k" -> ""; // Obfuscated - remove for now
                case "l" -> ""; // Bold - handled separately
                case "m" -> ""; // Strikethrough - handled separately
                case "n" -> ""; // Underline - handled separately
                case "o" -> ""; // Italic - handled separately
                case "r" -> ""; // Reset - handled separately
                default -> "&" + code;
            };
            legacyMatcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        legacyMatcher.appendTail(sb);
        
        return sb.toString();
    }
    
    /**
     * Strips all color codes from text.
     * Removes both legacy (&a, &b) and hex (&#RRGGBB) codes.
     * 
     * @param text Text with color codes
     * @return Plain text without color codes
     */
    @Nonnull
    public static String stripColors(@Nonnull String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        
        // Remove hex codes
        text = HEX_PATTERN.matcher(text).replaceAll("");
        
        // Remove legacy codes
        text = LEGACY_COLOR_PATTERN.matcher(text).replaceAll("");
        
        return text;
    }
    
    /**
     * Creates a gradient between two colors for a string.
     * 
     * @param text Text to apply gradient to
     * @param startColor Starting color (hex format)
     * @param endColor Ending color (hex format)
     * @return Text with gradient (simplified - applies start color to first half, end color to second half)
     */
    @Nonnull
    public static String gradient(@Nonnull String text, @Nonnull String startColor, @Nonnull String endColor) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        
        // Simple gradient: first half is start color, second half is end color
        // Full gradient interpolation would require character-by-character coloring
        int mid = text.length() / 2;
        return startColor + text.substring(0, mid) + endColor + text.substring(mid);
    }
    
    /**
     * Parses RGB values from hex string.
     * 
     * @param hex Hex color string (e.g., "#FF5555")
     * @return Array of [R, G, B] values (0-255)
     */
    @Nonnull
    public static int[] hexToRgb(@Nonnull String hex) {
        if (hex == null || !hex.startsWith("#") || hex.length() != 7) {
            return new int[]{255, 255, 255}; // Default to white
        }
        
        try {
            int r = Integer.parseInt(hex.substring(1, 3), 16);
            int g = Integer.parseInt(hex.substring(3, 5), 16);
            int b = Integer.parseInt(hex.substring(5, 7), 16);
            return new int[]{r, g, b};
        } catch (NumberFormatException e) {
            return new int[]{255, 255, 255};
        }
    }
    
    /**
     * Converts RGB values to hex string.
     * 
     * @param r Red (0-255)
     * @param g Green (0-255)
     * @param b Blue (0-255)
     * @return Hex color string (e.g., "#FF5555")
     */
    @Nonnull
    public static String rgbToHex(int r, int g, int b) {
        return String.format("#%02X%02X%02X",
            Math.max(0, Math.min(255, r)),
            Math.max(0, Math.min(255, g)),
            Math.max(0, Math.min(255, b))
        );
    }
}