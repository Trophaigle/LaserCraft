package fr.lasercraft.stickgun.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lasercraft.lasercraftapi.events.CommandHubEvent;
import fr.lasercraft.stickgun.GameState;
import fr.lasercraft.stickgun.PlayerDeathSnowballEvent;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.commands.CameraExecutor;
import fr.lasercraft.stickgun.manager.PointAndKillManager;
import fr.lasercraft.stickgun.teams.TeamManager;
import net.minecraft.server.v1_8_R3.Material;

public class GameListener implements Listener {

	@EventHandler
	public void oHub(CommandHubEvent e){
		Player p = e.getPlayer();
		if(!GameState.isState(GameState.LOBBY)){
			if(StickGun.playerInGame.contains(p)){
		p.sendMessage("§e--------------------------------------------");
		p.sendMessage("§6Fin de partie sur le serveur: §a"+Bukkit.getServerName());
		p.sendMessage("§7Total kill(s) sur la partie: §e"+PointAndKillManager.getKills(p));
		p.sendMessage("§7Total gain de §eCoins §7sur la partie: §e"+PointAndKillManager.getCoins(p));
		p.sendMessage("§e--------------------------------------------");
		PointAndKillManager.coins.remove(p);
		PointAndKillManager.kills.remove(p);
		}
		p.getActivePotionEffects().clear();
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF("hub");
		p.sendPluginMessage(StickGun.instance, "BungeeCord", out.toByteArray());
		StickGun.playerInGame.remove(p);
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}

	@EventHandler
	public void onArmorWear(InventoryClickEvent e) {
	      if (e.getSlotType().equals(SlotType.ARMOR)) {
	         e.setCancelled(true);
	      }
	      e.setCancelled(true);
	 }
	
	@EventHandler
	public void onFall(EntityDamageEvent e){
		if(e.getCause() == DamageCause.FALL){
			e.setDamage(0);
			e.setCancelled(true);
		}
	
	}
	
	/*@EventHandler
	public void onCameraPlayer(PlayerInteractAtEntityEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode().equals(GameMode.SPECTATOR)){
			if(e.getRightClicked().getType() == EntityType.PLAYER){
				Player target = (Player) e.getRightClicked();
				if(CameraExecutor.isCamera.get(p) == false){
					CameraExecutor.sendCamera(target, p);
					e.setCancelled(true);
				}
			}
		}
	}*/
	
	/*@EventHandler
	public void onSnick(PlayerToggleSneakEvent e){
		Player p = e.getPlayer();
		if(CameraExecutor.isCamera.get(p) == true){
			CameraExecutor.deleteCamera(p);
			// DELETE CAMERA
			//e.setCancelled(true);
			p.teleport(CameraExecutor.targetloc.get(p));
	
		}
	}*/
	
	@EventHandler
	public void onClic(EntityDamageByEntityEvent e){
		
		if(e.getDamager().getType().equals(EntityType.SNOWBALL)){
			//TROUVE GETSHOOTER
			Snowball s = (Snowball) e.getDamager();
			Player damager = (Player) s.getShooter();
			
			if(e.getEntity() instanceof Player){
				
			Player victime = (Player) e.getEntity();
			
			if(GameState.isState(GameState.GAME)){
			if(TeamManager.getInstance().isBlue(damager) && TeamManager.getInstance().isRed(victime)){
				e.setDamage(30);
				Bukkit.getPluginManager().callEvent(new PlayerDeathSnowballEvent(victime, damager));
				
			}else if(TeamManager.getInstance().isRed(damager) && TeamManager.getInstance().isBlue(victime)){
				e.setDamage(30);
				Bukkit.getPluginManager().callEvent(new PlayerDeathSnowballEvent(victime, damager));
				
			}else{
				e.setDamage(0);
				e.setCancelled(true);
					}
				}
			}else{
				e.setDamage(30);
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		e.setCancelled(true);
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(StickGun.instance.zone.isInCube(p)){
			if(GameState.isState(GameState.LOBBY)){
			p.teleport(new Location(p.getWorld(), 679, 179, -1081));
			}
			
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e){
		e.setCancelled(true);
		if (e.toWeatherState()) {
		        World world = Bukkit.getWorld("world");
		        if (e.getWorld() == world)
		        {
		          e.setCancelled(true);
		          world.setStorm(false);
		          world.setThundering(false);
		          world.setWeatherDuration(0);
		        }
		  }
	}
}