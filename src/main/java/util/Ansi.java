package util;

public final class Ansi {
    public static final String CLEAR = "\033[H\033[2J";
    public static final String RESET = "\033[0m";

    public static final String BOLD = "\033[1m";
    public static final String ITALIC = "\033[3m";
    public static final String UNDERLINE = "\033[4m";

    public static final String BLACK = "\033[30m";
    public static final String GRAY = "\033[37m";
    public static final String RED = "\033[31m";
    public static final String DARK_RED_256 = "\033[38;5;52m";
    public static final String DARKER_RED_256 = "\033[38;5;88m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";

    public static final String BG_BLACK = "\033[40m";
    public static final String BG_GRAY_256 = "\033[48;5;240m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_DARK_RED_256 = "\033[48;5;52m";
    public static final String BG_DARKER_RED_256 = "\033[48;5;88m";
    public static final String BG_BRIGHT_RED = "\033[101m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_PURPLE = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";

    public static void clearScreen() {
        System.out.print(Ansi.CLEAR);
        System.out.flush();
    }

    public static String createMainMenuOptionString(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(Ansi.BOLD + Ansi.YELLOW).append(input.charAt(0));
        sb.append(Ansi.RESET);
        for (int i = 1; i < input.length(); i++) {
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }
}
