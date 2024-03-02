package fr.lasercraft.stickgun.runnable;

import java.text.SimpleDateFormat;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.stickgun.GameState;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.manager.PointAndKillManager;
import fr.lasercraft.stickgun.manager.WinDetector;

public class GameRunnable extends BukkitRunnable{

	public static int timer = 60;
	
	public static boolean buucd = false;
	public static int timer_buucd = 300;
	
	@Override
	public void run() {
		
		for(Entry<Player, ScoreboardSign> entry : StickGun.instance.cs.entrySet()){
			String timer1 = new SimpleDateFormat("mm:ss").format(GameRunnable.timer * 1000);
            entry.getValue().setLine(3, "§fFin dans §6"+timer1);
            entry.getValue().setLine(6, "§cRouge §f"+PointAndKillManager.pointRouge+" point(s)");
            entry.getValue().setLine(5, "§9Bleu §f"+PointAndKillManager.pointBleu+" point(s)");
		}
		
		if(timer == 0){
			this.cancel();
			new WinDetector();
			GameState.setState(GameState.FINISH);
			Bukkit.getServer().getWorld("world").setPVP(false);
		}
		
		timer--;
		
		if(buucd == true) {
			timer_buucd--;
			if(timer_buucd == 0) {
				buucd = true;
				Bukkit.broadcastMessage("Fin de l'effet");
			}
		}
	}

}
