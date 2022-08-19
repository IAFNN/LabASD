package com.example.lab1asd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class HelloApplication extends Application {
    private final InputData INPUT_DATA = new InputData();
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
        return new Scene(PANE, 800, 600);
    }
    public void whenSizeEntered(){
        Label arraySize = new Label();
        arraySize.relocate(30, 100);
        arraySize.setText("Array size: " + INPUT_DATA.getSize());
        TextField arrayElementsTextField = new TextField("Enter " + INPUT_DATA.getSize() + " integers splitted" +
                " with '|'");
        arrayElementsTextField.setPrefSize(300, 20);
        arrayElementsTextField.relocate(arraySize.getLayoutX() + arraySize.getWidth() + 100, arraySize.getLayoutY() - 4);
        Button button = new Button("Start sorting");
        button.resize(50, 50);
        button.relocate(arrayElementsTextField.getLayoutX() + 420, arrayElementsTextField.getLayoutY());
        Button delete = new Button("Start deleting");
        delete.relocate(arrayElementsTextField.getLayoutX() + 320, arrayElementsTextField.getLayoutY());
        Button random = new Button("Random");
        random.resize(50, 50);
        random.relocate(arrayElementsTextField.getLayoutX() + 240, arrayElementsTextField.getLayoutY());
        random.setOnAction(actionEvent -> {
            String array = "";
            for(int i = 0; i < INPUT_DATA.getSize(); i++){
                array = array.concat(((int) (Math.random() * 100)) + "|");
            }
            array = array.substring(0, array.length() - 1);
            arrayElementsTextField.setText(array);
        });
        TextField delayTextField = new TextField("Enter delay between steps");
        delayTextField.resize(300, 20);
        delayTextField.relocate(button.getLayoutX() + 90, button.getLayoutY());
        button.setOnAction(actionEvent -> {
            Integer[] array = parseTextField(arrayElementsTextField);
            INPUT_DATA.setArray(array);
            INPUT_DATA.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted();
            CONTROLLER = INPUT_DATA.startSortingAlgorithm();
            delayTextField.clear();
            arrayElementsTextField.clear();
        });
        delete.setOnAction(actionEvent -> {
            Integer[] array = parseTextField(arrayElementsTextField);
            INPUT_DATA.setArray(array);
            INPUT_DATA.setDelay(Integer.parseInt(delayTextField.getText()));
            whenStarted();
            CONTROLLER = INPUT_DATA.startAdditionalAlgorithm();
            delayTextField.clear();
            arrayElementsTextField.clear();
        });
        PANE.getChildren().addAll(arrayElementsTextField, arraySize, delayTextField, button, random, delete);
    }
    public Integer[] parseTextField(TextField textField){
        Integer[] array = new Integer[INPUT_DATA.getSize()];
        String[] input = textField.getText().split("\\|");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(input[i]);
        }
        return array;
    }
    public void whenStarted(){
        Label status = new Label("Running");
        status.relocate(375, 153);
        Button pause = new Button("Pause");
        pause.resize(50, 50);
        pause.relocate(250, 150);
        pause.setOnAction(actionEvent -> {
            CONTROLLER.setToPause(true);
            status.setText("Paused");
        });
        Button resume = new Button("Resume");
        resume.resize(50, 50);
        resume.relocate(500, 150);
        resume.setOnAction(actionEvent -> {
            CONTROLLER.setToResume(true);
            status.setText("Running");
        });
        Label result = new Label();
        result.relocate(250, 200);
        result.setFont(new Font(20));
        Button reset = new Button("Reset");
        reset.resize(50, 50);
        reset.relocate(600, 150);
        reset.setOnAction(actionEvent -> {
            status.setText("");
            result.setText("");
        });
        INPUT_DATA.setLabelToDisplay(result);
        PANE.getChildren().addAll(pause, resume, status, reset, result);
    }
}