package core;

public class GameState {
    public enum GAME_STATES {
        MAIN_MENU,
        GAME,
    }

    public GAME_STATES currentState = GAME_STATES.MAIN_MENU;
    public GameState() {}
}
