package Concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by Valera on 29.05.2014.
 */
public class Calling extends  Thread {
    private CallCenter center;
    private int waitTime;
    private int callAgainTime;
    private int stayTime;

    public Calling(CallCenter c, int waitTime, int callAgainTime, int stayTime) {
        super();
        this.center = c;
        this.callAgainTime = callAgainTime;
        this.waitTime = waitTime;
        this.stayTime = stayTime;
    }

    @Override
    public void run() {
        try {
            /* Call */
            Operator freeOperator = null;
            while(freeOperator == null){
                freeOperator = center.getFreeOperators().poll(this.waitTime, TimeUnit.SECONDS);
                boolean status = center.getBusyOperators().offer(freeOperator);
                if (status) {
                    System.out.println("Operator with number " + freeOperator.getOperatorId() + " become busy.");
                }
            }



            /* Dialogue */
            Thread.currentThread().sleep(this.stayTime * 1000);

            /* End of call */
            Operator busyOperator = center.getBusyOperators().poll(this.waitTime, TimeUnit.SECONDS);
            if (busyOperator != null) {
                boolean status = center.getFreeOperators().offer(busyOperator, this.waitTime, TimeUnit.SECONDS);

                if (status) {
                    System.out.println("Operator with number " + busyOperator.getOperatorId() + " become free.");
                }

            }

        } catch (InterruptedException e) {
            System.out.println("Error in operator thread " + Thread.currentThread());
        }
    }
}