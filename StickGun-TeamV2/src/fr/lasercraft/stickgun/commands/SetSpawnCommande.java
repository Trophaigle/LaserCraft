package fr.lasercraft.stickgun.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lasercraft.stickgun.StickGun;

public class SetSpawnCommande implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender instanceof Player){
			Player p = (Player) sender;
		
		Location loc = p.getLocation();
		StickGun.instance.getConfig().set("location.spawn.world", loc.getWorld().getName());
		StickGun.instance.getConfig().set("location.spawn.x", loc.getX());
		StickGun.instance.getConfig().set("location.spawn.y", loc.getY());
		StickGun.instance.getConfig().set("location.spawn.z", loc.getZ());
		p.sendMessage("§aLe spawn principal à bien été enregistré en: §f" + loc.getX() +"|" + loc.getY() + "|" + loc.getZ() + "|" + loc.getWorld().getName());
	}
		return false;
	}

}
