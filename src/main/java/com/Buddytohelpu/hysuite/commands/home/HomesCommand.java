package com.Buddytohelpu.hysuite.commands.home;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.Buddytohelpu.hysuite.gui.HomeListGui;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import javax.annotation.Nonnull;

public class HomesCommand extends AbstractPlayerCommand {
    private final HomeManager homeManager;
    private final TeleportWarmupManager warmupManager;
    private final CooldownManager cooldownManager;
    private final RankManager rankManager;

    public HomesCommand(@Nonnull HomeManager homeManager, @Nonnull TeleportWarmupManager warmupManager,
                        @Nonnull CooldownManager cooldownManager, @Nonnull RankManager rankManager) {
        super("homes", "Open home management GUI");
        this.homeManager = homeManager;
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
            new HomeListGui(playerRef, homeManager, warmupManager, cooldownManager, rankManager, CustomPageLifetime.CanDismiss));
    }
}
