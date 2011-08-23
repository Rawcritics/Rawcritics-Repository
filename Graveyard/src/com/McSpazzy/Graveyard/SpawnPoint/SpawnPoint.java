package com.McSpazzy.Graveyard.SpawnPoint;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnPoint {
	private String name;
	private Location location;
	private String filename;
	private String spawngroup;
	private String spawnmessage;

	public SpawnPoint (String string, World world, double x, double y, double z, String group, String message){
		name = string;
		location = new Location(world, x, y, z);
		spawngroup = group;
		spawnmessage =  message;
	}

	public SpawnPoint (String string, Player player){
		name = string;
		location = player.getLocation();
		spawngroup = "All";
		spawnmessage =  "none";
	}

	public String getPath(){
		return filename;
	}
	
	public String getSpawnMessage(){
		return spawnmessage;
	}

	public String getGroup(){
		return spawngroup;
	}

	public String getWorldName(){
		return location.getWorld().getName();
	}

	public void setPath(String path){
		filename = path;
	}
	
	public void setSpawnMessage(String message){
		spawnmessage = message;
	}

	public Location getLocation(){
		return location;
	}

	public String getName(){
		return name;
	}

	public void setName(String string){
		name = string;
	}

	public void setGroup(String string){
		spawngroup = string;
	}

	public void setLocation(Location loc){
		location = loc;
	}

	public double getX(){
		return location.getX();
	}

	public double getY(){
		return location.getY();
	}

	public double getZ(){
		return location.getZ();
	}

	public World getWorld(){
		return location.getWorld();
	}
}
