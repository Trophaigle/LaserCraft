package fr.lasercraft.stickgun.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lasercraft.lasercraftapi.BDD.MySQL;
import fr.lasercraft.lasercraftapi.BDD.RankUnit;
import fr.lasercraft.stickgun.teams.TeamManager;

public class PlayerChatListener implements Listener {
	
	private HashMap<UUID, Long> cooldown = new HashMap<>();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();
			
		UUID uuid = event.getPlayer().getUniqueId();
         if(cooldown.containsKey(uuid)) {
                 float time = (System.currentTimeMillis() -  cooldown.get(uuid)) / 1000;
                 //Si vous souhaitez que le cooldown soit de trois secondes vous pouvez mettre
                 // time < 3.0f
                 if (time < 3.0f) {
                         event.setCancelled(true);
                         event.getPlayer().sendMessage("§7[§eSpam§7] §7Prenez une pause entre vos phrases !");
                         event.setCancelled(true);
                         return;
                 } else {
                         cooldown.put(uuid, System.currentTimeMillis());
                 }
         } else {
                 cooldown.put(uuid, System.currentTimeMillis());
         }
		
		
		RankUnit rank = MySQL.getRank(p);
		String o = rank.getName();
		String pref = "["+o+"]";
		
		String msgTeamRed = "(Team) §c"+pref+" "+p.getName()+"§f: ";
		String msgTeamBlue = "(Team) §9"+pref+" "+p.getName()+"§f: ";
		String msgAll = "(All) "+pref+" "+p.getName()+"§f: ";
		String msgAllBlue = "(All) §9"+pref+" "+p.getName()+"§f: ";
		String msgAllRed = "(All) §c"+pref+" "+p.getName()+"§f: ";
			
	
		String s1 = event.getMessage();
		char s = s1.charAt(0);
		String s7 = String.valueOf(s);
		if(s7.equalsIgnoreCase("!")){
			
			StringBuffer buff = new StringBuffer (event.getMessage());
			buff.deleteCharAt (0); 
						
			if(!TeamManager.getInstance().isBlue(p) && (!TeamManager.getInstance().isRed(p))){
				Bukkit.broadcastMessage(msgAll+event.getMessage());
			}else{
				if(TeamManager.getInstance().isBlue(p)){
				
					msgAllBlue = msgAllBlue + "§f"+buff;
					
				Bukkit.broadcastMessage(msgAllBlue);
				}
				
				if(TeamManager.getInstance().isRed(p)){
					msgAllRed = msgAllRed + "§f"+buff;
					Bukkit.broadcastMessage(msgAllRed);
					}
				
			}
			event.setCancelled(true);
			}else{
				if(TeamManager.getInstance().isBlue(p)){
					for(Player players : Bukkit.getOnlinePlayers()){
						if(TeamManager.getInstance().isBlue(players)){
							players.sendMessage(msgTeamBlue+event.getMessage());
							event.setCancelled(true);
							return;
							
							}
					 }
				}
				
				if(TeamManager.getInstance().isRed(p))
				for(Player players : Bukkit.getOnlinePlayers()){
					if(TeamManager.getInstance().isRed(players)){
						players.sendMessage(msgTeamRed+event.getMessage());	
						event.setCancelled(true);
						return;
						}
					}
				
				Bukkit.broadcastMessage(msgAll+event.getMessage());
				
		}
		event.setCancelled(true);
	
	}
	
	@EventHandler
    public void onLeave(PlayerQuitEvent e) {
            cooldown.remove(e.getPlayer().getUniqueId());
    }
}
	

