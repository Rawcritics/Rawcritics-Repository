package com.McSpazzy.Graveyard.Plugins;

import com.McSpazzy.Graveyard.Graveyard;

public class PluginsChecker{

	public final Graveyard plugin;

	public PluginsChecker(Graveyard instance) {
		plugin = instance;
	}

	public String checkForPermissions(){

		if(plugin.getServer().getPluginManager().getPlugin("Using PermissionsEx.") != null){
			return "PermissionsEx";
		}
		if(plugin.getServer().getPluginManager().getPlugin("Using Permissions.") != null){
			return "Permissions";
		}
		if(plugin.getServer().getPluginManager().getPlugin("Using PermissionsBukkit.") != null){
			return "PermissionsBukkit";
		}
		return "No permissions plugin found.";
		
	}
}
