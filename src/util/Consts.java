package util;

public final class Consts {
    public static void attackDelay() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
