package world.item;

public abstract class Item {
    String name;
    int attackValue = 0;
    boolean isWeapon;

    Item(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
    public int getAttackValue() { return this.attackValue; }
    public boolean isWeapon() { return this.isWeapon; }
}
