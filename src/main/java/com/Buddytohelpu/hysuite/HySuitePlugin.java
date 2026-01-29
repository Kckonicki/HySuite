package com.Buddytohelpu.hysuite;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.Buddytohelpu.hysuite.commands.admin.HysCommand;
import com.Buddytohelpu.hysuite.commands.home.DelHomeCommand;
import com.Buddytohelpu.hysuite.commands.home.HomeCommand;
import com.Buddytohelpu.hysuite.commands.home.HomesCommand;
import com.Buddytohelpu.hysuite.commands.home.SetHomeCommand;
import com.Buddytohelpu.hysuite.commands.spawn.SetSpawnCommand;
import com.Buddytohelpu.hysuite.commands.spawn.SpawnCommand;
import com.Buddytohelpu.hysuite.commands.teleport.BackCommand;
import com.Buddytohelpu.hysuite.commands.teleport.RtpCommand;
import com.Buddytohelpu.hysuite.commands.teleport.TpCommand;
import com.Buddytohelpu.hysuite.commands.teleport.TphereCommand;
import com.Buddytohelpu.hysuite.commands.tpa.TpacceptCommand;
import com.Buddytohelpu.hysuite.commands.tpa.TpaCommand;
import com.Buddytohelpu.hysuite.commands.tpa.TpahereCommand;
import com.Buddytohelpu.hysuite.commands.tpa.TpcancelCommand;
import com.Buddytohelpu.hysuite.commands.tpa.TpdenyCommand;
import com.Buddytohelpu.hysuite.commands.msg.AdminChatCommand;
import com.Buddytohelpu.hysuite.commands.msg.MsgCommand;
import com.Buddytohelpu.hysuite.commands.msg.ReplyCommand;
import com.Buddytohelpu.hysuite.commands.warp.DelWarpCommand;
import com.Buddytohelpu.hysuite.commands.warp.SetWarpCommand;
import com.Buddytohelpu.hysuite.commands.warp.WarpCommand;
import com.Buddytohelpu.hysuite.commands.warp.WarpsCommand;
import com.Buddytohelpu.hysuite.config.ConfigMigrator;
import com.Buddytohelpu.hysuite.config.HySuiteConfig;
import com.Buddytohelpu.hysuite.manager.BackManager;
import com.Buddytohelpu.hysuite.manager.CooldownManager;
import com.Buddytohelpu.hysuite.manager.DataManager;
import com.Buddytohelpu.hysuite.manager.HomeManager;
import com.Buddytohelpu.hysuite.manager.RankManager;
import com.Buddytohelpu.hysuite.manager.TeleportWarmupManager;
import com.Buddytohelpu.hysuite.manager.TpaManager;
import com.Buddytohelpu.hysuite.manager.WarpManager;
import com.Buddytohelpu.hysuite.manager.PrivateMessageManager;
import com.Buddytohelpu.hysuite.manager.AdminChatManager;
import com.Buddytohelpu.hysuite.manager.VanishManager;
import com.Buddytohelpu.hysuite.manager.JoinMessageManager;
import com.Buddytohelpu.hysuite.manager.PlaytimeManager;
import com.Buddytohelpu.hysuite.commands.admin.VanishCommand;
import com.Buddytohelpu.hysuite.commands.admin.FlyCommand;
import com.Buddytohelpu.hysuite.system.PlayerDeathBackSystem;
import com.Buddytohelpu.hysuite.lang.LanguageManager;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent;
import com.hypixel.hytale.server.core.event.events.player.DrainPlayerFromWorldEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.annotation.Nonnull;

public class HySuitePlugin extends JavaPlugin {
    private final Config<HySuiteConfig> config = this.withConfig("config", HySuiteConfig.CODEC);
    private DataManager dataManager;
    private RankManager rankManager;
    private TpaManager tpaManager;
    private HomeManager homeManager;
    private WarpManager warpManager;
    private BackManager backManager;
    private CooldownManager cooldownManager;
    private TeleportWarmupManager warmupManager;
    private PrivateMessageManager msgManager;
    private AdminChatManager adminChatManager;
    private VanishManager vanishManager;
    private JoinMessageManager joinMessageManager;
    private PlaytimeManager playtimeManager;
    private ScheduledExecutorService permissionScheduler;

