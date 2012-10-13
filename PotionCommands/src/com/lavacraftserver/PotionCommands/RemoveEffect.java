package com.lavacraftserver.PotionCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RemoveEffect {

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Boolean auth = false;
		if (sender.hasPermission("PotionCommands.remove") || sender.isOp()) {
			auth = true;				
		}
			
		if (commandLabel.equalsIgnoreCase("potion")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[PotionEffects] This command can only be run by a player!");
				return true;
			} else {
				final Player player = (Player) sender;
				if (!(auth == true)) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
					return true;
				}
				if ((args[0].equalsIgnoreCase("blind") || args[0].equalsIgnoreCase("blindness")) && auth == true && (player.hasPermission("PotionCommands.remove.blindness") || player.isOp())) {
					Player target = Bukkit.getPlayer(args[1]);
					target.removePotionEffect(PotionEffectType.BLINDNESS);
					return true;
				}
				if ((args[0].equalsIgnoreCase("confuse") || args[0].equalsIgnoreCase("confusion") || args[0].equalsIgnoreCase("nausea")) && auth == true && (player.hasPermission("PotionCommands.effect.confusion") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, confusiond * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("dmgresist") || args[0].equalsIgnoreCase("dr") && auth == true && (player.hasPermission("PotionCommands.effect.damageresistance") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, dmgresistd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("fastdig") || args[0].equalsIgnoreCase("digspeed") || args[0].equalsIgnoreCase("dig") || args[0].equalsIgnoreCase("haste") && auth == true && (player.hasPermission("PotionCommands.effect.haste") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, fastdiggingd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("fireresistance") || args[0].equalsIgnoreCase("fireresist") || args[0].equalsIgnoreCase("fr") && auth == true && (player.hasPermission("PotionCommands.effect.fireresistance") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, fireresistanced * 20, 1));
					return true;
				}	
				if (args[0].equalsIgnoreCase("harm") || args[0].equalsIgnoreCase("harming") && auth == true && (player.hasPermission("PotionCommands.effect.harming") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.HARM, harmd * 20, 1));
					return true;
				}	
				if (args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("healing") && auth == true && (player.hasPermission("PotionCommands.effect.healing") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, heald * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("hunger") && auth == true && (player.hasPermission("PotionCommands.effect.hunger") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, hungerd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("jump") || args[0].equalsIgnoreCase("highjump") || args[0].equalsIgnoreCase("jumpboost") && auth == true && (player.hasPermission("PotionCommands.effect.jumpboost") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, jumpd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("poison") && auth == true && (player.hasPermission("PotionCommands.effect.poison") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, poisond * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("regeneration") || args[0].equalsIgnoreCase("regen") && auth == true && (player.hasPermission("PotionCommands.effect.regeneration") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, regenerationd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("slowness") || args[0].equalsIgnoreCase("slow") && auth == true && (player.hasPermission("PotionCommands.effect.slowness") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("speed") || args[0].equalsIgnoreCase("swiftness") || args[0].equalsIgnoreCase("swift") && auth == true && (player.hasPermission("PotionCommands.effect.swiftness") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, speedd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("strength") || args[0].equalsIgnoreCase("strong") && auth == true && (player.hasPermission("PotionCommands.effect.strength") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, increasedmgd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("waterbreathing") || args[0].equalsIgnoreCase("breathing") || args[0].equalsIgnoreCase("wb") && auth == true && (player.hasPermission("PotionCommands.effect.waterbreathing") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, wbd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("weakness") || args[0].equalsIgnoreCase("weak") && auth == true && (player.hasPermission("PotionCommands.effect.weakness") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, weaknessd * 20, 1));
					return true;
				}
				if (args[0].equalsIgnoreCase("freakout") || args[0].equalsIgnoreCase("scare") && auth == true && (player.hasPermission("PotionCommands.effect.scare") || player.isOp())) {
					Player target = getServer().getPlayer(args[1]);
					target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, scared * 20, 1000));
					return true;
				}
				

		
			}
		}
		return true;
	}
}
