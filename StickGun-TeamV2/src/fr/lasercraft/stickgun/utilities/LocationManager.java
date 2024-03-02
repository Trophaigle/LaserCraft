package fr.lasercraft.stickgun.utilities;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import fr.lasercraft.stickgun.StickGun;

public class LocationManager {

	static LocationManager instance = new LocationManager();
	
	private LocationManager(){}
	public static LocationManager getLocation(){return instance;}
	
	public ArrayList<Location> location = new ArrayList<Location>();
	
	public void addLocation(){
		ConfigurationSection sec = StickGun.instance.getConfig().getConfigurationSection("location.respawns");
		if(sec == null) return;
		for(String integer : sec.getKeys(false)){
			Location loc = new Location(Bukkit.getWorld(sec.getString(sec.getString(integer + ".world"))),sec.getDouble(integer +".x"), sec.getDouble(integer +".y"), sec.getDouble(integer +".z"));
			location.add(loc);
		}
	}
	
	public Location getLocationForTeleport() {
		
		Random random = new Random();
			Location randomLoc = location.get(random.nextInt(location.size()));		
			return randomLoc;
	}
}
