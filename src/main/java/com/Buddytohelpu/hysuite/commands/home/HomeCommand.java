package com.Buddytohelpu.hysuite.commands.home;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.Buddytohelpu.hysuite.data.CommandSettings;
import com.Buddytohelpu.hysuite.data.LocationData;
import com.Buddytohelpu.hysuite.lang.Messages;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import com.Buddytohelpu.hysuite.util.ChatUtil;
import com.Buddytohelpu.hysuite.util.Permissions;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;

public class HomeCommand extends AbstractPlayerCommand {
    private final HomeManager homeManager;
    private final TeleportWarmupManager warmupManager;
    private final CooldownManager cooldownManager;
    private final RankManager rankManager;

    public HomeCommand(@Nonnull HomeManager homeManager, @Nonnull TeleportWarmupManager warmupManager,
                       @Nonnull CooldownManager cooldownManager, @Nonnull RankManager rankManager) {
        super("home", "Teleport to your home");
        this.homeManager = homeManager;
        this.warmupManager = warmupManager;
        this.cooldownManager = cooldownManager;
        this.rankManager = rankManager;
        this.setAllowsExtraArguments(true);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store,
                          @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        String input = context.getInputString().trim();
        String[] args = input.split("\\s+");

        if (args.length <= 1) {
            listHomes(context, playerRef);
            return;
        }

        String name = args[1];
        teleportToHome(context, store, ref, playerRef, world, name);
    }

    private void listHomes(CommandContext context, PlayerRef playerRef) {
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

    private void teleportToHome(CommandContext context, Store<EntityStore> store, Ref<EntityStore> ref,
                                PlayerRef playerRef, World world, String name) {
        UUID playerUuid = playerRef.getUuid();
        CommandSettings settings = rankManager.getEffectiveSettings(playerRef, CooldownManager.HOME);
        boolean bypassCooldown = Permissions.canBypassCooldown(playerRef);

        if (!settings.isEnabled()) {
            context.sendMessage(ChatUtil.parse(Messages.NO_PERMISSION_HOME));
            return;
        }

        if (!bypassCooldown && cooldownManager.isOnCooldown(playerUuid, CooldownManager.HOME, settings.getCooldownSeconds())) {
            long remaining = cooldownManager.getCooldownRemaining(playerUuid, CooldownManager.HOME, settings.getCooldownSeconds());
            context.sendMessage(ChatUtil.parse(Messages.COOLDOWN_HOME, remaining));
            return;
        }

        LocationData home = homeManager.getHome(playerUuid, name);
        if (home == null) {
            context.sendMessage(ChatUtil.parse(Messages.ERROR_HOME_NOT_FOUND, name, name));
            return;
        }

        int warmupSeconds = bypassCooldown ? 0 : settings.getWarmupSeconds();
        warmupManager.startWarmup(playerRef, store, ref, world, home, warmupSeconds, CooldownManager.HOME, "home '" + name + "'", null);
    }
}
