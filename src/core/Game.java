package core;

import logic.Log;
import util.Ansi;

public class Game {
    private boolean isRunning = true;

    private Manager manager = new Manager();
    private Display display = Display.getInstance(manager);
    private Input input = new Input(this.manager);

    private Log log = Log.getInstance();

    public Game() {
        this.manager.getCombat().initializeDisplay();
        this.log.add(Ansi.PURPLE + Ansi.ITALIC +  "You enter the horror of the shade, into the unknown.");
        this.manager.generateFloor();
    }

    public void run() {
        while (this.isRunning) {
            this.manager.prepareTurn();
            this.display.print();
            if (this.input.handle() == 0) this.isRunning = false;
            if (this.manager.isActionTaken()) this.manager.takeTurn();
        }
    }
}