    public HySuitePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        ConfigMigrator migrator = new ConfigMigrator(this.getDataDirectory(), this.getLogger());
        migrator.migrate();

        HySuiteConfig cfg = this.config.get();
        this.config.save();

        // Initialize language system (auto-syncs translation files)
        LanguageManager.init(this.getDataDirectory(), this.getLogger());
        LanguageManager.setLanguage(cfg.getLanguage());

        this.dataManager = new DataManager(this.getDataDirectory(), this.getLogger());
        this.rankManager = new RankManager(this.getDataDirectory(), this.getLogger(), cfg.getDefaultRankId());
        this.rankManager.setOnRanksSavedCallback(this::syncAllPlayerPermissions);
        this.backManager = new BackManager(cfg.getBackHistorySize());
        this.cooldownManager = new CooldownManager();
        this.warmupManager = new TeleportWarmupManager(this.backManager, this.cooldownManager);
        this.tpaManager = new TpaManager(this.rankManager);
        this.homeManager = new HomeManager(this.dataManager, this.rankManager);
        this.warpManager = new WarpManager(this.dataManager);
        this.msgManager = new PrivateMessageManager();
        this.adminChatManager = new AdminChatManager(this.getDataDirectory(), this.getLogger());
        this.vanishManager = new VanishManager();
        this.joinMessageManager = new JoinMessageManager(this.getDataDirectory(), this.getLogger());
        this.playtimeManager = new PlaytimeManager(this.getDataDirectory().toFile());

        this.getEntityStoreRegistry().registerSystem(new PlayerDeathBackSystem(this.backManager));

