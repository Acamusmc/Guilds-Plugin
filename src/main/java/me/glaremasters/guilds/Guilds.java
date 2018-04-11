package me.glaremasters.guilds;

import me.glaremasters.guilds.utils.SpigotUpdater;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Guilds extends JavaPlugin {

    private static Guilds instance;

    private static Permission permission = null;

    private static Economy economy = null;

    private static boolean vaultEconomy;

    private static boolean vaultPermissions;

    @Override
    public void onEnable() {
        instance = this;

        // Check to see if there's any updates for the plugin
        SpigotUpdater updater = new SpigotUpdater(this, 48920);
        try {
            // If there's an update, tell the user that they can update
            if (updater.checkForUpdates()) {
                getLogger().info("You appear to be running a version other than our latest stable release."
                        + " You can download our newest version at: " + updater.getResourceURL());
            }
        } catch (Exception e) {
            // If it can't check for an update, tell the user and throw an error.
            getLogger().info("Could not check for updates! Stacktrace:");
            e.printStackTrace();
        }

        vaultEconomy = setupEconomy();
        vaultPermissions = setupPermissions();

    }

    @Override
    public void onDisable() {

    }

    /**
     * Gets the instance of Guilds. This class is the main class and it extends JavaPlugin.
     *
     * @return Guilds instance
     */
    public static Guilds getInstance() {
        return instance;
    }

    /**
     * Sets up the boolean for initializing Guilds to hook into Vault's permission system.
     *
     * @return Vault's permission setup
     */
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    /**
     * Sets up the boolean for initializing Guilds to hook into Vault's economy system.
     *
     * @return Vault's economy setup
     */
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

}
