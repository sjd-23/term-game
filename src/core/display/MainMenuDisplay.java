package core.display;

import util.Ansi;
import util.Consts;

public class MainMenuDisplay {
    private static MainMenuDisplay mainMenuDisplay = null;

    public MainMenuDisplay() {}

    public static synchronized MainMenuDisplay getInstance() {
        if (mainMenuDisplay == null) mainMenuDisplay = new MainMenuDisplay();
        return mainMenuDisplay;
    }

    public void print() {
        Ansi.clearScreen();
        this.printTitleHeader();
        this.printOptions();
    }

    private void printTitleHeader() {
        System.out.println(Ansi.BLACK + Ansi.BG_WHITE +
                           "Untitled Terminal Game" +
                           Ansi.RESET + " " +
                           Ansi.BLACK + Ansi.BG_WHITE +
                           "v" + Consts.gameVersion +
                           Ansi.RESET);
        System.out.println(Ansi.PURPLE + Ansi.ITALIC + Consts.versionSplash);
        System.out.println(Ansi.RESET);
    }

    private void printOptions() {
        System.out.println(Ansi.GRAY + "p) " + Ansi.createMainMenuOptionString("Play") + Ansi.RESET);
        System.out.println(Ansi.GRAY + "q) " + Ansi.createMainMenuOptionString("Quit"));
        System.out.println(Ansi.RESET);
    }
}
