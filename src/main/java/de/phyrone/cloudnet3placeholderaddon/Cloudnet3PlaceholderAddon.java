package de.phyrone.cloudnet3placeholderaddon;

import me.clip.placeholderapi.events.ExpansionUnregisterEvent;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Cloudnet3PlaceholderAddon extends JavaPlugin implements Listener {

    private Set<PlaceholderExpansion> expansions = new HashSet<>(Arrays.asList(
            new CloudNetThreeCorePlaceholderExpansion(this)
    ));

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        expansions.forEach(PlaceholderExpansion::register);
    }

    @EventHandler
    public void onUnregisterHook(ExpansionUnregisterEvent event) {
        if (expansions.contains(event.getExpansion())) {
            PlaceholderExpansion expansion = event.getExpansion();
            Bukkit.getScheduler().runTaskLater(this, expansion::register, 5);
        }
    }

}
