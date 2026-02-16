package util;

public final class Consts {
    public static String gameVersion = "infdev";
    public static String versionSplash = "The horrors of the shade...";

    public static void attackDelay() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
