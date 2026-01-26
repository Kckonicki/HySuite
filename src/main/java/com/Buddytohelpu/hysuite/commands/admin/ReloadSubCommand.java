package com.Buddytohelpu.hysuite.commands.admin;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import com.Buddytohelpu.hysuite.config.HySuiteConfig;
import com.Buddytohelpu.hysuite.lang.Messages;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.util.ChatUtil;
import com.Buddytohelpu.hysuite.util.Permissions;
import javax.annotation.Nonnull;

public class ReloadSubCommand extends AbstractPlayerCommand {
    private final RankManager rankManager;
    private final Config<HySuiteConfig> config;

    public ReloadSubCommand(@Nonnull RankManager rankManager, @Nonnull Config<HySuiteConfig> config) {
        super("reload", "Reload configuration and ranks");
        this.rankManager = rankManager;
        this.config = config;
        this.requirePermission(Permissions.ADMIN_RELOAD);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store,
                          @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef sender, @Nonnull World world) {
        // Permission check is handled by requirePermission() in constructor
        try {
            config.load().join();
            rankManager.reload();
            context.sendMessage(ChatUtil.parse(Messages.SUCCESS_CONFIG_RELOADED));
        } catch (Exception e) {
            context.sendMessage(ChatUtil.parse(Messages.ERROR_RELOAD_FAILED, e.getMessage()));
        }
    }
}
