package algorithm;

import model.InputData;

public class SinusAlgorithm extends Algorithm<Double>{
    public SinusAlgorithm(InputData<Double> inputData){
        super(inputData);
        this.array = inputData.getArray();
    }
    private int i;
    private Double[] array;
    @Override
    public void run() {
        for(i = 0; i < array.length; i++){
            toString = () -> this + "\nCurrent element in work is " + array[i];
            waitForDelay();
            if(array[i] < 0){
                toString = () -> this + "\nCurrent element in work is " + array[i] + "\nsin(" + array[i] + ") = " + Math.round(Math.sin(array[i]) * 1000) / 1000.0;
                waitForDelay();
                array[i] = Math.round(Math.sin(array[i]) * 1000) / 1000.0;
            }
        }
        i--;
        waitForDelay();
    }
}
