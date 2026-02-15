package core.management;

import core.display.GameDisplay;
import entity.Entity;
import entity.entities.Goblin;
import item.Item;
import item.items.IronSword;
import logic.Action;
import logic.Player;
import logic.Combat;

public class GameManager {
    private int floorNumber;
    private boolean isFloorConcluded = false;
    private boolean isAttackMenuOpened = false;
    private boolean isActionTaken = false;

    Entity currentEnemy;
    Player player = new Player();
    Combat combat = new Combat();

    public GameManager() {
        this.floorNumber = 0;
        this.player.getInventory().addItem(new IronSword());
    }

    public void setCombatGameDisplay(GameDisplay gameDisplay) {
        this.combat.setGameDisplay(gameDisplay);
    }

    public int getFloorNumber() { return this.floorNumber; }
    public boolean isCombatActive() { return this.combat.isActive(); }
    public Combat getCombat() { return this.combat; }
    public boolean isFloorConcluded() { return this.isFloorConcluded; }
    public boolean isAttackMenuOpened() { return this.isAttackMenuOpened; }
    public Player getPlayer() { return this.player; }
    public boolean isActionTaken() { return this.isActionTaken; }

    public void prepareTurn() {
        if (this.combat.isActive() && !this.combat.isEnemyDead()) {
            this.combat.prepareTurn();
        }
    }

    public void takeTurn() {
        if (this.combat.isActive() && !this.combat.isEnemyDead() && this.isActionTaken) {
            this.combat.takeTurn();
        }

        if (this.combat.isActive() && this.combat.isEnemyDead()) {
            this.combat.endEncounter();
            this.isFloorConcluded = true;
        }

        this.isActionTaken = false;
    }

    public int takeAction(String command) {
        if (this.isAttackMenuOpened && command.length() >= 2) {
            try {
                int weaponSelection = Character.getNumericValue(command.charAt(1));
                if (weaponSelection >= 1 && weaponSelection <= this.player.getInventory().getSize()) {
                    Item i = this.player.getInventory().getList().get(weaponSelection - 1);
                    this.combat.submitPlayerAction(new Action(i, "attack", i.getAttackValue()));
                    this.isAttackMenuOpened = false;
                    this.isActionTaken = true;
                    return 1;
                } else {
                    return 1;
                }
            } catch (NumberFormatException e) {}
        }

        if (this.combat.isActive()) {
            switch (command) {
                case "q":
                case "quit":
                case "exit":
                    return 0;
                case "a":
                    this.isAttackMenuOpened = !this.isAttackMenuOpened;
                    return 1;
                case "b":
                    if (!this.combat.isActive()) return 1;
                    this.regenerateFloor();
                    return 1;
                case null, default:
                    return 1;
            }
        } else {
            switch (command) {
                case "q":
                case "quit":
                case "exit":
                    return 0;
                case "c":
                    this.generateFloor();
                    this.isFloorConcluded = false;
                case null, default:
                    return 1;
            }
        }
    }

    public void generateFloor() {
        this.floorNumber += 1;
        this.currentEnemy = new Goblin();
        this.currentEnemy.getInventory().addItem(new IronSword());
        this.combat.createEncounter(this.player, this.currentEnemy);
    }

    public void regenerateFloor() {
        if (this.combat.isActive()) this.combat.endEncounter();
        this.currentEnemy = new Goblin();
        this.combat.createEncounter(this.player, this.currentEnemy);
    }
}
