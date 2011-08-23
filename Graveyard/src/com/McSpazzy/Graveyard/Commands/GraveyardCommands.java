package com.McSpazzy.Graveyard.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.McSpazzy.Graveyard.Graveyard;
import com.McSpazzy.Graveyard.SpawnPoint.SpawnPoint;

public class GraveyardCommands implements CommandExecutor{

	public final Graveyard plugin;

	public GraveyardCommands(Graveyard instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = null;

		if (sender instanceof Player){
			player = (Player) sender;
		}


		
		if (args.length == 0) {
			return false;
		}


		
		//Command to tell the closest spawn point.
		if (args[0].equalsIgnoreCase("closest") && args.length == 1) {
			if (sender.getClass().toString().indexOf("Console") == -1){
				sender.sendMessage("Closest spawn point is: " + plugin.SpawnHandler.getClosest(player.getLocation()).getName());
				return true;
			} else {
				sender.sendMessage("You cannot use that command from the console.");
				return true;
			}
		}

		//Command to list the spawn points.
		if (args[0].equalsIgnoreCase("list") && args.length == 1) {
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
			for (SpawnPoint point: plugin.SpawnPointList.values()) sender.sendMessage(ChatColor.GRAY+ point.getName() + ": " + ChatColor.WHITE+ Math.round(point.getX()) +", " + Math.round(point.getY()) + ", " + Math.round(point.getZ()) + " : " + point.getWorldName());
					sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
					return true;
		}

		if (args[0].equalsIgnoreCase("list") && args.length == 2) {
			if (args[1].equalsIgnoreCase("world")){
				sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
				sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
				for (SpawnPoint point: plugin.SpawnHandler.getWorldList(player.getWorld()).values()) sender.sendMessage(ChatColor.GRAY+ point.getName() + ": " + ChatColor.WHITE+ Math.round(point.getX()) +", " + Math.round(point.getY()) + ", " + Math.round(point.getZ()) + " : " + point.getWorldName());
						sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
						return true;
			}
		}

		//Teleport help.
		if (args[0].equalsIgnoreCase("tp") && args.length == 1) {
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
			sender.sendMessage(ChatColor.GRAY+ "/" + command.getName() + ChatColor.WHITE+ " tp " + ChatColor.RED + "Name");
			sender.sendMessage(ChatColor.WHITE+ "Teleports player to the specified spawn point.");
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			return true;
		}

		//Command to teleport to the specified spawn point.
		if (args[0].equalsIgnoreCase("tp") && args.length > 1) {
			String pointname = "";
			for (int i = 1; i < args.length; i++) pointname += args[i] + " ";
			pointname = pointname.substring(0, pointname.length()-1);
			if (plugin.SpawnHandler.exists(pointname)) {
				player.teleport(plugin.SpawnHandler.getSpawnPoint(pointname).getLocation());
				player.sendMessage("Teleporting to: " + pointname);
			} else {
				player.sendMessage("Spawnpoint '" + pointname + "' does not exist.");
			}
			return true;
		}

		//Command to set the spawn point message.
		if (args[0].equalsIgnoreCase("message") && args.length > 1) {
			String message = "";
			for (int i = 1; i < args.length; i++) message += args[i] + " ";
			message = message.substring(0, message.length()-1);
			SpawnPoint closest = plugin.SpawnHandler.getClosest(player.getLocation());
			closest.setSpawnMessage(message);
			player.sendMessage(ChatColor.GRAY+ closest.getName() + ChatColor.WHITE+ " respawn message set to " + ChatColor.GREEN + message);
			plugin.SpawnHandler.saveSpawnPoint(closest);
			return true;
		}

		//Change Spawn Message Help.
		if (args[0].equalsIgnoreCase("message") && args.length == 1) {
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
			sender.sendMessage(ChatColor.GRAY+ "/" + command.getName() + ChatColor.WHITE+ " message " + ChatColor.RED + "Spawn Message");
			sender.sendMessage(ChatColor.WHITE+ "Changes the respawn message of the closest spawn point.");
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			return true;
		}

		//Add help.
		if (args[0].equalsIgnoreCase("add") && args.length == 1) {
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
			sender.sendMessage(ChatColor.GRAY+ "/" + command.getName() + ChatColor.WHITE+ " add " + ChatColor.RED + "Name");
			sender.sendMessage(ChatColor.WHITE+ "Adds a new spawn point at current location.");
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			return true;
		}

		//Command to add the spawn point at player location.
		if (args[0].equalsIgnoreCase("add") && args.length > 1) {
			String pointname = "";
			for (int i = 1; i < args.length; i++) pointname += args[i] + " ";
			pointname = pointname.substring(0, pointname.length()-1);
			SpawnPoint newpoint = new SpawnPoint(pointname, player);
			plugin.SpawnHandler.saveSpawnPoint(newpoint);
			plugin.SpawnHandler.addSpawnPoint(newpoint);
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
			sender.sendMessage(ChatColor.DARK_GREEN+ "Adding: " + ChatColor.GRAY+ pointname);
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			return true;
		}

		//Delete help.
		if (args[0].equalsIgnoreCase("delete") && args.length == 1) {
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
			sender.sendMessage(ChatColor.GRAY+ "/" + command.getName() + ChatColor.WHITE+ " remove " + ChatColor.RED + "Name");
			sender.sendMessage(ChatColor.WHITE+ "Permenently deletes specified spawn point.");
			sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			return true;
		}

		//Command to delete the specified spawn point.
		if (args[0].equalsIgnoreCase("delete") && args.length > 1){
			String pointname = "";
			for (int i = 1; i < args.length; i++) pointname += args[i] + " ";
			pointname = pointname.substring(0, pointname.length()-1);

			if (plugin.SpawnHandler.deleteSpawnPoint(pointname)) {
				sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
				sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
				sender.sendMessage(ChatColor.DARK_GREEN+ "Deleting: " + ChatColor.GRAY+ pointname);
				sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			} else {
				sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
				sender.sendMessage(ChatColor.YELLOW+ "Graveyard");
				sender.sendMessage(ChatColor.DARK_GREEN+ "Deleting: " + ChatColor.GRAY+ pointname);
				sender.sendMessage(ChatColor.RED+ "Error. Point does not exist.");
				sender.sendMessage(ChatColor.DARK_GREEN+ "*-------------------------*");
			}
			return true;
		}

		if (sender instanceof Player) {
			String fullCommand = "";
			for (String arg : args) fullCommand += arg + "";
					plugin.log.info(player.getName() + " tried command: " + command.getName() + " " + fullCommand);
		}
		return true;
	}



}
