package com.McSpazzy.Graveyard.SpawnPoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

import com.McSpazzy.Graveyard.Graveyard;

public class SpawnPointHandler{

	public final Graveyard plugin;
	
	public SpawnPointHandler(Graveyard instance) {
		plugin = instance;
	}

	public void loadSpawnPoint(String filename, Server server) {

		Properties Points = new Properties();

		try {

			FileReader loadPoint = new FileReader(filename);
			Points.load(loadPoint);

			String name = Points.getProperty("Name");
			String world = Points.getProperty("World");
			Double PointX = Double.parseDouble(Points.getProperty("xpos"));
			Double PointY = Double.parseDouble(Points.getProperty("ypos"));
			Double PointZ = Double.parseDouble(Points.getProperty("zpos"));
			String Group = Points.getProperty("Group");
			String Message = Points.getProperty("Message", name);
			Points.clear();

			loadPoint.close();
			System.out.println("[Graveyard] Loading: " + world + "/" + name + ": " + PointX + ", " + PointY + ", " + PointZ);
			SpawnPoint newpoint = new SpawnPoint(name, server.getWorld(world), PointX, PointY, PointZ, Group, Message);
			newpoint.setPath(filename);
			plugin.SpawnPointList.put(name, newpoint);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveSpawnPoint(SpawnPoint point) {

		Properties pointProperties = new Properties();

		try
		{

			pointProperties.setProperty("Name", point.getName());
			pointProperties.setProperty("World", ""+point.getWorld().getName());
			pointProperties.setProperty("xpos", ""+point.getX());
			pointProperties.setProperty("ypos", ""+point.getY());
			pointProperties.setProperty("zpos", ""+point.getZ());
			pointProperties.setProperty("Group", point.getGroup());
			pointProperties.setProperty("Message", point.getSpawnMessage());

			point.setPath(plugin.PointsDirectory + point.getName() + ".cfg");

			FileOutputStream fstream = new FileOutputStream(plugin.PointsDirectory + point.getName() + ".cfg");
			pointProperties.store(fstream, "Spawn Point File");
			fstream.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("[Graveyard] Unable to save the point!");
		}
	}

	public void addSpawnPoint(SpawnPoint newpoint) {
		plugin.SpawnPointList.put(newpoint.getName(), newpoint);
		System.out.println("[Graveyard] Loading: " + newpoint.getWorld().getName() + "/" + newpoint.getName() + ": " + newpoint.getX() + ", " + newpoint.getY() + ", " + newpoint.getZ());
	}

	public SpawnPoint getSpawnPoint(String name) {
		if (plugin.SpawnPointList.containsKey(name)) {
			return plugin.SpawnPointList.get(name);
		}
		return null;
	}

	public boolean deleteSpawnPoint(String name) {
		if (plugin.SpawnPointList.containsKey(name)) {
			plugin.SpawnPointList.remove(name);
			return true;
		}
		return false;
	}

	public boolean disableSpawnPoint(String name, List<SpawnPoint> list) {
		int index = 0;
		for (SpawnPoint point: list) {
			if (point.getName().equalsIgnoreCase(name)) {
				System.out.println("[Graveyard] Removing: " + point.getWorld().getName() + "/" + name);
				list.remove(index);
				return true;
			}
			index++;
		}
		return false;
	}

	
	public Map<String, SpawnPoint> getWorldList(World world){

		Map<String, SpawnPoint> WorldPointList = new HashMap<String, SpawnPoint>();

		for (SpawnPoint Spawns : plugin.SpawnPointList.values()) {
			if (Spawns.getWorldName() == world.getName()) {
				WorldPointList.put(Spawns.getName(), Spawns);
			}
		}
		
		return WorldPointList;

	}
	
	public SpawnPoint getClosest(Location location) {

		Collection<SpawnPoint> list = getWorldList(location.getWorld()).values();

		try {
			
			Map<SpawnPoint, Integer> DistanceList = new HashMap<SpawnPoint, Integer>();
			Map<Integer, SpawnPoint> DistanceClosest = new HashMap<Integer, SpawnPoint>();

			for(SpawnPoint point : list){
				DistanceList.put(point, getDistance(location, point));
				DistanceClosest.put(getDistance(location, point), point);
			}

			int dist = 0;
			int min = 0;

			for(Integer point :  DistanceList.values())
			{
				//plugin.getServer().broadcastMessage("distance calc: " + point);
				dist = point;

				if(dist < min || min == 0){
					min = dist;
				}
			}

			SpawnPoint closest = DistanceClosest.get(min);
			return plugin.SpawnPointList.get(closest.getName());

		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int getDistance(Location location, SpawnPoint point){

		int distance = 0;
		double x1 = point.getX();
		double z1 = point.getZ();
		double x2 = location.getX();
		double z2 = location.getZ();
		distance = (int) Math.sqrt( Math.pow(x2-x1, 2) + Math.pow(z2-z1, 2));

		return distance;
	}

	public void test() {

		System.out.println("Handler Test");

	}

	public SpawnPoint getRandom() {
		//Random randomSpawn = new Random();
		//return list.get(randomSpawn.nextInt(list.size()));
		return null;
	}

	public boolean exists(String name) {
		if (plugin.SpawnPointList.containsKey(name)) {
			return true;
		}
		return false;
	}
	public void loadPoints() {
		for (File file : new File(plugin.PointsDirectory).listFiles()) loadSpawnPoint(file.getPath(), plugin.getServer());
	}
}
