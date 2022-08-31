package algorithm;

public class SinusAlgorithm extends Algorithm{
    public SinusAlgorithm(Double[] array, int delay){
        super(array, delay);
    }
    private int i;
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
    }
}
