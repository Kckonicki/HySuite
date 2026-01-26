package com.Buddytohelpu.hysuite.util;

import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import javax.annotation.Nonnull;
import java.util.UUID;

public final class Permissions {
    public static final String COOLDOWN_BYPASS = "hysuite.cooldown.bypass";

    // Admin permissions
    public static final String ADMIN_RANKS = "hysuite.admin.ranks";
    public static final String ADMIN_SETRANK = "hysuite.admin.setrank";
    public static final String ADMIN_PLAYERINFO = "hysuite.admin.playerinfo";
    public static final String ADMIN_RELOAD = "hysuite.admin.reload";

    // Admin chat permissions (groups defined in adminchat.json)
    public static final String ADMIN_CHAT_STAFF = "hysuite.adminchat.staff";
    public static final String ADMIN_CHAT_ADMIN = "hysuite.adminchat.admin";

    public static final String VANISH = "hysuite.vanish";
    public static final String FLY = "hysuite.fly";

    // Command permissions
    public static final String SETWARP = "hysuite.setwarp";
    public static final String DELWARP = "hysuite.delwarp";
    public static final String SETSPAWN = "hysuite.setspawn";
    public static final String HTP = "hysuite.htp";
    public static final String HTPHERE = "hysuite.htphere";

    @Deprecated
    public static final String VIP = "hysuite.vip";
    @Deprecated
    public static final String VIP_HOMES = "hysuite.vip.homes";
    @Deprecated
    public static final String VIP_COOLDOWN = "hysuite.vip.cooldown";

    private Permissions() {
    }

    public static boolean hasPermission(@Nonnull PlayerRef player, @Nonnull String permission) {
        return PermissionsModule.get().hasPermission(player.getUuid(), permission);
    }

    public static boolean hasPermission(@Nonnull UUID playerUuid, @Nonnull String permission) {
        return PermissionsModule.get().hasPermission(playerUuid, permission);
    }

    public static boolean canBypassCooldown(@Nonnull PlayerRef player) {
        return hasPermission(player, COOLDOWN_BYPASS);
    }

    public static boolean canManageRanks(@Nonnull PlayerRef player) {
        return hasPermission(player, ADMIN_RANKS);
    }

    public static boolean canSetRanks(@Nonnull PlayerRef player) {
        return hasPermission(player, ADMIN_SETRANK);
    }

    public static boolean canViewPlayerInfo(@Nonnull PlayerRef player) {
        return hasPermission(player, ADMIN_PLAYERINFO);
    }

    public static boolean canReload(@Nonnull PlayerRef player) {
        return hasPermission(player, ADMIN_RELOAD);
    }

    @Deprecated
    public static boolean isVip(@Nonnull PlayerRef player) {
        return hasPermission(player, VIP);
    }

    @Deprecated
    public static boolean hasVipHomes(@Nonnull PlayerRef player) {
        return hasPermission(player, VIP_HOMES) || isVip(player);
    }

    @Deprecated
    public static boolean hasVipCooldown(@Nonnull PlayerRef player) {
        return hasPermission(player, VIP_COOLDOWN) || isVip(player);
    }
}
