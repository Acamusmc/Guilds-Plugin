package me.glaremasters.guilds;

import lombok.Getter;
import me.glaremasters.guilds.utils.SpigotUpdater;
import org.bukkit.plugin.java.JavaPlugin;

public final class Guilds extends JavaPlugin {

    @Getter Guilds i;

    @Override
    public void onEnable() {
        // Check to see if there's any updates for the plugin
        SpigotUpdater updater = new SpigotUpdater(this, 48920);
        try {
            // If there's an update, tell the user that they can update
            if (updater.checkForUpdates()) {
                getLogger().info("You appear to be running a version other than our latest stable release."
                        + " You can download our newest version at: " + updater
                        .getResourceURL());
            }
        } catch (Exception e) {
            // If it can't check for an update, tell the user and throw an error.
            getLogger().info("Could not check for updates! Stacktrace:");
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {

    }
}
