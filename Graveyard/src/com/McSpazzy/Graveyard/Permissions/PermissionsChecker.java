package com.McSpazzy.Graveyard.Permissions;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.McSpazzy.Graveyard.Graveyard;

public class PermissionsChecker{

	public final Graveyard plugin;
	private String PermsPlugin;
	
	public PermissionsChecker(Graveyard instance) {
		plugin = instance;
		PermsPlugin = plugin.GraveyardConfig.getPermissionsPlugin();
	}
	
	public boolean hasPermission(Player player, String permission){
		
		if (PermsPlugin.equalsIgnoreCase("PermissionsEX")){
			PermissionsExHandler(player, permission);
		}
		
		
		return false;
	}

	private boolean PermissionsExHandler(Player player, String permission){
		if(plugin.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
			PermissionManager permissions = PermissionsEx.getPermissionManager();

		    if(permissions.has(player, permission)){
		    	return true;
		    } else {
		    	return false;
		    }
		    
		} else {
		  plugin.log.info("[" + plugin.getDescription().getName()+"] Error while connecting to permissions plugin.");
		}
		
		
		
		return false;
	}
	
	
	
}
