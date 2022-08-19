package com.example.lab1asd;

import java.util.concurrent.CountDownLatch;

public class Controller extends Thread{
    private boolean toPause = false;
    private boolean toResume = false;
    private boolean toEnd = false;
    private Algorithm algorithm;
    public void setToPause(boolean toPause) {
        this.toPause = toPause;
    }
    public void setToResume(boolean toResume) {
        this.toResume = toResume;
    }

    public void end() {
        toEnd = true;
    }

    public Controller(Algorithm algorithm){
        this.algorithm = algorithm;
    }
    @Override
    public void run() {
        while (!toEnd){
            if(toPause){
                algorithm.interrupt();
                toPause = false;
            }
            if(toResume){
                algorithm.getCountDownLatch().countDown();
                algorithm.setCountDownLatch(new CountDownLatch(1));
                toResume = false;
            }
        }
    }
}
