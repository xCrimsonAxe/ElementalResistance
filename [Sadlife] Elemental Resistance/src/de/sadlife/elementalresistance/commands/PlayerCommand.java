package de.sadlife.elementalresistance.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.sadlife.elementalresistance.utils.Common;

public abstract class PlayerCommand extends Command{

	protected PlayerCommand(String name) {
		super(name);
	}

	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			Common.tell(sender, "&4You must be a player!");
			return false;
		}

		run((Player) sender, args);
		return true;
	}

	protected abstract void run(Player player, String[] args);
}
