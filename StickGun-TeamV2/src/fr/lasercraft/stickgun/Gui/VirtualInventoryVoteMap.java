package fr.lasercraft.stickgun.Gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.menuitems.utils.ItemBuilder;
import fr.lasercraft.lasercraftapi.virtualinventory.VirtualInventory;
import fr.lasercraft.stickgun.listener.MenuItemVote;

public class VirtualInventoryVoteMap extends VirtualInventory{

	public static int id = 1;
	
	public static int votelg = 0;
	public static int votemd = 0;
	public static ArrayList<Integer> votearrays = new ArrayList<>();
	
	public static void init(){
		votearrays.add(votelg);
		votearrays.add(votemd); 
	}
	
	public VirtualInventoryVoteMap() {
		super("Vote", 3, id);
		this.registerAndSetItem(new MenuItemVote("Random"), 18);
		this.registerAndSetItem(new MenuItemVote("LaserGame"), 19);
		this.registerAndSetItem(new MenuItemVote("Médiéval"), 20);
		ArrayList<String> list = new ArrayList<>();
		list.add("§7Votre vote permet de faire un sondage");
		ItemStack info = new ItemBuilder().type(Material.SIGN).name("§7Voter un cliquant sur une carte").lore(list).build();
		inventory.setItem(4, info);
	}

	@Override
	public Inventory getInventory() {
		
		return inventory;
	}

	@Override
	public void open(Player p) {
		p.openInventory(inventory);
		
	}
	
	public static String getVote(){
		if(votelg < votemd){
			return "LaserGame";
		}else if(votemd < votelg){
			return "Médiéval";
		}else if(votemd != 0 || votelg != 0){
			if(votemd == votelg){
				return "Vote Egalitaire";
			}
		}
		return "Aucun";
	}
	
	public static void sendMessageVote(Player p, String carte){
		for(Player pl : Bukkit.getOnlinePlayers()){
			Title.sendActionBar(pl, "§a"+p.getName()+" §ea voté pour la carte §b"+carte);
		}
	}


}
