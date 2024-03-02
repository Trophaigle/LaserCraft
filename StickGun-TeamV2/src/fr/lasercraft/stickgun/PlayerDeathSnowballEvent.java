package fr.lasercraft.stickgun;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDeathSnowballEvent extends Event{

	 private static final HandlerList handlers = new HandlerList();
     private Player victime;
     private Player killer;
 
     public PlayerDeathSnowballEvent(Player cible, Player shooter) {
        this.victime = cible;
        this.killer = shooter;
     }
     
     public Player getVictime(){
         return this.victime;
     }
     
     public HandlerList getHandlers(){
        return handlers;
     }
     
     public static HandlerList getHandlerList() {
        return handlers;
     }
     
     public Player getShooter(){
    	 return killer;
     }
}
