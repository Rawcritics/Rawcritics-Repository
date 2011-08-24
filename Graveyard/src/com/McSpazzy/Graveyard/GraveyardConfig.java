package com.McSpazzy.Graveyard;

import java.io.File;
import java.io.IOException;

import org.bukkit.util.config.Configuration;

public class GraveyardConfig {
	private Configuration config;
	public Graveyard plugin;
	private boolean permissions = false;
	private boolean discovery = false;
	private String permissionsplugin = "";
	
	public GraveyardConfig (Graveyard instance){
		plugin = instance;
	}

	public void loadConfig(){
		System.out.println("[Graveyard] Loading config.");
		config = plugin.getConfiguration();
		permissions = config.getBoolean("Permissions", false);
		discovery = config.getBoolean("Discovery", false);
	}

	public void saveConfig(){
		System.out.println("[Graveyard] Saving config.");
		config.save();
	}
	
	public boolean isUsingDiscovery(){
		return discovery;
	}
	
	public String getPermissionsPlugin(){
		return permissionsplugin;
	}
	
	public void setPermissionsPlugin(String text){
		permissionsplugin = text;
	}
	
	public boolean isUsingPermissions(){
		return permissions;
	}
	
	public void setUsingDiscovery(boolean using){
		discovery = using;
		config.setProperty("Discovery", discovery);
		config.save();
	}
	
	public void setUsingPermissions(boolean using){
		permissions = using;
		config.setProperty("Permissions", permissions);
		config.save();
	}

	public void init() {
		File configfile = new File(plugin.getDataFolder()+"/config.yml");
		
		File root = new File(plugin.directory);
		File points = new File(plugin.PointsDirectory);
		File player = new File(plugin.PlayerDirectory);

		if (!root.exists()) root.mkdir();
		if (!points.exists()) points.mkdir();
		if (!player.exists()) points.mkdir();
		
		if (!configfile.exists()) {
			try {
				System.out.println("[Graveyard] First run detected. Creating files and directories.");
				configfile.createNewFile();
				config.setProperty("Permissions", permissions);
				config.setProperty("Discovery", discovery);
				config.save();
			}	
			catch (IOException ex) {
				System.out.println("[Graveyard] Could not create or load configuration file.");
			}
		}
	}
}
