package item;

public abstract class Sword extends Item {
    public Sword(String name, int attackValue) {
        super(name);
        super.attackValue = attackValue;
        super.isWeapon = true;
    }
}
