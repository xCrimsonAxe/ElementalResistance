package de.sadlife.elementalresistance;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.sadlife.elementalresistance.commands.SummonResistanceCommand;
import de.sadlife.elementalresistance.events.AnvilCraftListener;
import de.sadlife.elementalresistance.events.MonsterAttackListener;
import de.sadlife.elementalresistance.utils.Common;

public class ElementalResistance extends JavaPlugin{
	private static ElementalResistance instance;

	@Override
	public void onEnable(){
		instance = this;
		//new SimpleConfig("boots.yml");

		Common.registerCommand(new SummonResistanceCommand("summonresistance"));
		registerEvents();
	}

	private void registerEvents() {
		final PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new AnvilCraftListener(), this);
		pm.registerEvents(new MonsterAttackListener(), this);
	}

	public static ElementalResistance getInstance() {
		return instance;
	}

}
