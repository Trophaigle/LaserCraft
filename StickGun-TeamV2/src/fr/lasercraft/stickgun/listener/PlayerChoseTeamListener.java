package fr.lasercraft.stickgun.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.lasercraft.lasercraftapi.inventory.InventoryHeadEvent;
import fr.lasercraft.lasercraftapi.menuitems.MenuItemInterface3;
import fr.lasercraft.lasercraftapi.shopitem.ShopItemInGameKit;
import fr.lasercraft.lasercraftapi.virtualinventory.VirtualInventory;
import fr.lasercraft.stickgun.StickGun;
import fr.lasercraft.stickgun.Gui.VirtualInventoryVoteMap;
import fr.lasercraft.stickgun.nms.ChangeServeur;
import fr.lasercraft.stickgun.teams.TeamManager;

public class PlayerChoseTeamListener implements Listener {

	@EventHandler
	public void onChoseTeam(PlayerInteractEvent e){
		Player p = e.getPlayer();
		switch(e.getItem().getItemMeta().getDisplayName()){
		case "§cEquipe Rouge §7(Click droit)":
			TeamManager.getInstance().addRedPlayer(p);
			ConnectionListener.giveItems(p);
			break;
		
		case "§9Equipe Bleu §7(Click droit)":
			TeamManager.getInstance().addBluePlayer(p);
			ConnectionListener.giveItems(p);
			break;
		case "§6Voter §f(Carte)":
			VirtualInventory.get(VirtualInventoryVoteMap.id).openInventory(p);
			break;
		case "Retourner au Hub":
			ChangeServeur.changeServer(p, "hub");
			break;
			
		default:
			break;
		}
		e.setCancelled(true);
	}
	
	 @EventHandler
	 public void onEvent(InventoryHeadEvent e){
		 Player p = e.getPlayer();
		 Inventory inv = Bukkit.createInventory(null, 3*9, "Joueur(s) en jeu");
		 for(Player player : StickGun.playerInGame){
			 ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			 SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			 skullMeta.setDisplayName(player.getName());
			 skullMeta.setOwner(player.getName());
			 skull.setItemMeta(skullMeta);
			 inv.addItem(skull);}
		 p.openInventory(inv);
	 }
	
}
