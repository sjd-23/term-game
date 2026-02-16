package core;

import util.Ansi;

import java.util.Scanner;

public class Input {
    private Manager manager;
    private Scanner sc = new Scanner(System.in);

    public Input() {}

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int listen() {
        if (!this.manager.isInputLocked) {
            System.out.print(Ansi.YELLOW + ">>" + Ansi.RESET + " ");
            return this.manager.takeAction(this.sc.nextLine());
        }

        return -1;
    }
}
