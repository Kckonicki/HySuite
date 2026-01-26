package com.Buddytohelpu.hysuite.rank;

import com.Buddytohelpu.hysuite.rank.editor.RankEditorData;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages rank editor sessions and rank-related state.
 *
 * This class intentionally avoids lambdas, codecs, and GUI logic
 * to prevent type erosion and API coupling.
 */
public final class RankManager {

    private final Map<UUID, RankEditorData> editorSessions = new ConcurrentHashMap<>();

    /**
     * Opens or retrieves an existing rank editor session for a player.
     */
    public RankEditorData openEditor(UUID playerId) {
        Objects.requireNonNull(playerId, "playerId");

        return editorSessions.computeIfAbsent(
                playerId,
                id -> new RankEditorData(id)
        );
    }

    /**
     * Gets an active rank editor session if one exists.
     */
    public Optional<RankEditorData> getEditor(UUID playerId) {
        Objects.requireNonNull(playerId, "playerId");
        return Optional.ofNullable(editorSessions.get(playerId));
    }

    /**
     * Closes and removes an editor session.
     */
    public void closeEditor(UUID playerId) {
        Objects.requireNonNull(playerId, "playerId");
        editorSessions.remove(playerId);
    }

    /**
     * Sets the selected rank for a player's editor session.
     */
    public void selectRank(UUID playerId, String rankId) {
        Objects.requireNonNull(playerId, "playerId");
        Objects.requireNonNull(rankId, "rankId");

        RankEditorData data = openEditor(playerId);
        data.setSelectedRank(rankId);
    }

    /**
     * Clears all editor sessions.
     * Useful during plugin reload or shutdown.
     */
    public void clearAll() {
        editorSessions.clear();
    }
}
