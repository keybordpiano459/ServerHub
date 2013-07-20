package me.KeybordPiano459.ServerHub;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHub implements CommandExecutor {
    ServerHub plugin;
    public CommandHub(ServerHub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hub")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("serverhub.hub")) {
                    if (args.length == 0) {
                        plugin.getIconMenu().open(player);
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            if (player.hasPermission("serverhub.reload")) {
                                plugin.reloadConfig();
                                player.sendMessage(ChatColor.GREEN + "The server hub has been reloaded");
                            } else {
                                player.sendMessage(ChatColor.RED + "You don't have permission to use this command");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Incorrect usage! Type /hub");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Incorrect usage! Type /hub");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                }
            } else {
                plugin.getLogger().info("You can't use this command from the console!");
            }
        }
        return false;
    }
}
