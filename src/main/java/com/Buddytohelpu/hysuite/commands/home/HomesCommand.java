package com.Buddytohelpu.hysuite.commands.home;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.Buddytohelpu.hysuite.lang.Messages;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.util.ChatUtil;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;

public class HomesCommand extends AbstractPlayerCommand {
    private final HomeManager homeManager;
    private final RankManager rankManager;

    public HomesCommand(@Nonnull HomeManager homeManager, @Nonnull RankManager rankManager) {
        super("homes", "List all your homes");
        this.homeManager = homeManager;
        this.rankManager = rankManager;
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store,
                          @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        UUID playerUuid = playerRef.getUuid();
        Set<String> homes = homeManager.getHomeNames(playerUuid);
        if (homes.isEmpty()) {
            context.sendMessage(ChatUtil.parse(Messages.ERROR_NO_HOMES));
            return;
        }
        int count = homes.size();
        int max = rankManager.getEffectiveMaxHomes(playerRef);
        context.sendMessage(ChatUtil.parse(Messages.INFO_HOMES_LIST, count, max));
        for (String home : homes) {
            context.sendMessage(ChatUtil.parse("<gray>  - " + home + "</gray>"));
        }
    }
}
