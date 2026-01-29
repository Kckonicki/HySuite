package com.Buddytohelpu.hysuite.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages player playtime tracking
 */
public class PlaytimeManager {
    private final File dataFile;
    private final Gson gson;
    private final Map<UUID, Long> playtimeData; // UUID -> total minutes
    private final Map<UUID, Long> sessionStart; // UUID -> join timestamp

    public PlaytimeManager(@Nonnull File dataFolder) {
        this.dataFile = new File(dataFolder, "playtime.json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.playtimeData = new ConcurrentHashMap<>();
        this.sessionStart = new ConcurrentHashMap<>();
        loadData();
    }

    /**
     * Called when a player joins the server
     */
    public void onPlayerJoin(@Nonnull UUID playerId) {
        sessionStart.put(playerId, System.currentTimeMillis());
    }

    /**
     * Called when a player leaves the server
     */
    public void onPlayerQuit(@Nonnull UUID playerId) {
        updatePlaytime(playerId);
        sessionStart.remove(playerId);
        saveData();
    }

    /**
     * Update playtime for a player (call periodically or on quit)
     */
    public void updatePlaytime(@Nonnull UUID playerId) {
        Long joinTime = sessionStart.get(playerId);
        if (joinTime == null) return;

        long sessionMinutes = (System.currentTimeMillis() - joinTime) / 60000;
        long currentPlaytime = playtimeData.getOrDefault(playerId, 0L);
        playtimeData.put(playerId, currentPlaytime + sessionMinutes);

        // Reset session start for next update
        sessionStart.put(playerId, System.currentTimeMillis());
    }

    /**
     * Get total playtime in minutes for a player
     */
    public long getPlaytimeMinutes(@Nonnull UUID playerId) {
        // Update current session time before returning
        updateCurrentSession(playerId);
        return playtimeData.getOrDefault(playerId, 0L);
    }

    /**
     * Get formatted playtime string (e.g., "24h 15m")
     */
    public String getFormattedPlaytime(@Nonnull UUID playerId) {
        long totalMinutes = getPlaytimeMinutes(playerId);
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        
        if (hours > 0) {
            return hours + "h " + minutes + "m";
        } else {
            return minutes + "m";
        }
    }

    /**
     * Update current session without saving (for display purposes)
     */
    private void updateCurrentSession(@Nonnull UUID playerId) {
        Long joinTime = sessionStart.get(playerId);
        if (joinTime == null) return;

        long sessionMinutes = (System.currentTimeMillis() - joinTime) / 60000;
        long basePlaytime = playtimeData.getOrDefault(playerId, 0L);
        
        // Temporarily add session time for display
        if (sessionMinutes > 0) {
            playtimeData.put(playerId, basePlaytime + sessionMinutes);
            sessionStart.put(playerId, System.currentTimeMillis());
        }
    }

    /**
     * Save all playtime data periodically
     */
    public void saveAllPlaytime() {
        // Update all online players before saving
        for (UUID playerId : sessionStart.keySet()) {
            updatePlaytime(playerId);
        }
        saveData();
    }

    /**
     * Load playtime data from file
     */
    private void loadData() {
        if (!dataFile.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(dataFile)) {
            Type type = new TypeToken<Map<String, Long>>() {}.getType();
            Map<String, Long> loadedData = gson.fromJson(reader, type);
            
            if (loadedData != null) {
                for (Map.Entry<String, Long> entry : loadedData.entrySet()) {
                    try {
                        UUID uuid = UUID.fromString(entry.getKey());
                        playtimeData.put(uuid, entry.getValue());
                    } catch (IllegalArgumentException e) {
                        // Skip invalid UUIDs
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load playtime data: " + e.getMessage());
        }
    }

    /**
     * Save playtime data to file
     */
    private void saveData() {
        try {
            dataFile.getParentFile().mkdirs();
            
            // Convert UUID keys to strings for JSON
            Map<String, Long> saveData = new HashMap<>();
            for (Map.Entry<UUID, Long> entry : playtimeData.entrySet()) {
                saveData.put(entry.getKey().toString(), entry.getValue());
            }

            try (FileWriter writer = new FileWriter(dataFile)) {
                gson.toJson(saveData, writer);
            }
        } catch (IOException e) {
            System.err.println("Failed to save playtime data: " + e.getMessage());
        }
    }

    /**
     * Reset playtime for a player (admin command)
     */
    public void resetPlaytime(@Nonnull UUID playerId) {
        playtimeData.remove(playerId);
        sessionStart.remove(playerId);
        saveData();
    }
}
