package me.KeybordPiano459.ServerHub;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerHub extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        getLogger().info("ServerHub v1.0 has been enabled!");
        config = new Config(this);
        this.getCommand("hub").setExecutor(new CommandGui(this));
        new File("plugins" + File.separator + "ServerHub").mkdirs();
        config.createConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("ServerHub v1.0 has been disabled.");
    }

    public IconMenu getIconMenu() {
        IconMenu menu = new IconMenu(getConfig().getString("gui-name"), getConfig().getInt("gui-rows")*9, new IconMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                event.getPlayer().performCommand(getConfig().getString("items.slot-" + event.getPosition() + ".command"));
                event.setWillClose(true);
                event.setWillDestroy(true);
            }
        }, this);
        for (int i = 0; i < getConfig().getConfigurationSection("items.").getKeys(false).size(); i++) {
            List<String> lorelist = new ArrayList<>();
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