package com.Buddytohelpu.hysuite.commands.admin;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;
import com.Buddytohelpu.hysuite.config.HySuiteConfig;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.WarpManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.PlaytimeManager;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import javax.annotation.Nonnull;

public class HysCommand extends AbstractCommandCollection {
    public HysCommand(@Nonnull RankManager rankManager, 
                     @Nonnull HomeManager homeManager,
                     @Nonnull WarpManager warpManager,
                     @Nonnull PlaytimeManager playtimeManager,
                     @Nonnull CooldownManager cooldownManager,
                     @Nonnull TeleportWarmupManager warmupManager,
                     @Nonnull Config<HySuiteConfig> config) {
        super("hysuite", "HySuite admin commands");
        this.addAliases("hys");
        
        // Admin commands
        this.addSubCommand(new RankSubCommand(rankManager));
        this.addSubCommand(new AssignSubCommand(rankManager));
        this.addSubCommand(new SetRankSubCommand(rankManager));
        this.addSubCommand(new RemoveRankSubCommand(rankManager));
        this.addSubCommand(new PlayerInfoSubCommand(rankManager, homeManager));
        this.addSubCommand(new ReloadSubCommand(rankManager, config));
        
        // Player commands
        this.addSubCommand(new DashboardSubCommand(homeManager, warpManager, rankManager, 
                                                   playtimeManager, cooldownManager, warmupManager));
    }

    @Override
    protected boolean canGeneratePermission() {
        // Don't require a permission for the parent command itself
        // Each subcommand has its own permission requirement
        return false;
    }
}
