package fr.lasercraft.stickgun.teams;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager {

	static TeamManager instance = new TeamManager();
	private TeamManager(){}
	public static TeamManager getInstance(){return instance;}
	private ArrayList<Player> blue = new ArrayList<Player>();
	private ArrayList<Player> red = new ArrayList<Player>();
	public ArrayList<String> redList = new ArrayList<>();
	public ArrayList<String> blueList = new ArrayList<>();
	public Scoreboard s;
	
	public ArrayList<Player> getBlueList(){
		return blue;
	}
	
	public ArrayList<Player> getRedList(){
		return red;
	}
	
	public ArrayList<String> getRedList1(){
		return redList;
	}
	
	public ArrayList<String> getBlueList1(){
		return blueList;
	}
	
	@SuppressWarnings("deprecation")
	public void addBluePlayer(Player player){
		if(red.contains(player)){
			red.remove(player);
			s.getTeam("Rouge").removePlayer(player);
			redList.remove(player.getName());
		}
		if(blue.contains(player)){
			player.sendMessage("§7Vous êtes déja dans l'équipe: §9Bleu");
			return;
		}
		blue.add(player);
		player.sendMessage("§9Vous rejoignez l'equipe bleue");
		s.getTeam("Bleu").addPlayer(player);
		blueList.add(player.getName());
	}
	
	@SuppressWarnings("deprecation")
	public void addRedPlayer(Player player){
		if(blue.contains(player)){
			blue.remove(player);
			s.getTeam("Bleu").removePlayer(player);
			blueList.remove(player.getName());
		}
		if(red.contains(player)){
			player.sendMessage("§7Vous êtes déja dans l'équipe: §cRouge");
			return;
		}
		red.add(player);
		player.sendMessage("§cVous rejoignez l'equipe rouge");
		s.getTeam("Rouge").addPlayer(player);
		redList.add(player.getName());
		//TeamTagUtils.setTeamTag(player);
	}
	
	@SuppressWarnings("deprecation")
	public void removeBluePlayer(Player player){
		if(blue.contains(player)){
			blue.remove(player);
			s.getTeam("Bleu").removePlayer(player);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void removeRedPlayer(Player player){
		if(red.contains(player)){
			red.remove(player);
			s.getTeam("Rouge").removePlayer(player);
		}
		
	}
	
	public void removePlayer(Player player){
		if(red.contains(player)){
			red.remove(player);
			
		
		}else if(blue.contains(player)){
			blue.remove(player);
			
		}else{
			return;
		}
	}
	
	public Integer getBlueSize(){
		return blue.size();
	}
	
	public Integer getRedSize(){
		return red.size();
	}
	
	public boolean isBlue(Player player){
		if(blue.contains(player))return true;
		return false;
	}
	
	public boolean isRed(Player player){
		if(red.contains(player))return true;
		return false;
	}
	
	public void clearArrayList(){
		red.clear();
		blue.clear();
	}
	
	@SuppressWarnings("deprecation")
	public void addInRandomTeam(Player player){
		if((!red.contains(player)) && (!blue.contains(player))){
			if(blue.size() <= red.size()){
				blue.add(player);
				player.sendMessage("§9Vous rejoignez l'equipe bleue");
				s.getTeam("Bleu").addPlayer(player);
			}else{
				red.add(player);
				player.sendMessage("§cVous rejoignez l'equipe rouge");
				s.getTeam("Rouge").addPlayer(player);
			}
			//TeamTagUtils.setTeamTag(player);
		}
	}
	
	public void removeTeams(Player player){
		if(isBlue(player)){
			removeBluePlayer(player);
		}else if(isRed(player)){
			removeRedPlayer(player);
		}
	}
	
	public void registerTeamsTag() {
	
		if(s.getTeam("Bleu") != null) {
	    	s.getTeam("Bleu").unregister();
	    }
	    
	    if(s.getTeam("Rouge") != null) {
	    	s.getTeam("Rouge").unregister();
	    }
	    
	    Team t1 = s.registerNewTeam("Bleu");
	    t1.setPrefix("§9");
	    
	    Team t2 = s.registerNewTeam("Rouge");
	    t2.setPrefix("§c");
		
	}
	
	public void registerKillTabList() {
		  if (this.s.getObjective("kills") != null) {
		      this.s.getObjective("kills").unregister();
		    }
		    Objective o = this.s.registerNewObjective("kills", "totalKillCount");
		    //playerKillCount compte le numbre de kill de joueur du joueur
		    //Voir http://minecraft.gamepedia.com/Scoreboard
		    o.setDisplayName(ChatColor.RED + "§e");
		    o.setDisplaySlot(DisplaySlot.PLAYER_LIST);
	}
}
