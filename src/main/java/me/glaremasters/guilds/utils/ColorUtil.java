package me.glaremasters.guilds.utils;

import org.bukkit.ChatColor;

/**
 * Created by GlareMasters on 4/11/2018.
 */
public class ColorUtil {

    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
