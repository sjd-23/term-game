package logic;

import core.Display;
import entity.Entity;
import util.Ansi;
import util.Consts;

public class Combat {
    private Log log = Log.getInstance();
    private CombatLog combatLog = CombatLog.getInstance();
    private Display display;
    Player player;
    Entity enemy;
    private boolean isActive = false;
    private int turnNumber = 0;
    Action enemyAction;
    Action playerAction;

    public Combat() {}

    public void initializeDisplay() {
        this.display = Display.getInstance();
    }

    public boolean isActive() { return this.isActive; }
    public Entity getEnemy() { return this.enemy; }
    public Action getEnemyAction() { return this.enemyAction; }

    public void prepareTurn() {
        this.enemyAction = Intelligence.prepareAction(this.enemy);
    }

    public void takeTurn() {
        if (this.enemyAction == null) return;
        if (this.playerAction == null) return;

        this.turnNumber += 1;

        this.combatLog.add(Ansi.ITALIC + Ansi.BOLD + "TURN #" + this.turnNumber + Ansi.RESET);
        display.print();
        Consts.attackDelay();

        if (this.player.getSpeed() >= this.enemy.getSpeed()) {
            this.combatLog.logPlayerAttack(this.player, this.playerAction);
            this.enemy.takeDamage(this.playerAction.getDamage());

            display.print();
            Consts.attackDelay();

            this.combatLog.logEnemyAttack(this.enemy, this.enemyAction);
            this.player.takeDamage(this.enemyAction.getDamage());

        } else {
            this.combatLog.logEnemyAttack(this.enemy, this.enemyAction);
            this.player.takeDamage(this.enemyAction.getDamage());

            display.print();
            Consts.attackDelay();

            this.combatLog.logPlayerAttack(this.player, this.playerAction);
            this.enemy.takeDamage(this.playerAction.getDamage());

        }
        display.print();
        Consts.attackDelay();

        this.enemyAction = null;
        this.playerAction = null;
    }

    public void createEncounter(Player player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
        this.log.add(Ansi.ITALIC + "A " + this.enemy.getTextColor()  + this.enemy.getName() + Ansi.RESET + Ansi.ITALIC + " has appeared.");
        this.isActive = true;
    }

    public void endEncounter() {
        this.log.add(Ansi.ITALIC + this.enemy.getTextColor() + this.enemy.getName() + Ansi.RESET + Ansi.ITALIC +
                     " has been " + Ansi.BG_RED + "KILLED." + Ansi.RESET);
        this.player = null;
        this.enemy = null;
        this.isActive = false;
        this.turnNumber = 0;
    }

    public boolean isEnemyDead() {
        return this.enemy.getHealth() <= 0;
    }

    public void submitPlayerAction(Action action) {
        if (!this.isActive) return;
        this.playerAction = action;
    }
}
