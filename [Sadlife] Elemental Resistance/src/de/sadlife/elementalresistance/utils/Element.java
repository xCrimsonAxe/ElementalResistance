package de.sadlife.elementalresistance.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

public class Element {
	private static String elementtype = "fire";
	private static int elementpercent = 0;

	public static String GetType(Entity damager) {
		if (damager instanceof Player) {
			final Player p = (Player) damager;
			System.out.println("Damager was Instance of Player (" + p.getDisplayName() + ")");
			// Change when Playerelemental System is implemented

		}
		if (damager instanceof Mob) {
			final BukkitAPIHelper api = new BukkitAPIHelper();
			if (api.isMythicMob(damager)) {
				final MythicMob mob = api.getMythicMobInstance(damager).getType();
				final String searchedString = "ELEMENT";
				for(final String s : mob.getDamageModifiers()){
					if (s.contains(searchedString)) {
						final String shortenedString = s.trim().substring(8, s.length());
						System.out.println(shortenedString);
						elementtype = shortenedString;
						return elementtype;
					}
				}
			}
		}
		return elementtype;
	}

	public static double GetPercent(Entity damager) {
		if (damager instanceof Player) {
			final Player p = (Player) damager;
			System.out.println("Damager was Instance of Player (" + p.getDisplayName() + ")");
			elementpercent = 20;
			// Change when Playerelemental System is implemented

		}
		if (damager instanceof Mob) {
			final BukkitAPIHelper api = new BukkitAPIHelper();
			if (api.isMythicMob(damager)) {
				final MythicMob mob = api.getMythicMobInstance(damager).getType();
				final String searchedString = "PERCENT";
				for(final String s : mob.getDamageModifiers()){
					if (s.contains(searchedString)) {
						final String shortenedString = s.trim().substring(8, s.length());
						System.out.println(shortenedString);
						elementpercent = Integer.parseInt(shortenedString);
						return elementpercent;
					}
				}
			}
		}
		return elementpercent;
	}
}
