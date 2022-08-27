package com.example.lab1asd;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.CountDownLatch;

public class InputData {
    private int size;
    private Integer[] array;
    private Label labelToDisplay;
    private Label timeCountDisplay;
    private int delay = 0;

    public InputData(){}

    public void setSize(int size) {
        this.size = size;
        array = new Integer[size];
    }

    public void setTimeCountDisplay(Label timeCountDisplay) {
        this.timeCountDisplay = timeCountDisplay;
    }


    public void setLabelToDisplay(Label labelToDisplay) {
        this.labelToDisplay = labelToDisplay;
    }


    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setArray(Integer[] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }
    public Controller startSortingAlgorithm(){
        Algorithm algorithm = new BubbleSort(array, labelToDisplay, delay, timeCountDisplay);
        Controller controller = algorithm.getController();
        algorithm.start();
        return controller;
    }
    public Controller startAdditionalAlgorithm(){
        Algorithm algorithm = new DeletingAlgorithm(array, labelToDisplay, delay, timeCountDisplay);
        Controller controller = algorithm.getController();
        algorithm.start();
        return controller;
    }
}

