package view;

import algorithm.Algorithm;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class View extends Thread{
    private final Algorithm algorithm;
    private final Label labelToDisplayResult;
    private final Label labelToDisplayTime;
    private long timeStart;
    public View(Label labelToDisplay, Label labelToDisplayTime, Algorithm algorithm){
        this.labelToDisplayResult = labelToDisplay;
        this.algorithm = algorithm;
        this.labelToDisplayTime = labelToDisplayTime;
    }
    @Override
    public void run() {
        timeStart = System.currentTimeMillis();
        try {
            Thread.sleep(algorithm.getDelay() / 10);
            while (algorithm.isAlive()) {
                Platform.runLater(() -> labelToDisplayResult.setText(algorithm.getToString().get().toString()));
                Thread.sleep(algorithm.getDelay() / 2);
            }
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        Platform.runLater(() -> labelToDisplayTime.setText((System.currentTimeMillis() - timeStart) + "ms"));
    }
}
