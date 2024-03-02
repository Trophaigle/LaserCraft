package fr.lasercraft.stickgun.manager;

import java.util.HashMap;
import org.bukkit.entity.Player;

public class PointAndKillManager {

	public static HashMap<Player, Integer> kills = new HashMap<>();
	public static HashMap<Player, Double> coins = new HashMap<>();
	
	public static int pointRouge = 0;
	public static int pointBleu = 0;
	
	public static Integer getKills(Player p){
		return kills.get(p);
	}
	
	public static Integer getPointRouge(){
		return pointRouge;
	}
	
	public static Integer getPointBleu(){
		return pointBleu;
	}
	
	public static Double getCoins(Player p){
		return coins.get(p);
	}
	
	public static void setCoins(Player p, double coinsint){
		double coins2 = coins.get(p) + coinsint;
		coins.put(p, coins2);
	}
	
	public static void setKills(Player p, int kill){
		int killes = kills.get(p) + kill;
		kills.put(p, killes);
	}	
	
	
}
