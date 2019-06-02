package de.sadlife.elementalresistance.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;


public class AnvilCraftListener implements Listener{
    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent e) {
    	final ItemStack first = e.getInventory().getItem(0);
    	final ItemStack second = e.getInventory().getItem(1);
    	if (!(isSingle(first) && isSingle(second))) {return;}
    }

    public void onClick(InventoryClickEvent e) {
    	if (e.getInventory().getType() == InventoryType.ANVIL && e.getRawSlot() == 2) {
    		final AnvilInventory anvil = (AnvilInventory) e.getInventory();
    		if (anvil.getRepairCost() == 0
                    && isPresent(anvil.getItem(2))
                    && !isPresent(e.getWhoClicked().getItemOnCursor()) || !e.getAction().name().startsWith("PICKUP")) {
                final Player player = (Player)e.getWhoClicked();
                if (player.getGameMode() == GameMode.CREATIVE) {
                    player.setItemOnCursor(anvil.getItem(2));
                    anvil.clear();
                }
            }
    	}
    }







    private boolean isSingle(final ItemStack item) {
        return isPresent(item) && item.getAmount() == 1;
    }
    public static boolean isPresent(final ItemStack item) {
        return item != null && item.getType() != Material.AIR;
    }

//    public void onAnvilUse(InventoryClickEvent e) {
//    	if (!(e.getWhoClicked() instanceof Player)
//    		|| e.getClickedInventory() == null
//    		|| e.getCurrentItem() == null
//    		|| e.getClickedInventory().getType() != InventoryType.ANVIL) {
//    		System.out.println("death1");
//			return;
//		}
//
//    	final ItemMeta meta = e.getCurrentItem().getItemMeta();
//    	if(meta == null || !meta.hasDisplayName()) {
//    		System.out.println("death3");
//			return;
//		}
//    	final Inventory anvil = e.getClickedInventory();
//    	if (anvil.getItem(0) == null || anvil.getItem(1) == null) {
//			return;
//		}
//
//    	if (anvil.getItem(1).getType() == Material.DIAMOND_BOOTS) {
//    		System.out.println("BOOTS in Slot 1!!!");
//    	}
//    	if (anvil.getItem(0).getType() == Material.DIAMOND_BOOTS) {
//    		System.out.println("BOOTS in Slot 0!!!");
//    	}
//    }
}
