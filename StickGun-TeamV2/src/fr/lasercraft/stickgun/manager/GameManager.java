package fr.lasercraft.stickgun.manager;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.stickgun.GameState;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.runnable.GameRunnable;
import fr.lasercraft.stickgun.teams.TeamManager;

public class GameManager {

	public static Location SpawnRed = new Location(Bukkit.getWorld("world"), -590.585, 29, -302.394, 56, -3.6F); //JAUNE
	public static Location SpawnBlue = new Location(Bukkit.getWorld("world"), -647.735, 29, -255.509, -144, -2.5F); //VERT

	
	public GameManager(){
		
		GameState.setState(GameState.GAME);
		
		for(Player p : Bukkit.getOnlinePlayers()){
		
			p.getInventory().clear();
			TeamManager.getInstance().addInRandomTeam(p);
			addStick(p);
			Bukkit.broadcastMessage("Vous recevez le §bBâton Fétiche");
			Bukkit.broadcastMessage("§6--------------------------------------------------------------");
			Bukkit.broadcastMessage("§7[§dIndex§7] Pour parler à tous les joueurs mettez [§d§l!§f§7] devant votre message :-p");
			Bukkit.broadcastMessage("§6--------------------------------------------------------------");
			ScoreboardSign cs = StickGun.instance.get(p);
			cs.removeLine(3);
			cs.removeLine(4);
			cs.removeLine(5);
			cs.removeLine(6);
			cs.setLine(4, "- Timer -");
			cs.setLine(7, "- Points -");
			cs.setLine(8, "   ");
			if(!StickGun.instance.cs.containsKey(p))
	            StickGun.instance.cs.put(p, cs);
		}
		
		for(Player red : TeamManager.getInstance().getRedList()){
			red.teleport(SpawnRed);
			setArmureRED(red);
		}
		
		for(Player blue : TeamManager.getInstance().getBlueList()){
			blue.teleport(SpawnBlue);
			setArmureBLUE(blue);
		}
		
		new GameRunnable().runTaskTimer(StickGun.instance, 0, 20);
		Bukkit.getServer().getWorld("world").setPVP(true);
		
	}
	
	public static void addStick(Player p){
		ItemStack s = new ItemStack(Material.STICK);
		ItemMeta m = s.getItemMeta();
		m.setDisplayName("§bBâton fétiche");
		ArrayList<String> ds = new ArrayList<>();
		ds.clear();
		ds.add("§7Clic pour tuer les joueurs de l'équipe adverse");
		m.setLore(ds);
		m.addEnchant(Enchantment.DURABILITY, 1, true);
		m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		s.setItemMeta(m);
		p.getInventory().setItem(0, s);
	}
	
	private void setArmureRED(Player pl) {
		
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta meta = (LeatherArmorMeta)chest.getItemMeta();
	    meta.setColor(Color.RED);
	    chest.setItemMeta(meta);
	    pl.getInventory().setChestplate(chest);
	    
	    ItemStack chest1 = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta1 = (LeatherArmorMeta)chest1.getItemMeta();
	    meta1.setColor(Color.RED);
	    chest1.setItemMeta(meta1);
	    pl.getInventory().setHelmet(chest1);
	    
	    ItemStack chest2 = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta meta2 = (LeatherArmorMeta)chest2.getItemMeta();
	    meta2.setColor(Color.RED);
	    chest2.setItemMeta(meta2);
	    pl.getInventory().setLeggings(chest2);
	    
	    ItemStack chest3 = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta meta3 = (LeatherArmorMeta)chest3.getItemMeta();
	    meta3.setColor(Color.RED);
	    chest3.setItemMeta(meta3);
	    pl.getInventory().setBoots(chest3);
	    
		
		
	}
	
	private void setArmureBLUE(Player pl) {
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta meta = (LeatherArmorMeta)chest.getItemMeta();
	    meta.setColor(Color.BLUE);
	    chest.setItemMeta(meta);
	    pl.getInventory().setChestplate(chest);
	    
	    ItemStack chest1 = new ItemStack(Material.LEATHER_HELMET);
	    LeatherArmorMeta meta1 = (LeatherArmorMeta)chest1.getItemMeta();
	    meta1.setColor(Color.BLUE);
	    chest1.setItemMeta(meta1);
	    pl.getInventory().setHelmet(chest1);
	    
	    ItemStack chest2 = new ItemStack(Material.LEATHER_LEGGINGS);
	    LeatherArmorMeta meta2 = (LeatherArmorMeta)chest2.getItemMeta();
	    meta2.setColor(Color.BLUE);
	    chest2.setItemMeta(meta2);
	    pl.getInventory().setLeggings(chest2);
	    
	    ItemStack chest3 = new ItemStack(Material.LEATHER_BOOTS);
	    LeatherArmorMeta meta3 = (LeatherArmorMeta)chest3.getItemMeta();
	    meta3.setColor(Color.BLUE);
	    chest3.setItemMeta(meta3);
	    pl.getInventory().setBoots(chest3);
	    
		
	}	
}
