package algorithm;

public class SelectionSort<T> extends Algorithm<T>{
    public SelectionSort(InputData<? extends Comparable<T>> inputData){
        super(inputData);
    }
    private Comparable<T> max;
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
                if(array[i2].compareTo((T) max) > 0){
                    maxIndex = i2;
                    max = array[i2];
                }
            }
            i2--;
            toString = () -> this + "\nMax element in this iteration is " + max + ", swapping with " + array[i];
            waitForDelay();
            Comparable<T> temp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = temp;
            i2 = i + 2;
        }
        i--;
    }
}
