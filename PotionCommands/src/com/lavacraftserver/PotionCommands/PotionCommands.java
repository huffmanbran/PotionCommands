package com.lavacraftserver.PotionCommands;

import net.minecraft.server.DataWatcher;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import net.minecraft.server.EntityLiving;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
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
		if (getConfig().getBoolean("auto-update"))
		{
			Updater updater = new Updater(this, "potioncommands", this.getFile(), Updater.UpdateType.DEFAULT, false);
			getLogger().info("You've opted in to updates for PotionCommands.");
			getLogger().info("To disable, see config file");
		}
	}

	@Override
	public void onDisable() 
	{
		getLogger().info("PotionCommands disabled");
	}
	
	public String applyEffect(String playerName, String type, int duration, int amplifier, CommandSender sender)
	{
		String baseName = "null";
		if (Bukkit.getPlayer(playerName) == null)
		{
			return ChatColor.RED + "Couldn't find player";
		}
		//Unimplemented improved command handler
		
		
		int supply = duration;
		
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
		int witherd = getConfig().getInt("wither-duration");
		
		
		
		PotionEffectType potion;
		switch(type.toLowerCase())
		{
		
			case "blindness": case "blind": potion = PotionEffectType.BLINDNESS; baseName = "blindness"; duration = blindnessd; break;
			case "nausea": case "confuse": case "confusion": potion = PotionEffectType.CONFUSION; baseName = "confusion"; duration = confusiond; break;
			case "dmgresist": case "dr": potion = PotionEffectType.DAMAGE_RESISTANCE; baseName = "damageresistance"; duration = dmgresistd; break;
			case "haste": case "dig": case "fastdig": case "digspeed": potion = PotionEffectType.FAST_DIGGING; baseName = "haste"; duration = fastdiggingd; break;
			case "fire": case "fireresistance": case "fr": potion = PotionEffectType.FIRE_RESISTANCE; baseName = "fireresistance"; duration = fireresistanced; break;
			case "harm": case "harming": case "hurt": potion = PotionEffectType.HARM; baseName = "harming"; duration = harmd; break;
			case "heal": case "healing": case "health": potion = PotionEffectType.HEAL; baseName = "healing"; duration = heald; break;
			case "hunger": case "hungry": case "food": potion = PotionEffectType.HUNGER; baseName = "hunger"; duration = hungerd; break;
			case "jump": case "highjump": case "jumpboost": potion = PotionEffectType.JUMP; baseName = "jumpboost"; duration = jumpd; break;
		    case "poison": potion = PotionEffectType.POISON; baseName = "poison"; duration = poisond; break;
		    case "regen": case "regenration": potion = PotionEffectType.REGENERATION; baseName = "regeneration"; duration = regenerationd; break;
		    case "slow": case "slowness": potion = PotionEffectType.SLOW; baseName = "slowness"; duration = slowd; break;
		    case "speed": case "quick": case "swift": case "swiftness": potion = PotionEffectType.SPEED; baseName = "swiftness"; duration = speedd; break;
		    case "increaseddamage": case "damage": case "strong": case "strength": potion = PotionEffectType.INCREASE_DAMAGE; baseName = "strength"; duration = increasedmgd; break;
		    case "waterbreathing": case "breathing": potion = PotionEffectType.WATER_BREATHING; baseName = "waterbreathing"; duration = wbd; break;
			case "weak": case "weakness": potion = PotionEffectType.WEAKNESS; baseName = "weakness"; duration = weaknessd; break;
			case "scare": case "freakout": potion = PotionEffectType.SLOW; amplifier = 1000; baseName = "scare"; duration = scared; break;
			case "flicker": case "dim": potion = PotionEffectType.BLINDNESS; duration = 15; amplifier = 10; baseName = "flicker"; break;
			case "wither": case "witherboss": potion = null; baseName = "wither"; duration = witherd; break; //TODO: 1.4
			default: potion = null; break;
		}
		
		if (potion != null)
		{
			String perm = "PotionCommands.effect." + baseName; //Guaranteed to be initialised
			if (sender.getName() == getServer().getPlayer(playerName).getName())
			{
				perm = perm + ".self";
			}
			else
			{
				perm = perm + ".other";
			}
			
			if (!sender.hasPermission(perm))
			{
				return ChatColor.RED + "You need permission: " + perm;
			}
			Player target = getServer().getPlayer(playerName);
			if (supply != -1)
			{
				duration = supply; //-1 = use config
			}
			target.removePotionEffect(potion);
			target.addPotionEffect(new PotionEffect(potion, duration, amplifier));
			return ChatColor.GREEN + "Potion applied successfully";
		}
		else
		{
			
			return ChatColor.RED + "Couldn't handle your request. Invalid potion";
		}
	}
	
	
	public void addPotionGraphicalEffect(LivingEntity entity, int color, int duration)
	{
		final EntityLiving el = ((CraftLivingEntity) entity).getHandle();
		final DataWatcher dw = el.getDataWatcher();
		dw.watch(8, Integer.valueOf(color));

		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
		{
			public void run()
			{
				int c = 0;
				if (!el.effects.isEmpty())
				{
					c = net.minecraft.server.PotionBrewer.a(el.effects.values());
				}
				dw.watch(8, Integer.valueOf(c));
			}
		}, duration);
	}
		 
	
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
		int utilised = 20;
		Boolean auth = false;
		
		if (sender.hasPermission("PotionCommands.use") || sender.isOp()) 
		{
			auth = true;				
		}
		
		if (commandLabel.equalsIgnoreCase("particle")) 
		{
			
			if (!sender.hasPermission("PotionCommands.particle"))
			{
				sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
				return true;
			}
			int d = 0;
		try
		{
			if (args.length == 2 && sender instanceof Player)
			{
				Player p = (Player) sender;
				int i = Integer.parseInt(args[0].replace("#", ""), 16);  
				d = Integer.parseInt(args[1]);
				LivingEntity e = p;
				addPotionGraphicalEffect(e, i, d); 
				return true;
			}
			if (args.length == 3)
			{
				
				Player p = Bukkit.getPlayer(args[0]);
				int i = Integer.parseInt(args[1].replace("#", ""), 16);  
				d = Integer.parseInt(args[2]);
				LivingEntity e = p;
				addPotionGraphicalEffect(e, i, d); 
				return true;
			}
		}
		catch (Exception e)
		{
			if (d > 0)
			{
				sender.sendMessage(ChatColor.RED + "Bad syntax or unknown player or bad sender");
			}
			else
			{
				sender.sendMessage(ChatColor.GREEN + "Infinite particle effect activated");
				return true;
			}
			return false;
			
			
			
		}
			return false;
			
		}
		
		if (commandLabel.equalsIgnoreCase("potion")) 
		{
			
			final CommandSender player = sender;
			auth = true;

			
	

			//TODO: Restructure commands into one place (too much repetition for my likings :D)
			if (args[0].equalsIgnoreCase("effects") && (player.hasPermission("PotionCommands.effects") || player.isOp())) 
			{
				sender.sendMessage(ChatColor.AQUA + "Blindness, confusion, damage resistance, fast digging, fire resistance, harm, heal, hunger, jump, poison, regeneration, slowness, swiftness, strength, water breathing, weakness, freakout/scare, flicker");
				return true;
			}
			
			//Flexible command check
			if (args.length > 0 && Bukkit.getPlayer(args[0]) == null) //NPEs are evil
			{
				//We're assuming they've chosen an effect as the first arg
				//Yes this method sucks. See /potionf
				if (args.length == 1)
				{
					//They've only given us an effect
					//Fine, we'll supply in duration + amplifier to be defaults.
					if (sender instanceof Player)
						sender.sendMessage(this.applyEffect(sender.getName(), args[0], -1, 1, sender));
					else
						sender.sendMessage(ChatColor.RED + "Unsupported command sender -- supply a player"); //Damn console
				}
				
				if (args.length == 2)
				{
					//They've only given us an effect and duration (from my POV)
					//Fine, we'll supply in amplifier to be default.
					if (sender instanceof Player)
						sender.sendMessage(this.applyEffect(sender.getName(), args[0], Integer.parseInt(args[1]), 1, sender));
					else
						sender.sendMessage(ChatColor.RED + "Unsupported command sender -- supply a player"); //Damn console
				}
				
				if (args.length == 3)
				{
					//Wow! They've been generous and given us the amplifier
					//Awesome stuff here in PotionCommands.java!!
					if (sender instanceof Player)
						sender.sendMessage(this.applyEffect(sender.getName(), args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), sender));
					else
						sender.sendMessage(ChatColor.RED + "Unsupported command sender -- supply a player"); //Damn console -- once again :(
				}
				
			}
			else
			{
				Player target = Bukkit.getPlayer(args[0]);
				if (args.length == 1)
				{
					//They've only given us a player
					//What the hell are we supposed to do with a player?
					//My assumption is that Bukkit has melted and returned us a player from the effect supplied
					//In this case, we'll duplicate the functionality above (applying the effect to the sender)
					if (sender instanceof Player)
						sender.sendMessage(this.applyEffect(sender.getName(), args[0], -1, 1, sender));
					else
						sender.sendMessage(ChatColor.RED + "Unsupported command sender -- supply a player"); //Damn console
				}
				
				if (args.length == 2)
				{
					//They've only given us an player and an effect
					//Fine, we'll supply in the amplifier and duration to be default.
					
					sender.sendMessage(this.applyEffect(target.getName(), args[1], -1, 1, sender));
					
				}
				
				if (args.length == 3)
				{
					//We've got a duration! Woot!!
					sender.sendMessage(this.applyEffect(target.getName(), args[1], Integer.parseInt(args[2]), 1, sender));
					
				}
				
				if (args.length == 4)
				{
					//This player deserves some cake. They've given us everything!
					sender.sendMessage(this.applyEffect(target.getName(), args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), sender));
					
				}
				
				
			}
			//Commenting this out in case the stuff above has broken everything. That would be a real shame...
			/*
			if ((args[0].equalsIgnoreCase("blind") || args[0].equalsIgnoreCase("blindness"))) 
			{
				Player target = getServer().getPlayer(args[1]);
				//target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindnessd * 20, 1));
				//sender.sendMessage(ChatColor.AQUA + "Applied blindness to " + target.getName() + " for " + blindnessd + " seconds!");
				sender.sendMessage(applyEffect(target.getName(), args[0], -1, 100, sender));
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
			} */
			
			//Bang and the code is gone...
		} 
		
		
		return false;
	}
}
