package core.management;

import core.Manager;

public class MainMenuManager extends Manager {
    public MainMenuManager() {
        super();
    }

    public int takeAction(String command) {
        switch (command) {
            case "q":
                return 0;
            case "p":
                return 1;
        }

        return -1;
    }
}
