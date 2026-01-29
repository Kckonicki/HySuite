package com.Buddytohelpu.hysuite.commands.admin;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.Buddytohelpu.hysuite.gui.DashboardGui;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.WarpManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.PlaytimeManager;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * Dashboard command - opens the HySuite dashboard GUI
 */
public class DashboardSubCommand extends AbstractAsyncCommand {
    private final HomeManager homeManager;
    private final WarpManager warpManager;
    private final RankManager rankManager;
    private final PlaytimeManager playtimeManager;
    private final CooldownManager cooldownManager;
    private final TeleportWarmupManager warmupManager;

    public DashboardSubCommand(@Nonnull HomeManager homeManager,
                              @Nonnull WarpManager warpManager,
                              @Nonnull RankManager rankManager,
                              @Nonnull PlaytimeManager playtimeManager,
                              @Nonnull CooldownManager cooldownManager,
                              @Nonnull TeleportWarmupManager warmupManager) {
        super("dashboard", "Open the HySuite dashboard");
        this.homeManager = homeManager;
        this.warpManager = warpManager;
        this.rankManager = rankManager;
        this.playtimeManager = playtimeManager;
        this.cooldownManager = cooldownManager;
        this.warmupManager = warmupManager;
        // No permission required - available to all players
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Nonnull
    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        CommandSender sender = context.sender();
        if (sender instanceof Player player) {
            Ref<EntityStore> ref = player.getReference();
            if (ref != null && ref.isValid()) {
                Store<EntityStore> store = ref.getStore();
                World world = store.getExternalData().getWorld();
                return CompletableFuture.runAsync(() -> {
                    PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
                    if (playerRef != null) {
                        DashboardGui dashboardGui = new DashboardGui(
                            playerRef,
                            homeManager,
                            warpManager,
                            rankManager,
                            playtimeManager,
                            cooldownManager,
                            warmupManager,
                            CustomPageLifetime.CanDismiss
                        );
                        player.getPageManager().openCustomPage(ref, store, dashboardGui);
                    }
                }, world);
            }
        }
        return CompletableFuture.completedFuture(null);
    }
}
