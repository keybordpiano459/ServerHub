package me.KeybordPiano459.ServerHub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    ServerHub plugin;
    public Config(ServerHub plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration config;
    public File configFile;

    public void createConfig() {
        configFile = new File("plugins" + File.separator + "ServerHub", "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                generateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateConfig() {
        try {
            configFile = new File("plugins" + File.separator + "ServerHub", "config.yml");
            FileWriter w = new FileWriter(configFile);
            w(w, "# ServerHub | KeybordPiano459");
            w(w, "");
            w(w, "# How many rows should the GUI have?");
            w(w, "gui-rows: 1");
            w(w, "");
            w(w, "# What should the name of the GUI be?");
            w(w, "gui-name: Server Activities");
            w(w, "");
            w(w, "# All GUI items are here. Add slots in the same format, and it will add items to the GUI");
            w(w, "items:");
            w(w, "  slot-0:");
            w(w, "    item-id: 345");
            w(w, "    name: Spawn");
            w(w, "    lore:");
            w(w, "      line-0: Server Spawnpoint");
            w(w, "    command: spawn");
            w(w, "  slot-1:");
            w(w, "    item-id: 266");
            w(w, "    name: Bank");
            w(w, "    lore:");
            w(w, "      line-0: Sell Gold for Money");
            w(w, "    command: warp bank");
            w(w, "  slot-2:");
            w(w, "    item-id: 331");
            w(w, "    name: Trade");
            w(w, "    lore:");
            w(w, "      line-0: Trading Booths");
            w(w, "    command: warp trade");
            w(w, "  slot-3:");
            w(w, "    item-id: 276");
            w(w, "    name: PVP Arena");
            w(w, "    lore:");
            w(w, "      line-0: Spectate PVP Fights");
            w(w, "    command: warp watchpvp");
            w(w, "  slot-4:");
            w(w, "    item-id: 256");
            w(w, "    name: Spleef");
            w(w, "    lore:");
            w(w, "      line-0: Participate in Spleef");
            w(w, "    command: warp spleef");
            w.close();
            reloadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void w(FileWriter writer, String string) throws IOException {
        writer.write(string + "\n");
    }

    public void reloadConfig() {
        if (!configFile.exists()) configFile = new File("plugins" + File.separator + "ServerHub", "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        InputStream defConfigStream = plugin.getResource("plugins" + File.separator + "ServerHub" + File.separator + "config.yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            config.setDefaults(defConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    public void saveConfig() {
        if (config == null || configFile == null) {
            return;
        }
        try {
            config.save(configFile);
        } catch (IOException e) {
            Logger.getLogger("Minecraft").severe("Could not save the config file to the disk!");
        }
    }
}