package it.r_developing.qRMapAPI;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    public static void main(String[] args) {

    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("QRMapAPI is running!");
    }

    @Override
    public void onDisable() {
        getLogger().info("QRMapAPI is disabled!");
    }

    public static Main getInstance() {
        return instance;
    }
}
