package it.r_developing.qRMapAPI.Tools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Version {
    private final Plugin plugin;

    public Version(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean isServerHigher() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");

        int ver = Integer.parseInt(parts[1]);
        return ver >= 13;
    }

    public String getPlugin() {
        return this.plugin.getDescription().getVersion();
    }
}
