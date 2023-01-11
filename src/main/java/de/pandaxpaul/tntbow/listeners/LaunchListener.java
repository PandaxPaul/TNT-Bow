package de.pandaxpaul.tntbow.listeners;

import de.pandaxpaul.tntbow.TNTBow;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow;

import org.bukkit.ChatColor;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class LaunchListener implements Listener {

    private final TNTBow plugin;

    public LaunchListener(TNTBow plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onProjectileLaunchEvent(ProjectileLaunchEvent event) {
        //get Arrow
        Arrow arrow = (Arrow) event.getEntity();
        //Check if the Arrow Shooter is the Player
        if (!(arrow.getShooter() instanceof Player)) {
            //if not --> return
            return;
        }
        //get Shooter
        Player player = (Player) arrow.getShooter();

        //If the bow from the player in main hand is the "TNT Bow" set a new metadata from the arrow to "TNT_ARROW"
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "TNT Bow")) {
            arrow.setMetadata("TNT_ARROW", new FixedMetadataValue(plugin, arrow));

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (arrow.isOnGround()) { //check if arrow is on Ground
                        World world = arrow.getWorld();
                        Location location = arrow.getLocation();
                        world.spawn(location, TNTPrimed.class);
                        cancel();
                        return;
                    }

                    arrow.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, arrow.getLocation(), 1, 0, 0, 0, 0);
                }

            }.runTaskTimer(plugin, 0, 1); //every tick
        }
}
}
