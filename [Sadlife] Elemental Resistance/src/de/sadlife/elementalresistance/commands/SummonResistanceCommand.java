package de.sadlife.elementalresistance.commands;

import org.bukkit.entity.Player;

import de.sadlife.elementalresistance.utils.ResistanceDialog;

public class SummonResistanceCommand extends PlayerCommand{



	public SummonResistanceCommand(String name) {
		super (name);
		setDescription("Summons equipment with custom resistance stats.");
	}

	@Override
	protected void run(Player player, String[] args) {
		new ResistanceDialog(player);
	}
}
