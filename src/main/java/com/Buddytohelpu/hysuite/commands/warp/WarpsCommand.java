package com.Buddytohelpu.hysuite.commands.warp;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.Buddytohelpu.hysuite.gui.WarpListGui;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import com.Buddytohelpu.hysuite.manager.WarpManager;
import javax.annotation.Nonnull;

public class WarpsCommand extends AbstractPlayerCommand {
    private final WarpManager warpManager;
    private final TeleportWarmupManager warmupManager;
    private final CooldownManager cooldownManager;
    private final RankManager rankManager;

    public WarpsCommand(@Nonnull WarpManager warpManager, @Nonnull TeleportWarmupManager warmupManager,
                        @Nonnull CooldownManager cooldownManager, @Nonnull RankManager rankManager) {
        super("warps", "Open warp list GUI");
        this.warpManager = warpManager;
        this.warmupManager = warmupManager;
        this.cooldownManager = cooldownManager;
        this.rankManager = rankManager;
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store,
                          @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());
        if (player == null) return;

        player.getPageManager().openCustomPage(ref, store,
            new WarpListGui(playerRef, warpManager, warmupManager, cooldownManager, rankManager, CustomPageLifetime.CanDismiss));
    }
}
