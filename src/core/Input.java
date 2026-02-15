package core;

import util.Ansi;

import java.util.Scanner;

public class Input {
    Manager manager;
    Scanner scanner = new Scanner(System.in);

    public Input(Manager manager) {
        this.manager = manager;
    }

    public int handle() {
        System.out.print(Ansi.YELLOW + ">>" + Ansi.RESET + " ");
        return this.manager.takeAction(this.scanner.nextLine());
    }
}
