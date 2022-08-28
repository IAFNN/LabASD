package algorithm;
import controller.Controller;
import view.View;
import javafx.scene.control.Label;

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
        Algorithm algorithm = new BubbleSort(array, delay);
        Controller controller = algorithm.getController();
        new View(labelToDisplay, timeCountDisplay, algorithm).start();
        algorithm.start();
        return controller;
    }
    public Controller startAdditionalAlgorithm(){
        Algorithm algorithm = new DeletingAlgorithm(array, delay);
        Controller controller = algorithm.getController();
        new View(labelToDisplay, timeCountDisplay, algorithm).start();
        algorithm.start();
        return controller;
    }
    public Controller startSelectionSort(){
        Algorithm algorithm = new SelectionSort(array, delay);
        Controller controller = algorithm.getController();
        new View(labelToDisplay, timeCountDisplay, algorithm).start();
        algorithm.start();
        return controller;
    }
}

