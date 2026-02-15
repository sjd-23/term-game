package core.management;

public class MainMenuManager {
    public MainMenuManager() {}

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
