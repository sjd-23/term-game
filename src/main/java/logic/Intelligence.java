package logic;

import entity.Entity;
import item.Item;

public class Intelligence {
    public Intelligence() {}

    public static Action prepareAction(Entity entity) {
        int health = entity.getHealth();
        int maxHealth = entity.getMaxHealth();
        Inventory inventory = entity.getInventory();

        Item bestAttackingWeapon = inventory.grabBestWeapon();
        if (bestAttackingWeapon == null) return null;

        return new Action(bestAttackingWeapon, "attack", bestAttackingWeapon.getAttackValue());
    }
}
