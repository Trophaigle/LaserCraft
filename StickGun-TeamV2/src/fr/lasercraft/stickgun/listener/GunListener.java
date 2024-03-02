package fr.lasercraft.stickgun.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import fr.lasercraft.stickgun.particule.ParticuleManager;

public class GunListener implements Listener {
	
	public static Player p;
	//public static Player victime;
	private HashMap<UUID, Long> cooldown = new HashMap<>();
	
	 @EventHandler
	  public void onAK47Use(PlayerInteractEvent e)
	  {
		 
	    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
	    (e.getItem().getType() == Material.STICK)){
	    	UUID uuid = e.getPlayer().getUniqueId();
	         if(cooldown.containsKey(uuid)) {
	                 float time = (System.currentTimeMillis() -  cooldown.get(uuid)) / 1000;
	                 //Si vous souhaitez que le cooldown soit de trois secondes vous pouvez mettre
	                 // time < 3.0f
	                 if (time < 1.0f) {   
	                         return;
	                 } else {
	                         cooldown.put(uuid, System.currentTimeMillis());
	                 }
	         } else {
	                 cooldown.put(uuid, System.currentTimeMillis());
	         }
	      p = e.getPlayer();
	      Location loc = p.getLocation().add(0.0D, 1.5D, 0.0D);

	      Snowball    s = (Snowball) p.getWorld().spawnEntity(p.getLocation(), EntityType.SNOWBALL);
	      // s.setVelocity(new Vector(p.getLocation().getDirection().getX(), p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ()));
		    s.setShooter(p);  
		    p.launchProjectile(Snowball.class, new Vector(p.getLocation().getDirection().getX(), p.getLocation().getDirection().getY() -0.05D, p.getLocation().getDirection().getZ()).multiply(20));
	      for (int i = 0; i <= 240; i++)
	      {
	        loc = loc.add(loc.getDirection().getX(), loc.getDirection().getY() - 0.05D, 
	          loc.getDirection().getZ());
	        ParticuleManager.displayParticuleGun(loc);
	        /*for (Entity ent : getEntitiesByLocation(loc, 1.0F)) {
	          if (((ent instanceof LivingEntity)) && (ent == p)) {
	           
	         ((LivingEntity)ent).damage(20D);
	          }
	        }*/
	     
	        if (loc.getBlock().getType().isSolid()) {
	          break;
	        }
	      }
	      p.getLocation().getWorld().playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0F, 2.0F);
	    }
	  }

	  
	/*private List<Entity> getEntitiesByLocation(Location loc, float r)
	  {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		List<Entity> ent = new ArrayList();
	    for (Entity e : loc.getWorld().getEntities()) {
	      if (e.getLocation().distanceSquared(loc) <= r) {
	        ent.add(e);
	        if(e instanceof Player){
	        	victime = (Player) e;
	        }
	      }
	    }
	    return ent;
	  }	
	
	public static Player getShooter(){
		return p;
	}
	
	public static Player getVictime(){
		return victime;
	}*/
}
