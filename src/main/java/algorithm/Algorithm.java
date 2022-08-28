package algorithm;

import controller.Controller;

import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

public abstract class Algorithm extends Thread{
    protected Integer[] array;
    protected Controller controller;
    protected Supplier<String> toString;

    public Algorithm(Integer[] array, int delay){
        this.array = array;
        this.delay = delay;
        (controller = new Controller(this)).start();
    }
    protected int delay;
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public int getDelay() {
        return delay;
    }
    protected void waitForDelay(){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {}
        }
    }
    public Controller getController() {
        return controller;
    }

    public Supplier<String> getToString() {
        return toString;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < array.length; i++){
            if(i % 10 == 0){
                result = result.concat("\n");
            }
            result = result.concat(array[i] + "|");
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}

