package fr.lasercraft.stickgun.particule;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class ParticuleManager {

	
	public static void displayParticuleGun(Location loc) {
		PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 5, null);
		//PacketPlayOutWorldParticles packet21 = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 0, 0, 0, 0, 5, null);
		//CRIT MAGIC
		for(Player p : Bukkit.getOnlinePlayers()){
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet2);
			//((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet21);
			
		}
	}
	
}
