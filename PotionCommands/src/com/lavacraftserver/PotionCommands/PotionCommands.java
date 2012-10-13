package com.lavacraftserver.PotionCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionCommands extends JavaPlugin 
{
	
	//public RemoveEffect RemoveEffect = new RemoveEffect(this);
	
	@Override
	public void onEnable() 
	{
		getLogger().info("PotionCommands successfully enabled!");
		getConfig().options().copyDefaults(true);
		saveConfig();
		getCommand("rpotion").setExecutor(new RemoveEffect(this));
	}

	@Override
	public void onDisable() 
	{
		getLogger().info("PotionCommands disabled");
	}
	
	public void applyEffect(String playerName, String type, int duration, int amplifier)
	{
		
		//Unimplemented improved command handler
		PotionEffectType potion;
		switch(type.toLowerCase())
		{
			case "blindness": case "blind": potion = PotionEffectType.BLINDNESS; break;
			case "nausea": case "confuse": case "confusion": potion = PotionEffectType.CONFUSION; break;
			case "dmgresist": case "dr": potion = PotionEffectType.DAMAGE_RESISTANCE; break;
			case "haste": case "dig": case "fastdig": case "digspeed": potion = PotionEffectType.FAST_DIGGING; break;
			case "fireresistance": case "fireresistance": case "fr": potion = PotionEffectType.FIRE_RESISTANCE; break;
			case "harm": case "harming": case "hurt": potion = PotionEffectType.HARM; break;
			case "heal": case "healing": case "health": potion = PotionEffectType.HEAL; break;
			case "hunger": case "hungry": case "food": potion = PotionEffectType.HUNGER; break;
			case "jump": case "highjump": case "jumpboost": potion = PotionEffectType.JUMP; break;
		    case "poison": potion = PotionEffectType.POISON; break;
		    case "regen": case "regenration": potion = PotionEffectType.REGENERATION; break;
		    case "slow": case "slowness": potion = PotionEffectType.SLOW; break;
		    case "speed": case "quick": case "swift": case "swiftness": potion = PotionEffectType.SPEED; break;
		    case "waterbreathing": case "breathing": potion = PotionEffectType.WATER_BREATHING; break;
			case "weak": case "weakness": potion = PotionEffectType.WEAKNESS; break;
			case "scare": case "freakout": potion = PotionEffectType.SLOW; break;
			case "flicker": case "dim": potion = PotionEffectType.BLINDNESS; break;
			default: potion = null; break;
		}
		
		if (potion != null)
		{
			
		}
	}
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		int blindnessd = getConfig().getInt("blindness-duration");
		int confusiond = getConfig().getInt("confusion-duration");
		int dmgresistd = getConfig().getInt("damage-resistance-duration");
		int fastdiggingd = getConfig().getInt("fast-digging-duration");
		int fireresistanced = getConfig().getInt("fire-resistance-duration");
		int harmd = getConfig().getInt("harming-duration");
		int heald = getConfig().getInt("healing-duration");
		int hungerd = getConfig().getInt("hunger-duration");
		int jumpd = getConfig().getInt("jump-boost-duration");
		int poisond = getConfig().getInt("poison-duration");
		int regenerationd = getConfig().getInt("regeneration-duration");
		int slowd = getConfig().getInt("slowness-duration");
		int speedd = getConfig().getInt("swiftness-duration");
		int increasedmgd = getConfig().getInt("strength-duration");
		int wbd = getConfig().getInt("water-breathing-duration");
		int weaknessd = getConfig().getInt("weakness-duration");
		int scared = getConfig().getInt("freak-out-duration");
		Boolean auth = false;
		
		if (sender.hasPermission("PotionCommands.use") || sender.isOp()) 
		{
			auth = true;				
		}
		
		if (commandLabel.equalsIgnoreCase("potion")) 
		{
			
			final CommandSender player = sender;
			if (!(auth == true)) 
			{
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
				return true;
			}
			if (args.length != 3 && args.length != 2 && auth == true) 
			{
				sender.sendMessage(ChatColor.RED + "Bad syntax: /potion <effect> <player> [duration]");
				return true;
			}
			
	
			if (args.length == 3 && auth == true) 
			{
				try 
				{
					blindnessd = Integer.parseInt(args[2]);
					confusiond = Integer.parseInt(args[2]);
					dmgresistd = Integer.parseInt(args[2]);
					fastdiggingd = Integer.parseInt(args[2]);
					fireresistanced = Integer.parseInt(args[2]);
					harmd = Integer.parseInt(args[2]);
					heald = Integer.parseInt(args[2]);
					hungerd = Integer.parseInt(args[2]);
					jumpd = Integer.parseInt(args[2]);
					poisond = Integer.parseInt(args[2]);
					regenerationd = Integer.parseInt(args[2]);
					slowd = Integer.parseInt(args[2]);
					speedd = Integer.parseInt(args[2]);
					increasedmgd = Integer.parseInt(args[2]);
					wbd = Integer.parseInt(args[2]);
					scared = Integer.parseInt(args[2]);
				}
				catch (Exception e) 
				{
					player.sendMessage(ChatColor.RED + "Duration must be an integer (in seconds)!");
					return true;
				}
			}

			//TODO: Restructure commands into one place (too much repetition for my likings :D)
			if (args[0].equalsIgnoreCase("effects") && (player.hasPermission("PotionCommands.effects") || player.isOp())) 
			{
				sender.sendMessage(ChatColor.AQUA + "Blindness, confusion, damage resistance, fast digging, fire resistance, harm, heal, hunger, jump, poison, regeneration, slowness, swiftness, strength, water breathing, weakness, freakout/scare, flicker");
				return true;
			}

			if ((args[0].equalsIgnoreCase("blind") || args[0].equalsIgnoreCase("blindness")) && auth == true && (player.hasPermission("PotionCommands.effect.blindness") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindnessd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied blindness to " + target.getName() + " for " + blindnessd + " seconds!");
				return true;
			}

			if ((args[0].equalsIgnoreCase("confuse") || args[0].equalsIgnoreCase("confusion") || args[0].equalsIgnoreCase("nausea")) && auth == true && (player.hasPermission("PotionCommands.effect.confusion") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, confusiond * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied confusion to " + target.getName() + " for " + confusiond + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("dmgresist") || args[0].equalsIgnoreCase("dr") && auth == true && (player.hasPermission("PotionCommands.effect.damageresistance") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, dmgresistd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied damage resistance to " + target.getName() + " for " + dmgresistd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("fastdig") || args[0].equalsIgnoreCase("digspeed") || args[0].equalsIgnoreCase("dig") || args[0].equalsIgnoreCase("haste") && auth == true && (player.hasPermission("PotionCommands.effect.haste") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, fastdiggingd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied haste to " + target.getName() + " for " + fastdiggingd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("fireresistance") || args[0].equalsIgnoreCase("fireresist") || args[0].equalsIgnoreCase("fr") && auth == true && (player.hasPermission("PotionCommands.effect.fireresistance") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, fireresistanced * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied fire resistance to " + target.getName() + " for " + fireresistanced + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("harm") || args[0].equalsIgnoreCase("harming") && auth == true && (player.hasPermission("PotionCommands.effect.harming") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.HARM, harmd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied harm to " + target.getName() + " for " + harmd + " seconds!");
				return true;
			}	

			if (args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("healing") && auth == true && (player.hasPermission("PotionCommands.effect.healing") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, heald * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied heal to " + target.getName() + " for " + heald + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("hunger") && auth == true && (player.hasPermission("PotionCommands.effect.hunger") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, hungerd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied hunger to " + target.getName() + " for " + hungerd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("jump") || args[0].equalsIgnoreCase("highjump") || args[0].equalsIgnoreCase("jumpboost") && auth == true && (player.hasPermission("PotionCommands.effect.jumpboost") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, jumpd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied jump boost to " + target.getName() + " for " + jumpd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("poison") && auth == true && (player.hasPermission("PotionCommands.effect.poison") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, poisond * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied poison to " + target.getName() + " for " + poisond + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("regeneration") || args[0].equalsIgnoreCase("regen") && auth == true && (player.hasPermission("PotionCommands.effect.regeneration") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, regenerationd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied regeneration to " + target.getName() + " for " + regenerationd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("slowness") || args[0].equalsIgnoreCase("slow") && auth == true && (player.hasPermission("PotionCommands.effect.slowness") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied slowness to " + target.getName() + " for " + slowd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("speed") || args[0].equalsIgnoreCase("swiftness") || args[0].equalsIgnoreCase("swift") && auth == true && (player.hasPermission("PotionCommands.effect.swiftness") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, speedd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied swiftness to " + target.getName() + " for " + speedd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("strength") || args[0].equalsIgnoreCase("strong") && auth == true && (player.hasPermission("PotionCommands.effect.strength") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, increasedmgd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied strength to " + target.getName() + " for " + increasedmgd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("waterbreathing") || args[0].equalsIgnoreCase("breathing") || args[0].equalsIgnoreCase("wb") && auth == true && (player.hasPermission("PotionCommands.effect.waterbreathing") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, wbd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied water breathing to " + target.getName() + " for " + wbd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("weakness") || args[0].equalsIgnoreCase("weak") && auth == true && (player.hasPermission("PotionCommands.effect.weakness") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, weaknessd * 20, 1));
				sender.sendMessage(ChatColor.AQUA + "Applied weakness to " + target.getName() + " for " + weaknessd + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("freakout") || args[0].equalsIgnoreCase("scare") && auth == true && (player.hasPermission("PotionCommands.effect.scare") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, scared * 20, 1000));
				sender.sendMessage(ChatColor.AQUA + "Scared " + target.getName() + " for " + scared + " seconds!");
				return true;
			}

			if (args[0].equalsIgnoreCase("flicker") && auth == true && (player.hasPermission("PotionCommands.effect.flicker") || player.isOp())) 
			{
				Player target = getServer().getPlayer(args[1]);
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15, 10));
				return true;
			}
		} 
		else 
		{
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
			return true;
		}
		
		return false;
	}
}
