package Native;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Valera on 29.05.2014.
 */
public class CallCenter {

   private int countOperators;

    private List<Operator> freeOperators;
    private List<Operator> busyOperators;

    public CallCenter(int countOperators) {
        this.countOperators = countOperators;
        freeOperators = new LinkedList<Operator>();
        busyOperators = new LinkedList<Operator>();

        for (int i = 0; i < countOperators; i++) {
            freeOperators.add(new Operator(i));
        }
    }

    public synchronized Operator getOperator(int maxWaitTime) throws InterruptedException {
        Operator freeOperator = null;


        long start = System.currentTimeMillis();
        while (freeOperators.size() == 0 && ((System.currentTimeMillis() - start) <= maxWaitTime * 1000)) {
            wait();
        }

        if (freeOperators.size() != 0) {
            freeOperator = ((LinkedList<Operator>)freeOperators).pollFirst();
            ((LinkedList<Operator>)busyOperators).offerLast(freeOperator);
        }

        return freeOperator;
    }

    public synchronized boolean freeOperator(Operator Operator) {
        boolean status = busyOperators.remove(Operator);
        if (status) {
            freeOperators.add(Operator);
        }
        notifyAll();
        return status;
    }
}
