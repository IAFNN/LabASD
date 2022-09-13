package fx;

import model.Student;
import model.Vector;
import controller.Controller;
import model.InputData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;


public class MainApplication extends Application {
    private final InputData<Double> INPUT_DATA = new InputData<>();
    private final Pane PANE = new Pane();
    private Controller CONTROLLER;
    @Override
    public void start(Stage stage) {
        Scene scene = constructScene();
        stage.setTitle("Lab1");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(windowEvent -> {
            if(CONTROLLER != null){
                CONTROLLER.end();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public Scene constructScene(){
        TextField arraySizeTextField = new TextField("Enter number of elements in array");
        arraySizeTextField.setPrefSize(200, 20);
        arraySizeTextField.relocate(300, 20);
        Button button = new Button("Confirm");
        button.resize(50, 50);
        button.relocate(arraySizeTextField.getLayoutX() + 220, arraySizeTextField.getLayoutY());
        button.setOnAction(actionEvent -> {
            INPUT_DATA.setSize(Integer.parseInt(arraySizeTextField.getText()));
            arraySizeTextField.clear();
            whenSizeEntered();
        });
        PANE.getChildren().addAll(arraySizeTextField, button);
        return new Scene(PANE, 1000, 600);
    }
    public void whenSizeEntered(){
        Label arraySize = new Label();
        arraySize.relocate(30, 100);
        arraySize.setText("Array size: " + INPUT_DATA.getSize());
        TextField arrayElementsTextField = new TextField("Enter " + INPUT_DATA.getSize() + " integers split" +
                " with '|'");
        arrayElementsTextField.setPrefSize(300, 20);
        arrayElementsTextField.relocate(arraySize.getLayoutX() + arraySize.getWidth() + 100, arraySize.getLayoutY() - 4);
        Button button = new Button("Start sorting");
        button.relocate(arrayElementsTextField.getLayoutX() + 420, arrayElementsTextField.getLayoutY());
        Button delete = new Button("Start deleting");
        delete.relocate(arrayElementsTextField.getLayoutX() + 320, arrayElementsTextField.getLayoutY());
        Button selection = new Button("Start selection sort");
        selection.relocate(button.getLayoutX(), button.getLayoutY() + 35);
        Button sinus = new Button("sin(x)");
        sinus.relocate(delete.getLayoutX(), delete.getLayoutY() + 35);
        Button shell = new Button("Shell algorithm");
        shell.relocate(selection.getLayoutX() + 130, selection.getLayoutY());
        Button quick = new Button("Quick sort");
        quick.relocate(sinus.getLayoutX() - 100, sinus.getLayoutY());
        Button random = new Button("Random");
        random.relocate(arrayElementsTextField.getLayoutX() + 240, arrayElementsTextField.getLayoutY());
        random.setOnAction(actionEvent -> {
            String array = "";
            for(int i = 0; i < INPUT_DATA.getSize(); i++){
                array = array.concat(((int) (Math.random() * 200 - 100)) + "|");
            }
            array = array.substring(0, array.length() - 1);
            arrayElementsTextField.setText(array);
        });
        TextField delayTextField = new TextField("Enter delay between steps");
        delayTextField.resize(300, 20);
        delayTextField.relocate(button.getLayoutX() + 90, button.getLayoutY());
        button.setOnAction(actionEvent -> {
            Double[] array = parseTextField(arrayElementsTextField);
            INPUT_DATA.setArray(array);
            INPUT_DATA.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted(arrayElementsTextField, INPUT_DATA);
            CONTROLLER = INPUT_DATA.startSortingAlgorithm();
            delayTextField.clear();
        });
        delete.setOnAction(actionEvent -> {
            Double[] array = parseTextField(arrayElementsTextField);
            INPUT_DATA.setArray(array);
            INPUT_DATA.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted(arrayElementsTextField, INPUT_DATA);
            CONTROLLER = INPUT_DATA.startAdditionalAlgorithm();
            delayTextField.clear();
        });
        selection.setOnAction(actionEvent -> {
            Double[] array = parseTextField(arrayElementsTextField);
            INPUT_DATA.setArray(array);
            INPUT_DATA.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted(arrayElementsTextField, INPUT_DATA);
            CONTROLLER = INPUT_DATA.startSelectionSort();
            delayTextField.clear();
        });
        sinus.setOnAction(actionEvent -> {
            Double[] array = parseTextField(arrayElementsTextField);
            INPUT_DATA.setArray(array);
            INPUT_DATA.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted(arrayElementsTextField, INPUT_DATA);
            CONTROLLER = INPUT_DATA.startSinusAlgorithm();
            delayTextField.clear();
        });
        shell.setOnAction(actionEvent -> {
            ArrayList<String> input = new ArrayList<>();
            Collections.addAll(input, arrayElementsTextField.getText().split(" "));
            ArrayList<ArrayList<String>> input2D = new ArrayList<>();
            for(int i = 0; i < input.size(); i++){
                input2D.add(i, new ArrayList<>());
                Collections.addAll(input2D.get(i), input.get(i).split("\\|"));
            }
            Vector[] inputData = new Vector[input.size()];
            for(int i = 0; i < inputData.length; i++){
                Double[] temp = new Double[input2D.get(i).size()];
                for(int i1 = 0; i1 < input2D.get(i).size(); i1++){
                    temp[i1] = Double.valueOf(input2D.get(i).get(i1));
                }
                inputData[i] = new Vector(temp);
            }
            InputData<Vector> vectorsInput = new InputData<>();
            vectorsInput.setArray(inputData);
            vectorsInput.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted(arrayElementsTextField, vectorsInput);
            CONTROLLER = vectorsInput.startShellAlgorithm();
            delayTextField.clear();
        });
        quick.setOnAction((actionEvent) -> {
            ArrayList<String> input = new ArrayList<>();
            Collections.addAll(input, arrayElementsTextField.getText().split(" "));
            Student[] students = new Student[input.size()];
            for(int i = 0; i < students.length; i++){
                String[] temp = input.get(i).split("\\|");
                students[i] = new Student(temp[0], Double.valueOf(temp[1]));
            }
            InputData<Student> inputData = new InputData<>();
            inputData.setArray(students);
            inputData.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted(arrayElementsTextField, inputData);
            CONTROLLER = inputData.startQuickSort();
            delayTextField.clear();
        });
        PANE.getChildren().addAll(arrayElementsTextField, arraySize, delayTextField, button, random, delete, selection, sinus, shell, quick);
    }
    public Double[] parseTextField(TextField textField){
        Double[] array = new Double[INPUT_DATA.getSize()];
        String[] input = textField.getText().split("\\|");
        for(int i = 0; i < array.length; i++){
            array[i] = Double.valueOf(input[i]);
        }
        return array;
    }
    public void whenStarted(TextField inputField, InputData<?> inputData){
        Button pause = new Button("Pause");
        pause.resize(50, 50);
        pause.relocate(250, 150);
        pause.setOnAction(actionEvent -> CONTROLLER.setToPause(true));
        Label result = new Label();
        result.relocate(50, 230);
        result.setFont(new Font(20));
        Label timeCount = new Label();
        timeCount.relocate(250, 200);
        timeCount.setFont(new Font(20));
        Button reset = new Button("Reset");
        reset.resize(50, 50);
        reset.relocate(300, 150);
        reset.setOnAction(actionEvent -> {
            result.setText("");
            timeCount.setText("");
            inputField.clear();
        });
        inputData.setTimeCountDisplay(timeCount);
        inputData.setLabelToDisplay(result);
        PANE.getChildren().addAll(pause, reset, result, timeCount);
    }
}