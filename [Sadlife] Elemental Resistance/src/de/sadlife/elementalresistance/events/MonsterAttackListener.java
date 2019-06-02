package de.sadlife.elementalresistance.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.sadlife.elementalresistance.utils.Element;
import de.sadlife.elementalresistance.utils.Resistance;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;

public class MonsterAttackListener implements Listener{

	final BukkitAPIHelper api = new BukkitAPIHelper();
    @EventHandler
	public void onMonsterAttack(EntityDamageByEntityEvent e) {
    	final Entity defender = e.getEntity();
    	Entity damager = e.getDamager();
    	e.setDamage(10);

    	if (!(damager instanceof Entity)) {
    		System.out.println("ERROR: Unknown Attackertype!");
    		return;
    	}
    	if (!(defender instanceof Entity)) {
    		System.out.println("ERROR: Unknown Defendertype!");
    		return;
    	}

    	// Get Attacker
    	if (e.getDamager() instanceof Arrow) {
    		final Arrow arrow = (Arrow) e.getDamager();
    		damager = (Entity) arrow.getShooter();
    	}
		final String damagerelement = Element.GetType(damager);
		double damagerelementalpercent = Element.GetPercent(damager);


    	// Get Defender
    	final int defenderresistence = Resistance.Get(defender, damagerelement);

		final double naturaldamage = e.getDamage();
    	final double physicalpercent = 100-damagerelementalpercent;

    		damagerelementalpercent = damagerelementalpercent - defenderresistence;
    		if (damagerelementalpercent < 0) {
    			damagerelementalpercent = 0;
    		}
    		System.out.println("Der urspruengliche Schaden beträgt: " + e.getDamage());
    		final double elementaldamage = naturaldamage*(damagerelementalpercent/100);
    		final double physicaldamage = naturaldamage*(physicalpercent/100);
    		System.out.println("Davon sind " + elementaldamage + " Elementarschaden (" + damagerelementalpercent + "%)");
    		System.out.println("und " + physicaldamage + " physischer Schaden (" + physicalpercent +"%)");



    		e.setDamage(elementaldamage+physicaldamage);
		}




}
