package de.pandaxpaul.tntbow;

import de.pandaxpaul.tntbow.listeners.LaunchListener;
import de.pandaxpaul.tntbow.commands.TntBowCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TNTBow extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.RED + "TNT Bow PLugin activ");
        System.out.println(ChatColor.BLUE + " author: @PandaxPaul");
        getCommand("tntBow").setExecutor((new TntBowCommands()));
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new LaunchListener(this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
