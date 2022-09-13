package model;
import algorithm.*;
import controller.Controller;
import view.View;
import javafx.scene.control.Label;

public class InputData<T extends Comparable<T>> {
    private int size;
    private T[] array;
    private Label labelToDisplay;
    private Label timeCountDisplay;
    private int delay = 0;

    public InputData(){}

    public void setSize(int size) {
        this.size = size;
    }

    public T[] getArray() {
        return array;
    }

    public int getDelay() {
        return delay;
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

    public void setArray(T[] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }
    public Controller startSortingAlgorithm(){
        try {
            Algorithm<T> algorithm = new BubbleSort<>(this);
            Controller controller = algorithm.getController();
            new View(labelToDisplay, timeCountDisplay, algorithm).start();
            algorithm.start();
            return controller;
        }catch (ClassCastException e){
            System.out.println("Incorrect data type");
            return null;
        }
    }
    public Controller startAdditionalAlgorithm(){
        try {
            Algorithm<T> algorithm = new DeletingAlgorithm<>(this);
            Controller controller = algorithm.getController();
            new View(labelToDisplay, timeCountDisplay, algorithm).start();
            algorithm.start();
            return controller;
        }catch (ClassCastException e){
            System.out.println("incorrect data type");
            return null;
        }
    }
    public Controller startSelectionSort(){
        try {
            Algorithm<T> algorithm = new SelectionSort<>(this);
            Controller controller = algorithm.getController();
            new View(labelToDisplay, timeCountDisplay, algorithm).start();
            algorithm.start();
            return controller;
        }catch (ClassCastException e){
            System.out.println("incorrect data type");
            return null;
        }
    }
    public Controller startSinusAlgorithm(){
        try {
            Algorithm<Double> algorithm = new SinusAlgorithm((InputData<Double>) this);
            Controller controller = algorithm.getController();
            new View(labelToDisplay, timeCountDisplay, algorithm).start();
            algorithm.start();
            return controller;
        }catch (ClassCastException e){
            System.out.println("Incorrect type of data");
            return null;
        }
    }
    public Controller startShellAlgorithm(){
        try {
            Algorithm<Vector> algorithm = new ShellAlgorithm((InputData<Vector>) this);
            Controller controller = algorithm.getController();
            new View(labelToDisplay, timeCountDisplay, algorithm).start();
            algorithm.start();
            return controller;
        }catch (ClassCastException e){
            System.out.println("Incorrect type of data");
            return null;
        }
    }
    public Controller startQuickSort(){
        try{
            Algorithm<Student> algorithm = new QuickSort((InputData<Student>) this);
            Controller controller = algorithm.getController();
            new View(labelToDisplay, timeCountDisplay, algorithm).start();
            algorithm.start();
            return controller;
        }catch (ClassCastException e){
            System.out.println("Incorrect type of data");
            return null;
        }
    }
}

