package threadspractice;

import java.util.logging.Level;
import java.util.logging.Logger;

class TaskMaster implements Runnable {

    private final OrderQueue orders;
    private int ordersCreated;
    
    public TaskMaster(OrderQueue orders) {
        this.orders = orders;
        this.ordersCreated = 0;
    }
    
    @Override
    public void run() {
        while (ordersCreated++ < 100) {
            String orderLabel = "Task " + ordersCreated;
            int orderTime =  (int) (Math.random() * 1000 + 250);
            try {
                orders.createTask(orderLabel, orderTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskMaster.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        orders.setNoMoreOrders();
    }
}
