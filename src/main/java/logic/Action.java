package logic;

import item.Item;

public class Action {
    private String type;
    private int damage;
    private Item item;

    public Action(Item item, String type, int damage) {
        this.item = item;
        this.type = type;
        this.damage = damage;
    }

    public int getDamage() { return this.damage; }
    public String getType() { return this.type; }
    public Item getItem() { return this.item; }
}
