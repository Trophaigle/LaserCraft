package fr.lasercraft.stickgun.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import fr.lasercraft.stickgun.StickGun;

public class AddRespawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender instanceof Player){
			Player p = (Player) sender;
		Location loc = p.getLocation();
		int size = ((ConfigurationSection) StickGun.instance.getConfig().get("location.respawns")).getKeys(false).size() + 1;
		ConfigurationSection section = StickGun.instance.getConfig().getConfigurationSection("location.respawns");
		section.set(size + ".x", loc.getX());
		section.set(size + ".y", loc.getY());
		section.set(size + ".z", loc.getZ());
		section.set(size + ".world", loc.getWorld().getName());
		p.sendMessage("§aUn nouveau respawn ajouté en: §f" + loc.getX() + "|" + loc.getY() + "|" + loc.getZ() + "|" + loc.getWorld().getName());
			}	
		
		return false;
	
		}

}
