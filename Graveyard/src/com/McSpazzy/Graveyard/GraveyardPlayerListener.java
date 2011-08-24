package com.McSpazzy.Graveyard;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.McSpazzy.Graveyard.SpawnPoint.SpawnPoint;

public class GraveyardPlayerListener extends PlayerListener {

	public static Graveyard plugin;

	public GraveyardPlayerListener(Graveyard instance) {
		plugin = instance;
	}

	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (plugin.SpawnPointList.size() > 0 && plugin.Permissions.hasPermission(event.getPlayer(), "graveyard.respawn")){
			SpawnPoint closest = plugin.SpawnHandler.getClosest(event.getPlayer().getLocation());
			if(!closest.getSpawnMessage().equalsIgnoreCase("none")){event.getPlayer().sendMessage(closest.getSpawnMessage());}
			event.setRespawnLocation(closest.getLocation());
		}
	}
}
