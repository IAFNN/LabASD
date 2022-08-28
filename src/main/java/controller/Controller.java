package controller;

import algorithm.Algorithm;

public class Controller extends Thread{
    private boolean toPause = false;
    private boolean toEnd = false;
    private final Algorithm algorithm;
    public void setToPause(boolean toPause) {
        this.toPause = toPause;
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
        }
    }
}
