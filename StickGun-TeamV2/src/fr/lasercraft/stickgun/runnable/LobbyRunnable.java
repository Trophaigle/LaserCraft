package fr.lasercraft.stickgun.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.stickgun.Gui.VirtualInventoryVoteMap;
import fr.lasercraft.stickgun.manager.GameManager;

public class LobbyRunnable extends BukkitRunnable{

	public static boolean start = false;
	public static int timer = 30;
	
	@Override
	public void run() {
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			pl.setLevel(timer);
		}
		
		/* MANQUE DE JOUEURS */
		if(Bukkit.getOnlinePlayers().size() < 1){
			Bukkit.broadcastMessage("Manque joueur");
			start = false;
			timer = 30;
			this.cancel();
			return;
		}
		
		if(Bukkit.getOnlinePlayers().size() == 0){
			timer = 30;
			this.cancel();
			start = false;
			return;
		}
		
		/* TEMPS A ZERO */
		if(timer == 0){
			for(Player p : Bukkit.getOnlinePlayers()){
			Title.sendTitle(p, "§aLa partie commence !", " ", 30);
			Title.sendActionBar(p, "§eCarte du vote: §b"+VirtualInventoryVoteMap.getVote());
			p.playSound(p.getLocation(), Sound.EXPLODE, 4.0F, 4.0F);
			}
			this.cancel();
			new GameManager();
			return;
		}
		
		/* MESSAGE TIMER */
		if((timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5 && timer != 0)){
			for(Player p : Bukkit.getOnlinePlayers()){
			Title.sendActionBar(p, "§6La partie commence dans §3"+timer+" §6seconde(s)");
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 4.0F, 4.0F);
			}
		}
		timer--;
	}

}
