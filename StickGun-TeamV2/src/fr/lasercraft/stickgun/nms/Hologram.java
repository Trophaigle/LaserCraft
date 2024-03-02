package fr.lasercraft.stickgun.nms;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import fr.lasercraft.stickgun.listener.DeathAndKillListener;

public class Hologram {
    
    String Text = "";
    double Height = 0;
    Location loc = null;
    ArmorStand Hologram = null;

   
   
    public Hologram(Location location,String Text,double Height){
            this.Text = Text;
            this.loc = location;
            this.Height = Height;
    }
   
    public Hologram(){
    }
   
    public void spawn(){
            this.loc.setY((this.loc.getY() + this.Height)-1.25);
            Hologram = (ArmorStand)this.loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            Hologram.setCustomName(this.Text);
            Hologram.setCustomNameVisible(true);
            Hologram.setGravity(false);
            Hologram.setVisible(false);
    }
   
    public void spawntemp(int Time){
            this.loc.setY((this.loc.getY() + this.Height)-1.25);
            Hologram = (ArmorStand)this.loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            Hologram.setCustomName(this.Text);
            Hologram.setCustomNameVisible(true);
            Hologram.setGravity(false);
            Hologram.setVisible(false);
            Bukkit.getScheduler().runTaskLater(DeathAndKillListener.plugin, new Runnable() {
                    @Override
                    public void run() {
                            remove();
                    }
            }, Time);
   
    }
   
    public void remove(){
            if(ifHologram()){
                    Hologram.remove();
            }
    }
   
    public void teleport(Location location){
            if(ifHologram()){
            Hologram.teleport(location);
            }
    }
   
    public void changeText(String Text){
            if(ifHologram()){
            Hologram.setCustomName(this.Text);
            }
    }
   
    public void setText(String Text){
    this.Text = Text;
    }
   
    public void setLocation(Location location){
    this.loc = location;
    }
   
    public void setHeight(int Height){
    this.Height = Height;
    }

    public Boolean ifHologram(){
            if(this.Hologram != null){
            return true;
            }else{
            return false;
            }
    }
   
   
   
   

}
