package core;

import core.display.GameDisplay;
import core.display.MainMenuDisplay;
import core.input.GameInput;
import core.input.MainMenuInput;
import core.management.GameManager;
import core.management.MainMenuManager;
import logic.CombatLog;
import logic.Log;
import util.Ansi;

public class Game {
    private boolean isRunning = true;
    GameState gameState = new GameState();

    // Managers
    private GameManager gameManager;
    private MainMenuManager mainMenuManager;

    // Displays
    private GameDisplay gameDisplay;
    private MainMenuDisplay mainMenuDisplay;

    // Inputs
    private GameInput gameInput;
    private MainMenuInput mainMenuInput;

    // Logs
    private Log log = Log.getInstance();
    private CombatLog combatLog = CombatLog.getInstance();

    public Game() {
        this.createMainMenuInstance();
    }

    public void run() {
        while (this.isRunning) {
            switch (this.gameState.currentState) {
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
        this.mainMenuDisplay = new MainMenuDisplay();
        this.mainMenuInput = new MainMenuInput(this.mainMenuManager);
    }

    private void createGameInstance() {
        // creation
        this.gameManager = new GameManager();
        this.gameDisplay = new GameDisplay(this.gameManager);
        this.gameInput = new GameInput(this.gameManager);

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
        int inputResult = this.mainMenuInput.handle();
        if (inputResult == 0) this.isRunning = false;
        else if (inputResult == 1) {
            this.createGameInstance();
            this.gameState.currentState = GameState.GAME_STATES.GAME;
        }
    }

    private void gameLoop() {
        this.gameManager.prepareTurn();
        this.gameDisplay.print();
        if (this.gameInput.handle() == 0) {
            this.createMainMenuInstance();
            this.gameState.currentState = GameState.GAME_STATES.MAIN_MENU;
        }
        if (this.gameManager.isActionTaken()) this.gameManager.takeTurn();
    }
}
