package it.r_developing.qRMapAPI;

import com.google.zxing.WriterException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Events implements Listener {

    @EventHandler
    public void onMapInitialize(MapInitializeEvent e) throws WriterException {
        String version = Bukkit.getServer().getVersion();
        MapView mapView = e.getMap();
        ItemStack mapItem;

        Player player = Objects.requireNonNull(mapView.getWorld()).getPlayers().stream().findFirst().orElse(null);
        if(player == null) return;

        if(version.split(" ")[1].compareTo("1.13") >= 0) {
            mapItem = player.getInventory().getItemInMainHand();
        } else {
            mapItem = player.getInventory().getItemInHand();
        }

        if(mapItem.hasItemMeta()) {
            ItemMeta meta = mapItem.getItemMeta();

            if(meta != null) {
                String data = meta.getDisplayName();

                if(!data.isEmpty()) {
                    BufferedImage image = it.r_developing.qRMapAPI.Tools.ImageCreator.generateQRcode(data);
                    mapView.getRenderers().clear();
                    mapView.addRenderer(new MapRenderer() {
                        @Override
                        public void render(@NotNull MapView mapView, @NotNull MapCanvas mapCanvas, @NotNull Player player) {
                            mapCanvas.drawImage(0, 0, image);
                        }
                    });
                }
            }
        }
    }
}
