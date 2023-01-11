package de.pandaxpaul.tntbow.commands;

import de.pandaxpaul.tntbow.TNTBow;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import  org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.PlayerInventory;

public class TntBowCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {

            return true;
        }

        switch (args[0].toLowerCase()) {
            case "getbow":
            case "givebow": {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    ItemStack bow = new ItemStack(Material.BOW,1);
                    ItemMeta bowMeta = bow.getItemMeta();
                    bowMeta.setDisplayName(ChatColor.RED + "TNT Bow");
                    bow.setItemMeta(bowMeta);
                    player.getInventory().addItem(bow);
                    sender.sendMessage(ChatColor.GRAY + "Dir wurde der" + ChatColor.RED + "TNT Bow " + ChatColor.RESET + "gegeben!");
                    break;
                } else {
                    System.out.println(sender + "hat versucht /tntBow getbow/givebow " + ChatColor.RED + "doch der Versuch wurde abgelehnt!");
                    break;
                }
            }
            default:
                sendUsage(sender);
                break;
        }
        return false;
}

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "Verwendung:");
        sender.sendMessage(ChatColor.GREEN + "/tntBow /giveBow");

    }
}
