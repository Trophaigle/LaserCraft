package fr.lasercraft.stickgun.listener;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.lasercraft.lasercraftapi.menuitem.MenuItem2;
import fr.lasercraft.stickgun.Gui.VirtualInventoryVoteMap;

public class MenuItemVote extends MenuItem2{

	public MenuItemVote(String mapName) {
		super("§9Carte: §b"+mapName, new String[]{}, new ItemStack(Material.PAPER));
	}

	@Override
	public void onInventoryClicEvent(Player p) {
		if(name.contains("LaserGame")){
			VirtualInventoryVoteMap.sendMessageVote(p, "LaserGame");
			VirtualInventoryVoteMap.votelg++;
			p.closeInventory();
			return;
		}
		if(name.contains("Médiéval")){
			VirtualInventoryVoteMap.sendMessageVote(p, "Médiéval");
			VirtualInventoryVoteMap.votemd++;
			p.closeInventory();
			return;
		}
		if(name.contains("Random")){
			Random r = new Random();
			Integer i = VirtualInventoryVoteMap.votearrays.get(r.nextInt(2));
			i++;
			VirtualInventoryVoteMap.sendMessageVote(p, "Random");
			p.closeInventory();
		}
		
	}

}
