package fr.lasercraft.stickgun.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import fr.lasercraft.stickgun.StickGun;

public class CommandeManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("setMainSpawnRed")){
			
		}
		if(cmd.getName().equalsIgnoreCase("setMainSpawnBlue")){
			
		}
		if(cmd.getName().equalsIgnoreCase("setSpawn")){
			
		}
		if(cmd.getName().equalsIgnoreCase("addRespawn")){
			
			}
		}
		return false;
	}
  
	
	
}
