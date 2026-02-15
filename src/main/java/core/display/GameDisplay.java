package core.display;

import core.management.GameManager;
import entity.Entity;
import logic.Action;
import logic.CombatLog;
import logic.Log;
import logic.Player;
import util.Ansi;

import java.util.Objects;

public class GameDisplay {
    private static GameDisplay gameDisplay = null;
    GameManager gameManager;
    Log log = Log.getInstance();
    CombatLog combatLog = CombatLog.getInstance();

    public GameDisplay(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void print() {
        Ansi.clearScreen();
        this.printHeaderPane();

        this.printLog();

        if (this.gameManager.isCombatActive()) {
            this.printCombatPane();
            this.printCombatLog();
            this.printPlayerSeperatorPane();
            this.printPlayerVitalsPane();
            this.printCombatMenuPane();
        } else if (this.gameManager.isFloorConcluded()) {
            this.printConclusionPane();
            this.printPlayerSeperatorPane();
            this.printPlayerVitalsPane();
            this.printConclusionMenuPane();
        }
    }

    private void printHeaderPane() {
        System.out.println(Ansi.BLACK + Ansi.BG_WHITE + "Floor No. " + this.gameManager.getFloorNumber() + Ansi.RESET + "\n");
    }

    private void printCombatPane() {
        Entity enemy = this.gameManager.getCombat().getEnemy();
        Action action = this.gameManager.getCombat().getEnemyAction();

        System.out.println(Ansi.GRAY + Ansi.ITALIC + enemy.getFlavorDescription() + Ansi.RESET);

        System.out.println(Ansi.RED + "═══| " + Ansi.BOLD + "COMBAT" + Ansi.RESET + Ansi.RED + " |═══" + Ansi.RESET + "\n");

        System.out.print(enemy.getHighlightColor() + enemy.getName() + Ansi.RESET + " ");
        System.out.println(Ansi.BG_RED + enemy.getHealth() + "/" + enemy.getMaxHealth() + Ansi.RESET + "\n");

        if (action == null) {
            System.out.println(Ansi.GRAY + Ansi.ITALIC + "It is indecipherable what the " + enemy.getTextColor() + enemy.getName() +
                               Ansi.GRAY + " is going to do.");
        } else if (Objects.equals(action.getType(), "attack")) {
            System.out.println(Ansi.ITALIC + enemy.getTextColor() + enemy.getName() + Ansi.GRAY +
                               " is readying an attack.");
        } else {
            System.out.println(Ansi.GRAY + Ansi.ITALIC + "It is indecipherable what " + enemy.getTextColor() + enemy.getName() +
                               Ansi.GRAY + " is going to do.");
        }

        System.out.println(Ansi.RESET);
    }

    private void printPlayerSeperatorPane() {
        System.out.println(Ansi.YELLOW + "═══| " + Ansi.BOLD + "YOU" + Ansi.RESET + Ansi.YELLOW + " |═══" + Ansi.RESET + "\n");
    }

    private void printCombatMenuPane() {
        if (this.gameManager.isAttackMenuOpened())  {
            System.out.println(Ansi.YELLOW + "a." + Ansi.RESET + " Attack with:");
            this.printAttackOptions();
        } else {
            System.out.println(Ansi.YELLOW + "a." + Ansi.RESET + " Attack with...");
        }
        System.out.println(Ansi.YELLOW + "b." + Ansi.RESET + " Run\n");
    }

    private void printPlayerVitalsPane() {
        Player player = this.gameManager.getPlayer();
        System.out.print(Ansi.BG_DARK_RED_256 +  "Health" + Ansi.RESET + " ");
        System.out.println(Ansi.BG_DARKER_RED_256 + player.getHealth() + "/" + player.getMaxHealth() + Ansi.RESET + "\n");
    }

    private void printAttackOptions() {
        if (this.gameManager.getPlayer().getInventory().getSize() == 0) {
            System.out.println(Ansi.CYAN + "     No weapons available." + Ansi.RESET);
            return;
        }

        int selectionChoice = 1;
        for (int i = 0; i < this.gameManager.getPlayer().getInventory().getSize(); i++) {
            System.out.println(Ansi.CYAN + "     a" + selectionChoice + ". " + Ansi.RESET + this.gameManager.getPlayer().getInventory().getList().get(i).getName());
            selectionChoice += 1;
        }
    }

    private void printConclusionPane() {
        System.out.println(Ansi.BLACK + Ansi.BG_GREEN + "Floor concluded!" + Ansi.RESET + "\n");
    }

    private void printConclusionMenuPane() {
        System.out.println(
                Ansi.YELLOW + "c." + Ansi.RESET + " Continue to next floor\n"
        );
    }

    private void printLog() {
        System.out.println("═══| " + Ansi.BOLD + "NARRATIVE" + Ansi.RESET + " |═══" + Ansi.RESET + "\n");

        for (String message : this.log.getList()) {
            System.out.println(Ansi.BLACK + Ansi.BG_GRAY_256 + ":" + Ansi.RESET + " " + message + Ansi.RESET);
        }

        for (int i = 0; i < this.log.getMaxSize() - this.log.getSize(); i++) {
            System.out.println(Ansi.BLACK + Ansi.BG_GRAY_256 + ":" + Ansi.RESET);
        }

        System.out.println();
    }

    private void printCombatLog() {
        for (String message : this.combatLog.getList()) {
            System.out.println(Ansi.BLACK + Ansi.BG_DARK_RED_256 + ":" + Ansi.RESET + " " + message + Ansi.RESET);
        }

        for (int i = 0; i < this.combatLog.getMaxSize() - this.combatLog.getSize(); i++) {
            System.out.println(Ansi.BLACK + Ansi.BG_DARK_RED_256 + ":" + Ansi.RESET);
        }
        System.out.println();
    }
}
