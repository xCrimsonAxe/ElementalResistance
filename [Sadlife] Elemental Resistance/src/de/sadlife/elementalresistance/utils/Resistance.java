package de.sadlife.elementalresistance.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

public class Resistance {
	private static int totalresistance = 0;

	public static int Get(Entity defender, String element) {
		String resistancetype;
		if (element.equalsIgnoreCase("fire") || element.equalsIgnoreCase("f")) {
			resistancetype = "FRESIST";
		} else if (element.equalsIgnoreCase("water") || element.equalsIgnoreCase("w")) {
			resistancetype = "WRESIST";
		} else if (element.equalsIgnoreCase("light") || element.equalsIgnoreCase("l")) {
			resistancetype = "LRESIST";
		} else if (element.equalsIgnoreCase("shadow") || element.equalsIgnoreCase("s")) {
			resistancetype = "SRESIST";
		} else {
			System.out.println("ERROR: Unknown Resistancetype found! (" + element + ")");
			System.out.println("ERROR: Setting Resistance to 0!");
			return totalresistance;
		}



		if (defender instanceof Player) {
			final Player p = (Player) defender;
			int bootsresistance = 0;
			int leggingsresistance = 0;

			if ((p.getInventory().getBoots() != null)) {
				final AdvancedItemStack boots = new AdvancedItemStack(p.getInventory().getBoots());
				bootsresistance = boots.getNBTTagInt(resistancetype, 0);
			}
			if ((p.getInventory().getLeggings() != null)) {
				final AdvancedItemStack leggings = new AdvancedItemStack(p.getInventory().getLeggings());
				leggingsresistance = leggings.getNBTTagInt(resistancetype, 0);
			}
			totalresistance = bootsresistance + leggingsresistance;
		}
		if (defender instanceof Mob) {
			final BukkitAPIHelper api = new BukkitAPIHelper();
			if (api.isMythicMob(defender)) {
				final MythicMob mob = api.getMythicMobInstance(defender).getType();
				for(final String s : mob.getDamageModifiers()){
					if (s.contains((resistancetype))) {
						final String shortenedString = StringUtils.left(s, -7);
						System.out.println(shortenedString);
						totalresistance = Integer.parseInt(shortenedString);
					}
				}
			}
		}
		return totalresistance;
	}
}
