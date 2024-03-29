package fr.topeka.warpgui.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryGui {

	private Inventory inv = null;
	private List<InvElement> elements = new ArrayList<>();
	private int invSize = 9; 
	private String _title;
	
	public InventoryGui(String title) {
		_title = title;
	}
	
	public void createInventory() {
		int max = 0;
		for(InvElement e : elements) {
			if(e.getPosition() > max)
				max = e.getPosition();
		}
		for(int i = 9;i<max;i*=9) {
			invSize = i;
		}
		if(invSize > 54)
			invSize = 54;
		inv = Bukkit.createInventory(null, invSize, _title);
		for(InvElement e : elements) {
			inv.setItem(e.getPosition(), e.getItem());
		}
	}
	
	public void displayInventory(Player player) {
		player.closeInventory();
		if(inv == null)
			createInventory();
		player.openInventory(inv);
		
	}
	
	public List<InvElement> getElements() {
		return elements;
	}

	public InvElement getClickedItem(int slot) {
		for(InvElement e : elements) {
			if(slot == e.getPosition()) {
				return e;
			}
		}
		return null;	
	}
	
}
