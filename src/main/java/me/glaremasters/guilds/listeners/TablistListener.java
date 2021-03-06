package me.glaremasters.guilds.listeners;

import me.glaremasters.guilds.Guilds;
import me.glaremasters.guilds.guild.Guild;
import me.glaremasters.guilds.handlers.TablistHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by GlareMasters on 7/20/2017.
 */
public class TablistListener implements Listener {

    private Guilds guilds;

    public TablistListener(Guilds guilds) {
        this.guilds = guilds;
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        TablistHandler TablistHandler = new TablistHandler(guilds);
        Player player = event.getPlayer();
        Guild guild = Guild.getGuild(player.getUniqueId());

        if (guild != null) {
           guilds.getServer().getScheduler().scheduleAsyncDelayedTask(guilds, () -> TablistHandler.addTablist(player), 30L);
        }
    }

}
