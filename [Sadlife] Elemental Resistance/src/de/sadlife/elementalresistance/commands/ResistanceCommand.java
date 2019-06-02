package de.sadlife.elementalresistance.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.sadlife.elementalresistance.utils.Resistance;

public class ResistanceCommand extends Command{
	public ResistanceCommand() {
		super ("resistance");
		setAliases(Arrays.asList("res"));
		setDescription("Displays your current Amount of elemental Resistance");

	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {

		if (sender instanceof Player) {
			final Entity p = (Player) sender;

			p.sendMessage("Feuerresistenz: " + Resistance.Get(p, "fire") + "%");
			p.sendMessage("Wasserresistenz: " + Resistance.Get(p, "water") + "%");
			p.sendMessage("Lichtresistenz: " + Resistance.Get(p, "light") + "%");
			p.sendMessage("Schattenresistenz: " + Resistance.Get(p, "shadow") + "%");
		} else {
			return true;

		}
		return true;
	}
}
