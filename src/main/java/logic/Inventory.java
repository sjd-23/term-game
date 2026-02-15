package logic;

import item.Item;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
    private List<Item> inventory = new ArrayList<>();
    private int maxSize = 8;

    public Inventory() {}

    public int getSize() { return this.inventory.size(); }
    public int getMaxSize() { return this.maxSize; }
    public List<Item> getList() { return this.inventory; }

    public boolean addItem(Item item) {
        if (this.inventory.size() + 1 >= this.maxSize) return false;
        this.inventory.add(item);
        return true;
    }

    public Item grabWeapon() {
        for (Item i : this.inventory) {
            if (i.isWeapon()) return i;
        }

        return null;
    }

    public Item grabBestWeapon() {
        Item weapon = this.grabWeapon();
        if (weapon == null) return null;

        for (Item i : this.inventory) {
            if (i.getAttackValue() > weapon.getAttackValue()) {
                weapon = i;
            }
        }

        return weapon;
    }
}
