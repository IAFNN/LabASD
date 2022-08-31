package algorithm;

public class SelectionSort extends Algorithm{
    public SelectionSort(Double[] array, int delay){
        super(array, delay);
    }
    private double max;
    private int i2;
    private int i;
    @Override
    public void run() {
        for(i = 0; i < array.length; i++){
            int maxIndex = i;
            max = array[i];
            toString = () -> this + "\nCurrent max element is " + max + ", now comparing with " + array[i2];
            for(i2 = i + 1; i2 < array.length; i2++){
                waitForDelay();
                if(array[i2] > max){
                    maxIndex = i2;
                    max = array[i2];
                }
            }
            i2--;
            toString = () -> this + "\nMax element in this iteration is " + max + ", swapping with " + array[i];
            waitForDelay();
            double temp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = temp;
            i2 = i + 2;
        }
        i--;
    }
}
