package Native;

/**
 * Created by Valera on 29.05.2014.
 */
public class Entry {
    public static void main(String[] args) {
        CallCenter center = new CallCenter(20);
        try {
            for (int i = 0; i < 500; i++) {
                Thread.currentThread().sleep(1);
                new Calling(center, 3,5,4).start();
            }
        } catch (InterruptedException ex) {
            System.out.println("Error in NativeLauncher thread " + Thread.currentThread());
        }
    }

}
