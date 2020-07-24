package threadspractice;

import java.util.logging.Level;
import java.util.logging.Logger;

class Worker implements Runnable {
    
    private final OrderQueue orders;
    private final int workerId;
    
    public Worker(OrderQueue orders, int workerId) {
        this.orders = orders;
        this.workerId = workerId;
    }

    @Override
    public void run() {
        
        while (!orders.weAreDone()) {
            
            try {
                
                Task task = orders.acceptTask();
                
                System.out.println("Worker " + workerId + " accepted " + task);
                
                try {
                    Thread.sleep(task.getTimeToComplete());
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId +
                            " failed to complete " + task);
                }
                
                //System.out.println("Worker " + workerId + " finished " + task);
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //System.out.println("Worker " + workerId + " finished " + task);
        }
    }
}
