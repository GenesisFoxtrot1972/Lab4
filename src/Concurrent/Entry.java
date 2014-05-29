package Concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Valera on 29.05.2014.
 */
public class Entry {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CallCenter parking = new CallCenter(50);
        try {

            for (int i = 0; i < 1000; i++) {
                Thread.currentThread().sleep(1);
                executor.execute(new Calling(parking, 1,2,3));
            }
        } catch (InterruptedException ex) {
            System.out.println("Error in ConcurrentLauncher thread " + Thread.currentThread());
        } finally {
            executor.shutdown();
        }
    }

}
