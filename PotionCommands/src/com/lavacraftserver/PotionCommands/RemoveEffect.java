package com.lavacraftserver.PotionCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class RemoveEffect implements CommandExecutor
{

	public PotionCommands plugin;
	public RemoveEffect(PotionCommands p)
	{
		this.plugin = p;
	}
				
				
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		Boolean auth = false;
		if (sender.hasPermission("PotionCommands.remove") || sender.isOp()) 
		{
			auth = true;		
			//User is allowed to use the command
		}
				
				
			
		if (commandLabel.equalsIgnoreCase("removepotion")) 
		{
			if (!(sender instanceof Player)) 
			{
				sender.sendMessage("[PotionEffects] Sorry, this command can only be run by a player!");
				return true;
			}
				
				 
			else //:
			{
				final Player player = (Player) sender;
				
				
			
				
				if (!(auth == true)) 
				{
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
					return true;
				}
				
				
				if ((args[0].equalsIgnoreCase("blind") || args[0].equalsIgnoreCase("blindness")) && auth == true && (player.hasPermission("PotionCommands.remove.blindness") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.BLINDNESS); //Remove whole type, not a certain effect
					return true;
				}
				
				
				
				if ((args[0].equalsIgnoreCase("confuse") || args[0].equalsIgnoreCase("confusion") || args[0].equalsIgnoreCase("nausea")) && auth == true && (player.hasPermission("PotionCommands.effect.confusion") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.CONFUSION); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("dmgresist") || args[0].equalsIgnoreCase("dr") && auth == true && (player.hasPermission("PotionCommands.effect.damageresistance") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("fastdig") || args[0].equalsIgnoreCase("digspeed") || args[0].equalsIgnoreCase("dig") || args[0].equalsIgnoreCase("haste") && auth == true && (player.hasPermission("PotionCommands.effect.haste") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.FAST_DIGGING); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("fireresistance") || args[0].equalsIgnoreCase("fireresist") || args[0].equalsIgnoreCase("fr") && auth == true && (player.hasPermission("PotionCommands.effect.fireresistance") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.FIRE_RESISTANCE); //Remove whole type, not a certain effect
					return true;
				}
				
					
				if (args[0].equalsIgnoreCase("harm") || args[0].equalsIgnoreCase("harming") && auth == true && (player.hasPermission("PotionCommands.effect.harming") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.HARM); //Remove whole type, not a certain effect
					return true;
				}
				
					
				if (args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("healing") && auth == true && (player.hasPermission("PotionCommands.effect.healing") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.HEAL); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("hunger") && auth == true && (player.hasPermission("PotionCommands.effect.hunger") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.HUNGER); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("jump") || args[0].equalsIgnoreCase("highjump") || args[0].equalsIgnoreCase("jumpboost") && auth == true && (player.hasPermission("PotionCommands.effect.jumpboost") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.JUMP); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("poison") && auth == true && (player.hasPermission("PotionCommands.effect.poison") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.POISON); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("regeneration") || args[0].equalsIgnoreCase("regen") && auth == true && (player.hasPermission("PotionCommands.effect.regeneration") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.REGENERATION); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("slowness") || args[0].equalsIgnoreCase("slow") && auth == true && (player.hasPermission("PotionCommands.effect.slowness") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.SLOW); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("speed") || args[0].equalsIgnoreCase("swiftness") || args[0].equalsIgnoreCase("swift") && auth == true && (player.hasPermission("PotionCommands.effect.swiftness") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.SPEED); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("strength") || args[0].equalsIgnoreCase("strong") && auth == true && (player.hasPermission("PotionCommands.effect.strength") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.INCREASE_DAMAGE); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("waterbreathing") || args[0].equalsIgnoreCase("breathing") || args[0].equalsIgnoreCase("wb") && auth == true && (player.hasPermission("PotionCommands.effect.waterbreathing") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.WATER_BREATHING); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("weakness") || args[0].equalsIgnoreCase("weak") && auth == true && (player.hasPermission("PotionCommands.effect.weakness") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.WEAKNESS); //Remove whole type, not a certain effect
					return true;
				}
				
				
				if (args[0].equalsIgnoreCase("freakout") || args[0].equalsIgnoreCase("scare") && auth == true && (player.hasPermission("PotionCommands.effect.scare") || player.isOp())) 
				{
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.SLOW); //Remove whole type, not a certain effect
					return true;
				}
				
				
				

		
			}
				
				
		}
				
				
		return true;
	}
				
				
}
				
				
