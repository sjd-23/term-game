package core;

import core.display.GameDisplay;
import core.display.MainMenuDisplay;
import core.management.GameManager;
import core.management.MainMenuManager;
import logic.CombatLog;
import logic.Log;
import util.Ansi;

public class Game {
    private boolean isRunning = true; // Master Switch

    // Game State
    private enum STATES {
        MAIN_MENU,
        GAME,
    }
    private STATES currentState = STATES.MAIN_MENU;

    // Managers
    private GameManager gameManager;
    private MainMenuManager mainMenuManager;

    // Displays
    private GameDisplay gameDisplay;
    private MainMenuDisplay mainMenuDisplay;

    private Input input = new Input();

    // Logs
    private Log log = Log.getInstance();
    private CombatLog combatLog = CombatLog.getInstance();

    public Game() {
        this.createMainMenuInstance();
    }

    public void run() {
        while (this.isRunning) {
            switch (this.currentState) {
                case GAME:
                    this.gameLoop();
                    break;
                case MAIN_MENU:
                    this.mainMenuLoop();
                    break;
            }
        }
    }

    private void createMainMenuInstance() {
        // creation
        this.mainMenuManager = new MainMenuManager();
        this.mainMenuDisplay = MainMenuDisplay.getInstance();
        this.input.setManager(this.mainMenuManager);
    }

    private void createGameInstance() {
        // creation
        this.gameManager = new GameManager();
        this.gameDisplay = new GameDisplay(this.gameManager);
        this.input.setManager(this.gameManager);

        // reset logs
        this.log.getList().clear();
        this.combatLog.getList().clear();

        // setup, mostly placeholder
        this.gameManager.setCombatGameDisplay(this.gameDisplay);
        this.log.add(Ansi.PURPLE + Ansi.ITALIC +  "You enter the horror of the shade, into the unknown.");
        this.gameManager.generateFloor();
    }

    private void mainMenuLoop() {
        this.mainMenuDisplay.print();
        int inputResult = this.input.listen();
        if (inputResult == 0) this.isRunning = false;
        else if (inputResult == 1) {
            this.createGameInstance();
            this.currentState = STATES.GAME;
        }
    }

    private void gameLoop() {
        this.gameManager.prepareTurn();
        this.gameDisplay.print();
        if (this.input.listen() == 0) {
            this.createMainMenuInstance();
            this.currentState = STATES.MAIN_MENU;
        }
        if (this.gameManager.isActionTaken()) this.gameManager.takeTurn();
    }
}
