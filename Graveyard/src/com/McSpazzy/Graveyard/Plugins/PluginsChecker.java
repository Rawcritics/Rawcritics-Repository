package com.McSpazzy.Graveyard.Plugins;

import com.McSpazzy.Graveyard.Graveyard;

public class PluginsChecker{

	public final Graveyard plugin;

	public PluginsChecker(Graveyard instance) {
		plugin = instance;
	}

	public String checkForPermissions(){

		if(plugin.getServer().getPluginManager().getPlugin("PermissionsEx") != null){
			plugin.GraveyardConfig.setPermissionsPlugin("PermissionsEx");
			return "PermissionsEx";
		}
		if(plugin.getServer().getPluginManager().getPlugin("Permissions") != null){
			plugin.GraveyardConfig.setPermissionsPlugin("Permissions");
			return "Permissions";
		}
		if(plugin.getServer().getPluginManager().getPlugin("PermissionsBukkit") != null){
			plugin.GraveyardConfig.setPermissionsPlugin("PermissionsBukkit");
			return "PermissionsBukkit";
		}
		return "No permissions";
		
	}
}
