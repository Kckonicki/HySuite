package com.Buddytohelpu.hysuite.gui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.Buddytohelpu.hysuite.data.DashboardData;
import com.Buddytohelpu.hysuite.data.Rank;
import com.Buddytohelpu.hysuite.lang.Messages;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.WarpManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.PlaytimeManager;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Dashboard GUI showing player stats and quick actions
 */
public class DashboardGui extends InteractiveCustomUIPage<DashboardData> {

    private final HomeManager homeManager;
    private final WarpManager warpManager;
    private final RankManager rankManager;
    private final PlaytimeManager playtimeManager;
    private final CooldownManager cooldownManager;
    private final TeleportWarmupManager warmupManager;
    private final UUID playerUuid;
    private final PlayerRef playerRef;

    public DashboardGui(@Nonnull PlayerRef playerRef,
                       @Nonnull HomeManager homeManager,
                       @Nonnull WarpManager warpManager,
                       @Nonnull RankManager rankManager,
                       @Nonnull PlaytimeManager playtimeManager,
                       @Nonnull CooldownManager cooldownManager,
                       @Nonnull TeleportWarmupManager warmupManager,
                       @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, DashboardData.CODEC);
        this.playerRef = playerRef;
        this.homeManager = homeManager;
        this.warpManager = warpManager;
        this.rankManager = rankManager;
        this.playtimeManager = playtimeManager;
        this.cooldownManager = cooldownManager;
        this.warmupManager = warmupManager;
        this.playerUuid = playerRef.getUuid();
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder cmd,
                      @Nonnull UIEventBuilder events, @Nonnull Store<EntityStore> store) {
        cmd.append("Pages/HySuite_Dashboard.ui");

        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());

        // Set title
        cmd.set("#TitleLabel.Text", Messages.UI_DASHBOARD_TITLE.get());

        // Build stats section
        buildStats(cmd, playerRef);

        // Build quick actions section
        buildQuickActions(cmd, events);

        // Close button
        events.addEventBinding(CustomUIEventBindingType.Activating, "#CloseButton",
            EventData.of("Action", "Close"), false);
    }

    private void buildStats(@Nonnull UICommandBuilder cmd, @Nonnull PlayerRef playerRef) {
        // Homes count
        int homeCount = homeManager.getHomeCount(playerUuid);
        int maxHomes = rankManager.getEffectiveMaxHomes(playerRef);
        cmd.set("#HomesCount.Text", homeCount + "/" + maxHomes);

        // Warps count
        int warpCount = warpManager.getWarpCount();
        cmd.set("#WarpsCount.Text", warpCount + " available");

        // Current rank
        Rank rank = rankManager.getPlayerRank(playerUuid);
        String rankName = rank != null ? rank.getDisplayName() : "Default";
        cmd.set("#RankName.Text", rankName);

        // Playtime
        String playtime = playtimeManager.getFormattedPlaytime(playerUuid);
        cmd.set("#PlaytimeValue.Text", playtime);

        // TODO: Add balance when economy system is implemented
        // cmd.set("#BalanceValue.Text", "$" + economyManager.getBalance(playerUuid));
    }

    private void buildQuickActions(@Nonnull UICommandBuilder cmd, @Nonnull UIEventBuilder events) {
        // Manage Homes button
        events.addEventBinding(CustomUIEventBindingType.Activating, "#ManageHomesButton",
            EventData.of("Action", "OpenHomes"), false);

        // View Warps button
        events.addEventBinding(CustomUIEventBindingType.Activating, "#ViewWarpsButton",
            EventData.of("Action", "OpenWarps"), false);

        // Player Stats button (future feature)
        events.addEventBinding(CustomUIEventBindingType.Activating, "#PlayerStatsButton",
            EventData.of("Action", "OpenProfile"), false);

        // Settings button (future feature)
        events.addEventBinding(CustomUIEventBindingType.Activating, "#SettingsButton",
            EventData.of("Action", "OpenSettings"), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref,
                                   @Nonnull Store<EntityStore> store,
                                   @Nonnull DashboardData data) {
        super.handleDataEvent(ref, store, data);

        if (data.action != null) {
            switch (data.action) {
                case "Close":
                    this.close();
                    break;

                case "OpenHomes":
                    Player player = store.getComponent(ref, Player.getComponentType());
                    if (player != null) {
                        HomeListGui homeGui = new HomeListGui(
                            playerRef,
                            homeManager,
                            warmupManager,
                            cooldownManager,
                            rankManager,
                            CustomPageLifetime.CanDismiss
                        );
                        player.getPageManager().openCustomPage(ref, store, homeGui);
                    }
                    break;

                case "OpenWarps":
                    Player player2 = store.getComponent(ref, Player.getComponentType());
                    if (player2 != null) {
                        WarpListGui warpGui = new WarpListGui(
                            playerRef,
                            warpManager,
                            warmupManager,
                            cooldownManager,
                            rankManager,
                            CustomPageLifetime.CanDismiss
                        );
                        player2.getPageManager().openCustomPage(ref, store, warpGui);
                    }
                    break;

                case "OpenProfile":
                    Player player3 = store.getComponent(ref, Player.getComponentType());
                    if (player3 != null) {
                        player3.sendMessage(Message.raw("§ePlayer profile coming soon in v1.0.2!"));
                    }
                    break;

                case "OpenSettings":
                    Player player4 = store.getComponent(ref, Player.getComponentType());
                    if (player4 != null) {
                        player4.sendMessage(Message.raw("§eSettings menu coming soon in v1.0.2!"));
                    }
                    break;
            }
        }
    }
}