package fr.lasercraft.stickgun.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OptionListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			switch(e.getItem().getItemMeta().getDisplayName()){
			case "§aVitesse §eI §7(Click-droit)":
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 20));
				p.getInventory().remove(e.getItem());
				break;
			}
		}
	}
	
}
