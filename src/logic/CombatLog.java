package logic;

import entity.Entity;
import util.Ansi;

import java.util.ArrayList;
import java.util.List;

public class CombatLog {
    private static CombatLog log = null;
    private List<String> events = new ArrayList<>();
    private final int maxSize = 4;

    public CombatLog() {}

    public static synchronized CombatLog getInstance() {
        if (log == null) log = new CombatLog();
        return log;
    }

    public void add(String message) {
        this.events.add(message);
        if (this.events.size() > this.maxSize) this.events.removeFirst();
    }

    public List<String> getList() { return this.events; }
    public int getSize() { return this.events.size(); }
    public int getMaxSize() { return this.maxSize; }

    public void logEnemyAttack(Entity enemy, Action enemyAction) {
        this.add(Ansi.ITALIC + enemy.getTextColor() + enemy.getName() +
                 Ansi.RESET + Ansi.ITALIC +
                 " has dealt " +
                 Ansi.RED + enemyAction.getDamage() +
                 Ansi.RESET + Ansi.ITALIC +
                 " damage with " + enemyAction.getItem().getName() + ".");
    }

    public void logPlayerAttack(Player player, Action playerAction) {
        this.add(Ansi.ITALIC + Ansi.YELLOW + "You" +
                 Ansi.RESET + Ansi.ITALIC +
                 " have dealt " +
                 Ansi.RED + playerAction.getDamage() +
                 Ansi.RESET + Ansi.ITALIC +
                 " damage with " + playerAction.getItem().getName() + ".");
    }
}
