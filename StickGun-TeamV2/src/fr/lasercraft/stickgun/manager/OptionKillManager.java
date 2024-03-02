package fr.lasercraft.stickgun.manager;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.lasercraft.lasercraftapi.menuitems.utils.ItemBuilder;



public class OptionKillManager {

	Player player;
	
	public OptionKillManager(Player p){
		this.player = p;
	}
	
	public void setItemSpeed(){
		ItemStack i = new ItemBuilder().type(Material.IRON_BOOTS).name("§aVitesse I §e(10s) §7(Click-droit)").lore(Arrays.asList("Click pour déclenchez ta vitesse")).build();
		player.getInventory().addItem(i);
	}
	
	public void setItemInvisibility(){
		ItemStack i = new ItemBuilder().type(Material.POTION).name("§aInvisibilité I §e(10s) §7(Click-droit)").lore(Arrays.asList("Click pour déclenchez ton invisibilité")).build();
		player.getInventory().addItem(i);
	}
	
	public void setItemInvincible(){
		ItemStack i = new ItemBuilder().type(Material.APPLE).name("§aInvinsibilité §e(10s) §7(Click-droit)").lore(Arrays.asList("Click pour déclenchez ton invinsibilité")).build();
		player.getInventory().addItem(i);
	}
}
