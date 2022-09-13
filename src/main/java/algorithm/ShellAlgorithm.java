package algorithm;

public class ShellAlgorithm extends Algorithm<Vector>{
    public ShellAlgorithm(InputData<Vector> inputData){
        super(inputData);
        array = inputData.getArray();
        toString = this::toString;
    }
    private Vector[] array;
    private int i0;
    private int gap;
    private double min;
    @Override
    public void run() {
        waitForDelay();
        for(i0 = 0; i0 < array.length; i0++){
            Double[] temp = array[i0].getArray();
            min = temp[0];
            int minIndex = 0;
            for(int i = 0; i < temp.length; i++){
                if(temp[i] < min){
                    min = temp[i];
                    minIndex = i;
                }
            }
            toString = () -> this + "\nMin element in " + i0 + " column is " + min + "\nln(" + min + ") = " + Math.log(min);
            temp[minIndex] = Math.log(temp[minIndex]);
            waitForDelay();
        }
        for(gap = array.length / 2; gap > 0; gap /= 2){
            toString = () -> this + "\nCurrent gap is " + gap;
            waitForDelay();
            for(int i = gap; i < array.length; i++){
                StringBuilder string = new StringBuilder();
                for(int iStr = i - gap; iStr < array.length; iStr += gap){
                    string.append(iStr).append(" ");
                }
                toString = () -> this + "\nCurrent working group is " + string;
                waitForDelay();
                Vector temp = array[i];
                int j;

                for(j = i; j >= gap; j -= gap){
                    if(array[j - gap].compareTo(temp) > 0){
                        array[j] = array[j - gap];
                        array[j - gap] = temp;
                    }
                }
            }
        }
        waitForDelay();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < array[0].getArray().length; i++){
            for (Vector vector : array) {
                string.append(String.format("%06.2f", vector.getArray()[i])).append("  ");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
