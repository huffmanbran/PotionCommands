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
			@SuppressWarnings("unused")
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
		
		int supply = duration; //yes I know
		
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
		int nvd = getConfig().getInt("nv-duration");
		int invd = getConfig().getInt("invisibility-duration");
		
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
			case "invisible": case "invisibility": potion = PotionEffectType.INVISIBILITY; baseName = "wither"; duration = invd; break; //TODO: 1.4
			case "nightvision": case "nv": potion = PotionEffectType.NIGHT_VISION; baseName = "nightvision"; duration = nvd; break; //TODO: 1.4
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

			
	
			if (args.length == 0)
			{
				sender.sendMessage(ChatColor.RED + "Usage: /potion <effects> or /potion [user] <effect> [duration] [amplifier]");
				return false;
			}
			//TODO: Restructure commands into one place (too much repetition for my likings :D)
			if (args[0].equalsIgnoreCase("effects") && (player.hasPermission("PotionCommands.effects") || player.isOp())) 
			{
				sender.sendMessage(ChatColor.AQUA + "Blindness, confusion, damage resistance, fast digging, fire resistance, harm, heal, hunger, jump, poison, regeneration, slowness, swiftness, strength, water breathing, weakness, freakout/scare, flicker");
				return true;
			}
			
		try
		{
			//Flexible command check
			//I was probably drunk when writing these comments :\
		
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
				return true;
				
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
					//They've given us everything!
					sender.sendMessage(this.applyEffect(target.getName(), args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), sender));
					
				}
				return true;
				
			}
		}
		catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "PotionCommands encountered an error");
			sender.sendMessage(ChatColor.GREEN + "Details are in the console. Brighten our day with a bug report!");
			
			getLogger().severe(e.toString());
			return true;

		}
			
			
			
			//Bang and the code is gone...
		} 
		
		
		return false;
	}
}
