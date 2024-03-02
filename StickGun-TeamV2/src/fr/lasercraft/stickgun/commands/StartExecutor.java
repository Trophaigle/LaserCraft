package fr.lasercraft.stickgun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lasercraft.stickgun.GameState;
import fr.lasercraft.stickgun.runnable.LobbyRunnable;

public class StartExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if(p.isOp()){
		if(GameState.isState(GameState.LOBBY)){
		LobbyRunnable.timer = 1;
		
		p.sendMessage("§aVous démarez la partie, bon jeu !");
		Bukkit.broadcastMessage("§d"+p.getName()+" §ademarre la partie !");
				}
			}
		return false;
	}
}
