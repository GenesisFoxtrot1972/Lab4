package Native;

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


            Operator operator = null;
            /*  Call  */
            while (operator == null) {
                operator = center.getOperator(this.waitTime);
                if (operator != null) {
                    System.out.println("Operator with number " + operator.getOperatorId() + " become busy.");
                }
            }

            /* Dialogue */
            Thread.currentThread().sleep(this.stayTime * 1000);

            /* End of call */
            boolean status = center.freeOperator(operator);
            if (status) {
                System.out.println("Operator with number " + operator.getOperatorId() + " become free.");
            } else {
                System.out.println("Operator with number " + operator.getOperatorId() + " don't exist ");
            }

        } catch (InterruptedException e) {
            System.out.println("Error in Car thread " + Thread.currentThread());
        }

    }
}