package me.KeybordPiano459.ServerHub;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemClick implements Listener {
    ServerHub plugin;
    public ItemClick(ServerHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getTypeId() == plugin.getConfig().getInt("click-item-id")) {
                plugin.getIconMenu().open(player);
            }
        }
    }
}
