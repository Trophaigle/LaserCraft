package fr.lasercraft.stickgun.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.BDD.MySQL;
import fr.lasercraft.stickgun.GameState;
import fr.lasercraft.stickgun.PlayerDeathSnowballEvent;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.manager.GameManager;
import fr.lasercraft.stickgun.manager.OptionKillManager;
import fr.lasercraft.stickgun.manager.PointAndKillManager;
import fr.lasercraft.stickgun.nms.Hologram;
import fr.lasercraft.stickgun.teams.TeamManager;
import fr.lasercraft.stickgun.utilities.LocationManager;

public class DeathAndKillListener implements Listener {

	private static HashMap<BukkitRunnable, Integer> tasks = new HashMap<BukkitRunnable, Integer>();
	
	public static Plugin plugin;
	
	@SuppressWarnings("static-access")
	public DeathAndKillListener(Plugin pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent e){
		Player victime = e.getEntity();
		
		if(!StickGun.playerInGame.contains(victime)) e.setDeathMessage(null); //return;
		if(!GameState.isState(GameState.GAME)) return;
	
		if(victime.getLastDamageCause().equals(DamageCause.SUICIDE)) e.setDeathMessage(victime.getCustomName()+" §ea succombé...");
		if(victime.getLastDamageCause().equals(DamageCause.CUSTOM) || victime.getLastDamageCause().equals(DamageCause.PROJECTILE)){
			Player killer = victime.getKiller();
			if(killer == null) e.setDeathMessage(victime.getCustomName()+" §ea succombé...");
			else
				e.setDeathMessage(victime.getCustomName()+"§7a été tué par "+killer.getCustomName());
		}
		e.setDroppedExp(0);
	}
	
	@EventHandler
	public void onDeat(PlayerDeathSnowballEvent e){
		final Player victime = e.getVictime();
		Player killer = e.getShooter();
		
		if(TeamManager.getInstance().isBlue(victime)){
			if(killer != null){
				killer.sendMessage("Vous avez tué §9"+victime.getName() +" §e+1,75 §6LaserCoins §7(§akill§7)");
				victime.sendMessage("Vous avez été tué par §c"+killer.getName());
				
				}
			setHoloAndPoins(killer);
			
			MySQL.addCoins(killer, (long) 1.75);
			PointAndKillManager.pointRouge++;
			
			PointAndKillManager.setKills(killer, 1);
			PointAndKillManager.setCoins(killer, 1.75);	
		}
		
		if(TeamManager.getInstance().isRed(victime)){
			if(killer != null){
			killer.sendMessage("Vous avez tué §c"+victime.getName()+" §e+1,75 §6LaserCoins §7(§akill§7)");
			victime.sendMessage("Vous avez été tué par §9"+killer.getName());
			}
			setHoloAndPoins(killer);
		
			MySQL.addCoins(killer, (long) 1.75);
			PointAndKillManager.pointBleu++;
			PointAndKillManager.setKills(killer, 1);
			PointAndKillManager.setCoins(killer, 1.75);	
		}
		
		victime.setHealth(20);
		victime.setGameMode(GameMode.SPECTATOR);
		Title.sendUniqueTitle(victime, "§cVous êtes Mort", 80);
		
		new BukkitRunnable(){
			@Override
			public void run(){
				if(!tasks.containsKey(this)){
					tasks.put(this, 5);
				}
				
				if(tasks.get(this) == 0){
					victime.teleport(LocationManager.getLocation().getLocationForTeleport());
					victime.setGameMode(GameMode.ADVENTURE);
					tasks.remove(this);
					this.cancel();
				}else{
					Title.sendUniqueSubTitle(victime,"§b§lRespawn dans §6§l"+tasks.get(this)+" §b§lseconde(s)", 20);
					
					int i = tasks.get(this) -1;
					tasks.remove(this);
					tasks.put(this, i);
				}
			}
		}.runTaskTimer(StickGun.instance, 0, 20);	
	}

	private void setHoloAndPoins(Player killer){
		if(killer != null){
		 Location loc = killer.getLocation().add(0.0D, 3D, 0.0D);
		 	loc = loc.add(loc.getDirection().getX(), loc.getDirection().getY() - 0.09D, 
		    loc.getDirection().getZ());
         Hologram hologram = new Hologram(loc,"§a+1 Points",-2D);
         hologram.spawntemp(30);
		}
	}
	
}
