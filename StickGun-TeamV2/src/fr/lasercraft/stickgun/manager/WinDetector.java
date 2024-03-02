package fr.lasercraft.stickgun.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.BDD.MySQL;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.nms.ChangeServeur;
import fr.lasercraft.stickgun.teams.TeamManager;

public class WinDetector {

	PointAndKillManager pm;
	int timereject = 13;
	
	@SuppressWarnings("static-access")
	public WinDetector(){
		
		if(pm.pointBleu >  pm.pointRouge){
			for(Player p : Bukkit.getOnlinePlayers()){
				p.getInventory().clear();
				Title.sendTitle(p, "§eFin de la partie !", "Victoire de l'équipe §9Bleu §f("+pm.getPointBleu()+" point(s))", 110);
				Bukkit.broadcastMessage("§e--------------------------------------------");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§6§lVictoire de l'équipe §9Bleu    §c§l! FELICITATION !");
				Bukkit.broadcastMessage(" ");
				for(Player blue : TeamManager.getInstance().getBlueList()){
					MySQL.addCoins(blue, 1);
					blue.sendMessage("Le gain de la victoire :) §e+1 §6LaserCoin §7(§aVictoire§7)");
				}
			}
		}else if(pm.pointRouge > pm.pointBleu){
			for(Player p : Bukkit.getOnlinePlayers()){
				Title.sendTitle(p, "§eFin de la partie !", "Victoire de l'équipe §cRouge §f("+pm.getPointRouge()+" point(s))", 110);
				Bukkit.broadcastMessage("§e--------------------------------------------");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§6§lVictoire de l'équipe §cRouge    §c§l! FELICITATION !");
				Bukkit.broadcastMessage(" ");
				for(Player blue : TeamManager.getInstance().getRedList()){
					MySQL.addCoins(blue, 1);
					blue.sendMessage("Le gain de la victoire :) §e+1 §6LaserCoin §7(§aVictoire§7)");
				}
			}
		}else if(pm.pointRouge == pm.pointBleu){
			for(Player p : Bukkit.getOnlinePlayers()){
				Title.sendTitle(p, "§eFin de la partie !", "Egalité §cRouge §f("+pm.getPointRouge()+" point(s)) §6et §9Bleu §f("+pm.getPointBleu()+" point(s))", 110);
				Bukkit.broadcastMessage("§e--------------------------------------------");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§6§lEgalité    §c§l! FELICITATION !");
				Bukkit.broadcastMessage(" ");
				for(Player player : StickGun.playerInGame){
					MySQL.addCoins(player, (long) 0.50);
					player.sendMessage("Le gain de la l'égalité :) §e+1 §6LaserCoin §7(§aEgalité§7)");
				}
			}
		}
		for(Player p : Bukkit.getOnlinePlayers()){
			p.setCustomNameVisible(true);
			//METTRE CE MESSAGE DANS L API
			p.sendMessage("§e--------------------------------------------");
			p.sendMessage("§6Fin de partie sur le serveur: §a"+Bukkit.getServerId());
			p.sendMessage("§7Total Kill(s) sur la partie: §e"+PointAndKillManager.getKills(p));
			p.sendMessage("§7Total gain de §eCoins §7sur la partie: §e"+PointAndKillManager.getCoins(p));
			p.sendMessage("§e--------------------------------------------");
		}
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				timereject--;
				if(timereject == 3){
					for(Player p : Bukkit.getOnlinePlayers()){
						ChangeServeur.changeServer(p, "hub01");
						//OU KICK OU RESTART
					}
				}
				if(timereject == 0){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload");
					this.cancel();
					timereject = 15;
				}
			}
		}.runTaskTimer(StickGun.instance, 0, 20);	
	}
}
