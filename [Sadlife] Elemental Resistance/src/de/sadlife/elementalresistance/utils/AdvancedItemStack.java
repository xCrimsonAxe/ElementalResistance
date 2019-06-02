package de.sadlife.elementalresistance.utils;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_13_R2.NBTBase;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagInt;
import net.minecraft.server.v1_13_R2.NBTTagString;


public class AdvancedItemStack extends ItemStack {

	public AdvancedItemStack(Material type) {
		super(type);

	}
	public AdvancedItemStack(ItemStack stack) {
		super(stack);
	}


	public AdvancedItemStack(Material type, int amount) {
		super(type, amount);
	}

	public static AdvancedItemStack Resistance(Object displayname, Object slot, Object objfresist, Object objwresist, Object objlresist, Object objsresist, Object objlevel) {
		final int intfresist = Integer.parseInt(objfresist.toString());
		final int intwresist = Integer.parseInt(objwresist.toString());
		final int intlresist = Integer.parseInt(objlresist.toString());
		final int intsresist = Integer.parseInt(objsresist.toString());
		final int intlevel   = Integer.parseInt(objlevel.toString());
		Material item;

		if (slot.equals(EquipmentSlot.FEET)) {
			item = Material.DIAMOND_BOOTS;
		} else {
			item = Material.DIAMOND_LEGGINGS;
		}
		final AdvancedItemStack stack = new AdvancedItemStack(item);
			final NBTTagInt fire = new NBTTagInt(intfresist);
			final NBTTagInt water = new NBTTagInt(intwresist);
			final NBTTagInt light = new NBTTagInt(intlresist);
			final NBTTagInt shadow = new NBTTagInt(intsresist);
			final NBTTagInt level = new NBTTagInt(intlevel);
			stack.setNBTTag("FRESIST", fire);
			stack.setNBTTag("WRESIST", water);
			stack.setNBTTag("LRESIST", light);
			stack.setNBTTag("SRESIST", shadow);
			stack.setNBTTag("LEVEL", level);

		return stack;

	}



	public void setDisplayName(String name) {
		final ItemMeta m = this.getItemMeta();
		this.setItemMeta(m);
		final net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
		final ItemMeta meta = CraftItemStack.getItemMeta(nmsItem);
		meta.setDisplayName(name);
		this.setItemMeta(meta);


	}

	public void setNBTTag(String key, NBTBase value ) {
		final ItemMeta m = this.getItemMeta();
		String stored = "";
		if(m.getDisplayName() != null) {
			stored = m.getDisplayName();
		}
		this.setItemMeta(m);
		final net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
		final NBTTagCompound comp = nmsItem.getTag();
		comp.set(key, value);
		nmsItem.setTag(comp);
		final ItemMeta meta = CraftItemStack.getItemMeta(nmsItem);
		meta.setDisplayName(stored);
		this.setItemMeta(meta);
	}

	public void setNBTTag(String key, String value) {
		setNBTTag(key, new NBTTagString(value));
	}

	public NBTBase getNBTTag(String key) {
		try {
			final net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			final NBTTagCompound comp = nmsItem.getTag();
			return comp.get(key);
		} catch (final Exception e) {
			return null;
		}
	}

	public String getNBTTagString(String key) {
		try {
			final net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			final NBTTagCompound comp = nmsItem.getTag();
			final String value = comp.getString(key);
			return value.isEmpty() ? null : value;
		} catch (final Exception e) {
			return null;
		}
	}

	public int getNBTTagInt(String key, int defaultValue) {
		try {
			final net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			final NBTTagCompound comp = nmsItem.getTag();
			return comp.getInt(key);
		} catch (final Exception e) {
			return defaultValue;
		}
	}

	public void removeNBTTag(String key) {
		final net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
		final NBTTagCompound comp = nmsItem.getTag();
		comp.remove(key);
		nmsItem.setTag(comp);
		final ItemMeta meta = CraftItemStack.getItemMeta(nmsItem)	;
		this.setItemMeta(meta);
	}
}
