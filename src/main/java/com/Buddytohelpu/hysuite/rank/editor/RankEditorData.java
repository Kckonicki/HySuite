package com.Buddytohelpu.hysuite.rank.editor;

import java.util.Objects;
import java.util.UUID;

/**
 * Holds per-player state for the Rank Editor UI.
 * This class is intentionally simple and explicitly typed
 * to avoid generic type erosion in callbacks and stores.
 */
public final class RankEditorData {

    private final UUID playerId;

    private String selectedRank;
    private boolean dirty;

    public RankEditorData(UUID playerId) {
        this.playerId = Objects.requireNonNull(playerId, "playerId");
        this.dirty = false;
    }

    /**
     * The player this editor session belongs to.
     */
    public UUID getPlayerId() {
        return playerId;
    }

    /**
     * Currently selected rank ID (or name, depending on your system).
     */
    public String getSelectedRank() {
        return selectedRank;
    }

    public void setSelectedRank(String selectedRank) {
        this.selectedRank = selectedRank;
        this.dirty = true;
    }

    /**
     * Whether changes have been made that need saving.
     */
    public boolean isDirty() {
        return dirty;
    }

    public void markClean() {
        this.dirty = false;
    }

    @Override
    public String toString() {
        return "RankEditorData{" +
                "playerId=" + playerId +
                ", selectedRank='" + selectedRank + '\'' +
                ", dirty=" + dirty +
                '}';
    }
}
