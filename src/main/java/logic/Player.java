package logic;

public class Player {
    private int attack = 5;
    private int health = 50;
    private int maxHealth = 50;
    private int speed = 0;

    private Inventory inventory = new Inventory();

    public Player() {}

    public int getAttack() { return this.attack; }
    public int getHealth() { return this.health; }
    public int getMaxHealth() { return this.maxHealth; }
    public int getSpeed() { return this.speed; }
    public Inventory getInventory() { return this.inventory; }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}
