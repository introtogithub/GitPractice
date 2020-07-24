package threadspractice;

import java.util.ArrayDeque;
import java.util.Queue;

class OrderQueue {
    
    private Queue<Task> orders;
    private boolean moreOrdersComing;
    
    public OrderQueue() {
        orders = new ArrayDeque<>();
        moreOrdersComing = true;
    }
    
    public synchronized void  createTask(String label, int timeToComplete) throws  InterruptedException{
        
        while (orders.size()>=5){
            wait();
        }
        orders.offer(new Task(label, timeToComplete));
        notify();
    }
    
    public synchronized Task acceptTask() throws InterruptedException{
        while (orders.isEmpty()) {
            wait();
        }
        Task task = orders.poll();
        notify();
        return task;
    }
    
    public void setNoMoreOrders() {
        moreOrdersComing = false;
    }
    
    public synchronized boolean weAreDone() {
        
        return orders.isEmpty() && !moreOrdersComing;
    }
    
   
}