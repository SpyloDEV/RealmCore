package com.tristan.realmcore;

import com.tristan.realmcore.command.DailyCommand;
import com.tristan.realmcore.command.FeedCommand;
import com.tristan.realmcore.command.FlyCommand;
import com.tristan.realmcore.command.HealCommand;
import com.tristan.realmcore.command.HomeCommand;
import com.tristan.realmcore.command.RtpCommand;
import com.tristan.realmcore.command.SetHomeCommand;
import com.tristan.realmcore.command.SetSpawnCommand;
import com.tristan.realmcore.command.SpawnCommand;
import com.tristan.realmcore.command.TpAcceptCommand;
import com.tristan.realmcore.command.TpDenyCommand;
import com.tristan.realmcore.command.TpaCommand;
import com.tristan.realmcore.command.TpaHereCommand;
import com.tristan.realmcore.command.WarpCommand;
import com.tristan.realmcore.command.SetWarpCommand;
import com.tristan.realmcore.command.admin.GamemodeCommand;
import com.tristan.realmcore.command.admin.RealmCoreCommand;
import com.tristan.realmcore.command.admin.VanishCommand;
import com.tristan.realmcore.gui.MenuListener;
import com.tristan.realmcore.listener.PlayerConnectionListener;
import com.tristan.realmcore.listener.PlayerProtectionListener;
import com.tristan.realmcore.manager.CooldownManager;
import com.tristan.realmcore.manager.DailyRewardManager;
import com.tristan.realmcore.manager.HomeManager;
import com.tristan.realmcore.manager.SpawnManager;
import com.tristan.realmcore.manager.TeleportRequestManager;
import com.tristan.realmcore.manager.UserDataManager;
import com.tristan.realmcore.manager.VanishManager;
import com.tristan.realmcore.manager.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RealmCorePlugin extends JavaPlugin {

    private UserDataManager userDataManager;
    private HomeManager homeManager;
    private WarpManager warpManager;
    private SpawnManager spawnManager;
    private TeleportRequestManager teleportRequestManager;
    private DailyRewardManager dailyRewardManager;
    private CooldownManager cooldownManager;
    private VanishManager vanishManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("messages.yml", false);

        this.userDataManager = new UserDataManager(this);
        this.homeManager = new HomeManager(this, userDataManager);
        this.warpManager = new WarpManager(this);
        this.spawnManager = new SpawnManager(this);
        this.teleportRequestManager = new TeleportRequestManager(this);
        this.dailyRewardManager = new DailyRewardManager(this, userDataManager);
        this.cooldownManager = new CooldownManager();
        this.vanishManager = new VanishManager(this);

        registerCommands();
        registerListeners();

        getLogger().info("RealmCore enabled successfully.");
    }

    @Override
    public void onDisable() {
        userDataManager.saveAll();
        homeManager.save();
        warpManager.save();
        spawnManager.save();
        getLogger().info("RealmCore disabled.");
    }

    private void registerCommands() {
        getCommand("realmcore").setExecutor(new RealmCoreCommand(this));

        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));

        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));

        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));

        getCommand("tpa").setExecutor(new TpaCommand(this));
        getCommand("tpahere").setExecutor(new TpaHereCommand(this));
        getCommand("tpaccept").setExecutor(new TpAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TpDenyCommand(this));

        getCommand("daily").setExecutor(new DailyCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("rtp").setExecutor(new RtpCommand(this));

        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("gm").setExecutor(new GamemodeCommand(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerProtectionListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
    }

    public UserDataManager getUserDataManager() {
        return userDataManager;
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    public TeleportRequestManager getTeleportRequestManager() {
        return teleportRequestManager;
    }

    public DailyRewardManager getDailyRewardManager() {
        return dailyRewardManager;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }

    public void reloadAll() {
        reloadConfig();
        userDataManager.reload();
        homeManager.reload();
        warpManager.reload();
        spawnManager.reload();
        saveResource("messages.yml", true);
    }
}