        this.getEventRegistry().register(PlayerConnectEvent.class, this::onPlayerConnect);
        this.getEventRegistry().register(PlayerDisconnectEvent.class, this::onPlayerDisconnect);
        this.getEventRegistry().registerGlobal(AddPlayerToWorldEvent.class, this.joinMessageManager::onPlayerEnterWorld);
        this.getEventRegistry().registerGlobal(DrainPlayerFromWorldEvent.class, this.joinMessageManager::onPlayerLeaveWorld);
    }

    @Override
    protected void start() {
        this.getCommandRegistry().registerCommand(new TpaCommand(this.tpaManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new TpahereCommand(this.tpaManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new TpacceptCommand(this.tpaManager, this.warmupManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new TpdenyCommand(this.tpaManager));
        this.getCommandRegistry().registerCommand(new TpcancelCommand(this.tpaManager));
        this.getCommandRegistry().registerCommand(new SetHomeCommand(this.homeManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new HomeCommand(this.homeManager, this.warmupManager, this.cooldownManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new DelHomeCommand(this.homeManager));
        this.getCommandRegistry().registerCommand(new HomesCommand(this.homeManager, this.warmupManager, this.cooldownManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new SetWarpCommand(this.warpManager));
        this.getCommandRegistry().registerCommand(new WarpCommand(this.warpManager, this.warmupManager, this.cooldownManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new DelWarpCommand(this.warpManager));
        this.getCommandRegistry().registerCommand(new WarpsCommand(this.warpManager, this.warmupManager, this.cooldownManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new SetSpawnCommand());
        this.getCommandRegistry().registerCommand(new SpawnCommand(this.warmupManager, this.cooldownManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new BackCommand(this.backManager, this.warmupManager, this.cooldownManager, this.rankManager));
        this.getCommandRegistry().registerCommand(new RtpCommand(this.warmupManager, this.cooldownManager, this.rankManager, this.config.get().getRtpMinRange(), this.config.get().getRtpMaxRange()));
        this.getCommandRegistry().registerCommand(new TpCommand(this.backManager));
        this.getCommandRegistry().registerCommand(new TphereCommand(this.backManager));
        this.getCommandRegistry().registerCommand(new HysCommand(this.rankManager, this.homeManager, this.warpManager, this.playtimeManager, this.cooldownManager, this.warmupManager, this.config));
        this.getCommandRegistry().registerCommand(new MsgCommand(this.msgManager));
        this.getCommandRegistry().registerCommand(new ReplyCommand(this.msgManager));
        this.getCommandRegistry().registerCommand(new AdminChatCommand(this.adminChatManager));
        this.getCommandRegistry().registerCommand(new VanishCommand(this.vanishManager));
        this.getCommandRegistry().registerCommand(new FlyCommand());

        this.permissionScheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "HySuite-PermissionSync");
            t.setDaemon(true);
            return t;
        });
        this.permissionScheduler.scheduleAtFixedRate(this::syncAllPlayerPermissions, 1, 1, TimeUnit.MINUTES);
        
        // Save playtime data every 5 minutes
        this.permissionScheduler.scheduleAtFixedRate(() -> {
            this.playtimeManager.saveAllPlaytime();
        }, 5, 5, TimeUnit.MINUTES);

        this.getLogger().at(Level.INFO).log("HySuite loaded with rank system!");
    }

    private void onPlayerConnect(@Nonnull PlayerConnectEvent event) {
        vanishManager.onPlayerJoin(event.getPlayerRef());
        joinMessageManager.onPlayerConnect(event);
        playtimeManager.onPlayerJoin(event.getPlayerRef().getUuid());

        PlayerRef player = event.getPlayerRef();
        if (!playerHasAnyRank(player)) {
            rankManager.grantRankPermission(player.getUuid(), rankManager.getDefaultRankId());
        } else {
            rankManager.ensureGrantedPermissions(player.getUuid());
        }
    }

    private boolean playerHasAnyRank(@Nonnull PlayerRef player) {
        for (var rank : rankManager.getAllRanks()) {
            if (com.hypixel.hytale.server.core.permissions.PermissionsModule.get()
                    .hasPermission(player.getUuid(), rank.getPermission())) {
                return true;
            }
        }
        return false;
    }

    private void onPlayerDisconnect(@Nonnull PlayerDisconnectEvent event) {
        joinMessageManager.onPlayerDisconnect(event);
        vanishManager.onPlayerLeave(event.getPlayerRef().getUuid());
        playtimeManager.onPlayerQuit(event.getPlayerRef().getUuid());
    }

    private void syncAllPlayerPermissions() {
        try {
            Universe universe = Universe.get();
            if (universe == null) {
                return;
            }
            for (PlayerRef player : universe.getPlayers()) {
                rankManager.ensureGrantedPermissions(player.getUuid());
            }
        } catch (Exception e) {
            this.getLogger().at(Level.WARNING).log("Failed to sync player permissions: %s", e.getMessage());
        }
    }

    public void triggerPermissionSync() {
        syncAllPlayerPermissions();
    }

    @Override
    protected void shutdown() {
        if (this.permissionScheduler != null) {
            this.permissionScheduler.shutdown();
        }
        if (this.homeManager != null) {
            this.homeManager.save();
        }
        if (this.warpManager != null) {
            this.warpManager.save();
        }
        if (this.warmupManager != null) {
            this.warmupManager.shutdown();
        }
        if (this.joinMessageManager != null) {
            this.joinMessageManager.shutdown();
        }
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public void reloadConfig() {
        this.config.load();
        this.rankManager.reload();
        if (this.adminChatManager != null) {
            this.adminChatManager.reload();
        }
        if (this.joinMessageManager != null) {
            this.joinMessageManager.reload();
        }
        // Reload language
        LanguageManager.setLanguage(this.config.get().getLanguage());
        LanguageManager.reload();
    }
}
