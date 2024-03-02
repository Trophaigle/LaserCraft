package fr.lasercraft.stickgun;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import fr.lasercraft.lasercraftapi.Cuboide;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.stickgun.Gui.VirtualInventoryVoteMap;
import fr.lasercraft.stickgun.commands.AddRespawn;
import fr.lasercraft.stickgun.commands.CameraExecutor;
import fr.lasercraft.stickgun.commands.CommandeManager;
import fr.lasercraft.stickgun.commands.SetSpawnBlue;
import fr.lasercraft.stickgun.commands.SetSpawnCommande;
import fr.lasercraft.stickgun.commands.SetSpawnRed;
import fr.lasercraft.stickgun.commands.StartExecutor;
import fr.lasercraft.stickgun.listener.ConnectionListener;
import fr.lasercraft.stickgun.listener.DeathAndKillListener;
import fr.lasercraft.stickgun.listener.GameListener;
import fr.lasercraft.stickgun.listener.GunListener;
import fr.lasercraft.stickgun.listener.OptionListener;
import fr.lasercraft.stickgun.listener.PlayerChatListener;
import fr.lasercraft.stickgun.listener.PlayerChoseTeamListener;
import fr.lasercraft.stickgun.nms.ChangeServeur;
import fr.lasercraft.stickgun.ping.ServerListPing;
import fr.lasercraft.stickgun.teams.TeamManager;
import fr.lasercraft.stickgun.utilities.LocationManager;

public class StickGun extends JavaPlugin{

	public HashMap<Player, ScoreboardSign> cs = new HashMap<>();
	public static StickGun instance;
	public static Cuboide zone;
	public static ArrayList<Player> playerInGame = new ArrayList<>();
	public static ArrayList<Player> playerspectator = new ArrayList<>();
	@Override
	public void onEnable() {
		instance = this;
		//new VirtualInventoryVoteMap();
		registerEvents();
		
		getServer().getWorld("world").setPVP(false);
		
		zone = new Cuboide(new Location(Bukkit.getWorld("world"),655,140,-1121), new Location(Bukkit.getWorld("world"),713 ,145,-1052));
	
		GameState.setState(GameState.LOBBY);
		
		TeamManager.getInstance().s = Bukkit.getScoreboardManager().getMainScoreboard();
		TeamManager.getInstance().registerTeamsTag();
		TeamManager.getInstance().registerKillTabList();
		
		getCommand("start").setExecutor(new StartExecutor());
		getCommand("camera").setExecutor(new CameraExecutor());
		getCommand("setSpawn").setExecutor(new SetSpawnCommande());
		getCommand("setMainSpawnRed").setExecutor(new SetSpawnRed());
		getCommand("setMainSpawnBlue").setExecutor(new SetSpawnBlue());
		getCommand("addRespawn").setExecutor(new AddRespawn());
		
		new CommandeManager();
		
		ChangeServeur.init(this);
		
		//VirtualInventoryVoteMap.init();
		
		saveDefaultConfig();
		LocationManager.getLocation().addLocation();
	
		super.onEnable();
	}
	
	private void registerEvents(){
		Bukkit.getPluginManager().registerEvents(new GunListener(), this);
		Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
		Bukkit.getPluginManager().registerEvents(new ServerListPing(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChoseTeamListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathAndKillListener(this), this);
		Bukkit.getPluginManager().registerEvents(new GameListener(), this);
		Bukkit.getPluginManager().registerEvents(new OptionListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
	}

	@Override
	public void onDisable() {
		
		super.onDisable();
	}

	public ScoreboardSign get(Player pls) {
		return cs.get(pls);
	}  
}
