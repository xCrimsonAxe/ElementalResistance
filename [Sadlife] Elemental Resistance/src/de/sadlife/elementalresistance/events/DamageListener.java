package de.sadlife.elementalresistance.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;

public class DamageListener implements Listener {
	final BukkitAPIHelper api = new BukkitAPIHelper();
    @EventHandler
	public void onMonsterAttack(EntityDamageEvent e) {
    	if (e.getCause() == DamageCause.PROJECTILE) {

    	}
    }
}
