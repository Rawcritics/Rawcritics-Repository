package com.McSpazzy.Graveyard;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.java.JavaPlugin;
import com.McSpazzy.Graveyard.Commands.GraveyardCommands;
import com.McSpazzy.Graveyard.Plugins.PluginsChecker;
import com.McSpazzy.Graveyard.SpawnPoint.SpawnPointHandler;
import com.McSpazzy.Graveyard.SpawnPoint.SpawnPoint;

public class Graveyard extends JavaPlugin
{
	public final Logger log = Logger.getLogger("Minecraft");
	private final GraveyardPlayerListener playerListener = new GraveyardPlayerListener(this);
	public String directory = "plugins/Graveyard";
	public String PointsDirectory = "plugins/Graveyard/points/";
	public String StringsDirectory = "plugins/Graveyard/strings/";
	public String PlayerDirectory = "plugins/Graveyard/players/";

	public SpawnPointHandler SpawnHandler = new SpawnPointHandler(this);

	public Map<String, SpawnPoint> SpawnPointList = new HashMap<String, SpawnPoint>();
	public GraveyardConfig GraveyardConfig = new GraveyardConfig(this);
	public PluginsChecker PlugnChecker = new PluginsChecker(this);
	
	


	public void onDisable()
	{
		GraveyardConfig.saveConfig();
		log.info("[" + this.getDescription().getName()+"] v"+this.getDescription().getVersion()+" unloaded successfully!");
	}

	public void onEnable()
	{

		GraveyardConfig.init();
		GraveyardConfig.loadConfig();

		loadPoints();
		
		if(GraveyardConfig.isUsingPermissions()){
			log.info("[" + this.getDescription().getName()+"] " + PlugnChecker.checkForPermissions());
		} else {
			log.info("[" + this.getDescription().getName()+"] Using OP defaults");
		}
			
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_RESPAWN, playerListener, Priority.High, this);

		log.info("[" + this.getDescription().getName()+"] v"+ this.getDescription().getVersion()+" loaded successfully!");
		
		
		getCommand("graveyard").setExecutor(new GraveyardCommands(this));

	}

	public void loadPoints() {
		for (File file : new File(PointsDirectory).listFiles()) SpawnHandler.loadSpawnPoint(file.getPath(), getServer());
	}

}
