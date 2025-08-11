package SoloProject;

public class WaitUtil {
    public static void waitForSecond (int sec)
    {
        //long mili = sec*1000;
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
