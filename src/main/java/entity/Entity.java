package entity;

import logic.Inventory;

public abstract class Entity {
    private String name;
    private int health;
    private int maxHealth;
    private int speed;

    private Inventory inventory = new Inventory();
    protected String highlightColor;
    protected String textColor;
    protected String flavorDescription;

    public Entity(String name, int health, int maxHealth, int speed) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
    }

    public String getName() { return this.name; }
    public int getHealth() { return this.health; }
    public void setHealth(int health) { this.health = health; }
    public int getMaxHealth() { return this.maxHealth; }
    public int getSpeed() { return this.speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public Inventory getInventory() { return this.inventory; }
    public abstract String getHighlightColor();
    public abstract String getTextColor();
    public abstract String getFlavorDescription();

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}
