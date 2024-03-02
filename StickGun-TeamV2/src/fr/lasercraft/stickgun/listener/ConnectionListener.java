package fr.lasercraft.stickgun.listener;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.menuitems.utils.ItemBuilder;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.stickgun.GameState;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.commands.CameraExecutor;
import fr.lasercraft.stickgun.manager.PointAndKillManager;
import fr.lasercraft.stickgun.runnable.LobbyRunnable;
import fr.lasercraft.stickgun.teams.TeamManager;

public class ConnectionListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.getInventory().clear();
		if(GameState.isState(GameState.LOBBY)){
		StickGun.playerInGame.add(p);
		p.teleport(new Location(p.getWorld(), -632.443, 206.06250, -268.517));
		Title.sendTitle(p, "§6StickGun", "§aChoisi vite ton équipe !", 80);
		p.performCommand("effect "+p.getName()+" clear");
		
		//ItemStack i = new ItemBuilder().type(Material.PAPER).name("§6Voter §f(Carte)").build();
		ItemStack hub = new ItemBuilder().type(Material.BED).name("Retourner au Hub").build();
		
		ItemStack air = new ItemStack(Material.AIR);
		
		p.getInventory().setItem(8, hub);
		//p.getInventory().setItem(6, i);
		p.getInventory().setChestplate(air);
		p.getInventory().setHelmet(air);
		p.getInventory().setBoots(air);
		p.getInventory().setLeggings(air);
		
		//p.getInventory().setItem(7, kits);
		giveItems(p);
		
		setScoreboard(p);
		
		for(Player pls : Bukkit.getOnlinePlayers()){
		Title.sendActionBar(pls, "§a"+p.getName()+" §erejoind la partie! §a(§b"+StickGun.playerInGame.size()+"§a/§b"+Bukkit.getMaxPlayers()+"§a)");
		}
		e.setJoinMessage(null);
		
		p.setHealth(20);
		
		PointAndKillManager.kills.put(p, 0);
		PointAndKillManager.coins.put(p, 0.00);
		CameraExecutor.isCamera.put(p, false);
		
		if (Bukkit.getOnlinePlayers().size() >= 1 && LobbyRunnable.start == false && StickGun.instance.getConfig().getBoolean("canStart") == true) {
			new LobbyRunnable().runTaskTimer(StickGun.instance, 0L, 20L);
			LobbyRunnable.start = true;
				}
		}else{
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("§eLa partie à déjà commencé, vous êtes désormais spectateur !");
			Title.sendTitle(p, "§6StickGun", "§eLa partie a déja commencé", 80);
			ItemStack b = new ItemBuilder().type(Material.COMPASS).name("Voir joueur(s)").build();
			p.getInventory().setItem(0, b);
			ItemStack hub = new ItemBuilder().type(Material.BED).name("Retourner au Hub").build();
			p.getInventory().setItem(8, hub);
			StickGun.playerspectator.add(p);
		}
		
	}

	@SuppressWarnings("deprecation")
	public static void giveItems(Player p){
		//1
		ItemStack red = new ItemStack(Material.BANNER, 1);
		BannerMeta meta = (BannerMeta) red.getItemMeta();
		meta.setBaseColor(DyeColor.getByDyeData((byte) 1));
		meta.setDisplayName("§cEquipe Rouge §7(Click droit)");
		ArrayList<String> playerlistred = new ArrayList<>();
		playerlistred.add(" ");
		for(Player pl : TeamManager.getInstance().getRedList()){
			playerlistred.add("§c- "+pl.getName());
		}
		playerlistred.add(" ");
		playerlistred.add("§7↪ Click pour rejoindre l'équipe §cRouge");
		
		meta.setLore(playerlistred);
		red.setItemMeta(meta);
		List<Pattern> patterns = new ArrayList<Pattern>();
		patterns.add(new Pattern(DyeColor.getByDyeData((byte) 0), PatternType.getByIdentifier("bt")));
		meta.setPatterns(patterns);
		
		//2
		ItemStack blue = new ItemStack(Material.BANNER, 1);
		BannerMeta metae = (BannerMeta) blue.getItemMeta();
		metae.setBaseColor(DyeColor.getByDyeData((byte) 4));
		metae.setDisplayName("§9Equipe Bleu §7(Click droit)");
		
		ArrayList<String> playerlistblue = new ArrayList<>();
		playerlistblue.add(" ");
		for(Player pl : TeamManager.getInstance().getBlueList()){
			playerlistblue.add("§9- "+pl.getName());
		}
		playerlistblue.add(" ");
		playerlistblue.add("§7↪ Click pour rejoindre l'équipe §9Bleu");
		
		metae.setLore(playerlistblue);
		blue.setItemMeta(metae);
		List<Pattern> patternse = new ArrayList<Pattern>();
		patternse.add(new Pattern(DyeColor.getByDyeData((byte) 0), PatternType.getByIdentifier("bt")));
		metae.setPatterns(patternse);
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			pl.getInventory().setItem(0, blue);
			pl.getInventory().setItem(1, red);
		}
	}
	
	private void setScoreboard(Player p) {
		ScoreboardSign cs = StickGun.instance.get(p);
        if(cs == null)
        {
            cs = new ScoreboardSign(p, "§7= §6§lStickGun §f§7=");
            cs.create();
            cs.setLine(6, "  ");
            cs.setLine(5, "- Equipe -");
            cs.setLine(4, "§cRouge §e(§f" + TeamManager.getInstance().getRedSize() + "§e)");
            cs.setLine(3, "§9Bleu §e(§f" + TeamManager.getInstance().getBlueSize() + "§e)");
            cs.setLine(2, " ");
        	cs.setLine(1, "§7Id §f"+ Bukkit.getServerName());
        	cs.setLine(0, "§6play.lasercraft.fr");
        }
            if(!StickGun.instance.cs.containsKey(p))
                StickGun.instance.cs.put(p, cs);
		
	}

}
