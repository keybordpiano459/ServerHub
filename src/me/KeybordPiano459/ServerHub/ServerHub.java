package me.KeybordPiano459.ServerHub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerHub extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        getLogger().info("ServerHub v1.1 has been enabled!");
        config = new Config(this);
        this.getCommand("hub").setExecutor(new CommandHub(this));
        new File("plugins" + File.separator + "ServerHub").mkdirs();
        config.createConfig();
        getServer().getPluginManager().registerEvents(getIconMenu(), this);
        getServer().getPluginManager().registerEvents(new ItemClick(this), this);

        try {
            BukkitMetrics metrics = new BukkitMetrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the data :-(
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("ServerHub v1.1 has been disabled.");
    }

    public IconMenu getIconMenu() {
        IconMenu menu = new IconMenu(getConfig().getString("gui-name"), getConfig().getInt("gui-rows") * 9, new IconMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                event.getPlayer().performCommand(getConfig().getString("items.slot-" + event.getPosition() + ".command"));
            }
        }, this);

        for (int i = 0; i < getConfig().getConfigurationSection("items.").getKeys(false).size(); i++) {
            /*ItemStack item = null;
            String id = getConfig().getString("items.slot-" + i + ".item-id");
            if (id.contains(":")) {
                String[] parts = id.split(":");
                item = new ItemStack(Integer.valueOf(parts[0]));
                item.setData(new MaterialData(Integer.valueOf(parts[1])));
            } else {
                item = new ItemStack(Integer.valueOf(id));
            }*/

            List<String> lorelist = new ArrayList<String>();
            for (int j = 0; j < getConfig().getConfigurationSection("items.slot-" + i + ".lore.").getKeys(false).size(); j++) {
                lorelist.add(getConfig().getString("items.slot-" + i + ".lore.line-" + j));
            }
            menu.setOption(i, new ItemStack(getConfig().getInt("items.slot-" + i + ".item-id")), getConfig().getString("items.slot-" + i + ".name"), lorelist);
        }
        
        return menu;
    }

    @Override
    public FileConfiguration getConfig() {
        return config.getConfig();
    }

    @Override
    public void reloadConfig() {
        config.reloadConfig();
    }

    @Override
    public void saveConfig() {
        config.saveConfig();
    }
}