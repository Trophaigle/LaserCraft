package fr.lasercraft.stickgun.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.PacketPlayOutCamera;

public class CameraExecutor implements CommandExecutor {

	public static HashMap<Player,Boolean> isCamera = new HashMap<>();
	public static HashMap<Player, Location> targetloc = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		
		if(player.isOp()){
        if(args.length == 1){
            
            if(args[0].equalsIgnoreCase("leave")){
                    //LE METTRE DANS L API
                    deleteCamera(player);
                    player.teleport(targetloc.get(player));
            }else{
           
            if(Bukkit.getPlayer(args[0]) != null){
            Player target = (Player)Bukkit.getPlayer(args[0]);
            sendCamera(target, player);
            targetloc.put(player, target.getLocation());
           
            }else{
                    sender.sendMessage("§cHors-ligne");
            		}}
        		}else {
        			player.sendMessage("§c/camera leave");
        			player.sendMessage("§c/camera <joueurPseudo>");
        		}
	
		}
		
		return false;
	}

	public static void deleteCamera(Player player){
		PacketPlayOutCamera camera = new PacketPlayOutCamera();
        camera.a = player.getEntityId();
        player.setGameMode(GameMode.SURVIVAL);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(camera);
	}
	
	public static void sendCamera(Player target,Player player){
		 PacketPlayOutCamera camera = new PacketPlayOutCamera();
         camera.a = target.getEntityId();
        
         player.setGameMode(GameMode.SPECTATOR);
         ((CraftPlayer)player).getHandle().playerConnection.sendPacket(camera);
         targetloc.put(player, target.getLocation());
	}

}
