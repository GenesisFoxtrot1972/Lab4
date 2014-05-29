package Concurrent;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Valera on 29.05.2014.
 */
public class CallCenter {

   private int countOperators;

    private ArrayBlockingQueue<Operator> freeOperators;
    private ArrayBlockingQueue<Operator> busyOperators;

    public CallCenter(int countOperators) {
        freeOperators = new ArrayBlockingQueue<Operator>(countOperators);
        busyOperators = new ArrayBlockingQueue<Operator>(countOperators);

        for (int i = 0; i < countOperators; i++) {
            freeOperators.offer(new Operator(i));
        }
    }

    public ArrayBlockingQueue<Operator> getFreeOperators() {
        return freeOperators;
    }

    public ArrayBlockingQueue<Operator> getBusyOperators() {
        return busyOperators;
    }
}
