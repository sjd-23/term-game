package core.input;

import core.management.GameManager;
import util.Ansi;

import java.util.Scanner;

public class GameInput {
    GameManager gameManager;
    Scanner scanner = new Scanner(System.in);

    public GameInput(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public int handle() {
        System.out.print(Ansi.YELLOW + ">>" + Ansi.RESET + " ");
        return this.gameManager.takeAction(this.scanner.nextLine());
    }
}
