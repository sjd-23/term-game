package core.input;

import core.management.MainMenuManager;
import util.Ansi;

import java.util.Scanner;

public class MainMenuInput {
    MainMenuManager mainMenuManager;
    Scanner scanner = new Scanner(System.in);

    public MainMenuInput(MainMenuManager mainMenuManager) {
        this.mainMenuManager = mainMenuManager;
    }

    public int handle() {
        System.out.print(Ansi.YELLOW + ">>" + Ansi.RESET + " ");
        return this.mainMenuManager.takeAction(this.scanner.nextLine());
    }
}
