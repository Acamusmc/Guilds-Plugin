package me.glaremasters.guilds.listeners;

import static me.glaremasters.guilds.util.ConfigUtil.getBoolean;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import me.glaremasters.guilds.Guilds;
import me.glaremasters.guilds.guild.Guild;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class PlayerDamageListener implements Listener {

    public PlayerDamageListener(Guilds guilds) {
        this.guilds = guilds;
    }

    private Guilds guilds;

    @EventHandler
    public void onDamageOfPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        Guild guild = Guild.getGuild(player.getUniqueId());
        Guild guild2 = Guild.getGuild(damager.getUniqueId());
        if (guild == null || guild2 == null) {
            return;
        }
        if (getBoolean("hooks.worldguard")) {
            ApplicableRegionSet set = WGBukkit.getPlugin().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation());
            if (set.queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                return;
            }
        }
        if (guild.equals(guild2)) {
            event.setCancelled(!getBoolean("allow-guild-damage"));
        }
        if (Guild.areAllies(player.getUniqueId(), damager.getUniqueId())) {
            event.setCancelled(!getBoolean("allow-ally-damage"));
        }
    }
}
