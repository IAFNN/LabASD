package com.example.lab1asd;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public abstract class Algorithm extends Thread{
    protected Integer[] array;
    protected Label labelToDisplay;
    protected Controller controller;

    public Algorithm(Integer[] array, Label label, int delay){
        this.array = array;
        this.labelToDisplay = label;
        this.delay = delay;
        (controller = new Controller(this)).start();
    }
    protected int delay;
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public Controller getController() {
        return controller;
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
class BubbleSort extends Algorithm{
    public BubbleSort(Integer[] array, Label label, int delay) {
        super(array, label, delay);
    }
    @Override
    public void run() {
        for(int i = 0; i < array.length; i++){
            for(int i2 = 0; i2 < array.length - i - 1; i2++){
                int tempI2 = i2;
                Platform.runLater(() -> {
                    String temp2 = toString();
                    temp2 = temp2.concat("\nCurrent elements in work are " + array[tempI2] + " " + array[tempI2 + 1]);
                    labelToDisplay.setText(temp2);
                });
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException ignored) {}
                }
                if(array[i2] > array[i2 + 1]){
                    int temp2 = array[i2];
                    array[i2] = array[i2 + 1];
                    array[i2 + 1] = temp2;
                }
            }
        }
    }
}
class DeletingAlgorithm extends Algorithm{
    public DeletingAlgorithm(Integer[] array, Label label, int delay) {
        super(array, label, delay);
    }
    @Override
    public void run() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer integer : array) {
            Platform.runLater(() -> {
                labelToDisplay.setText(this + "\nCurrent frequency map is\n" + map);
            });
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException ignored) {}
            }
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        int maxValue = Integer.MIN_VALUE;
        int maxElement = 0;
        for(Map.Entry<Integer, Integer> element : map.entrySet()){
            if(element.getValue() > maxValue){
                maxValue = element.getValue();
                maxElement = element.getKey();
            }
        }
        int finalMaxElement = maxElement;
        Platform.runLater(() -> {
            labelToDisplay.setText(this + "\nCurrent frequency map is\n" + map + "\nThe most frequent element is " + finalMaxElement + "\nDeleting...");
        });
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {}
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        arrayList.removeAll(List.of(maxElement));
        array = arrayList.toArray(new Integer[0]);
        Platform.runLater(() -> {
            labelToDisplay.setText(toString());
        });
    }
}
