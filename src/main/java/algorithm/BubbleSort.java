package algorithm;

class BubbleSort extends Algorithm {
    public BubbleSort(Double[] array, int delay) {
        super(array, delay);
    }

    private int i2;

    @Override
    public void run() {
        int i;
        toString = () -> this + "\nCurrent elements in work are " + array[i2] + " " + array[i2 + 1];
        for (i = 0; i < array.length; i++) {
            boolean toStop = true;
            for (i2 = 0; i2 < array.length - i - 1; i2++) {
                waitForDelay();
                if (array[i2] > array[i2 + 1]) {
                    double temp2 = array[i2];
                    array[i2] = array[i2 + 1];
                    array[i2 + 1] = temp2;
                    toStop = false;
                }
            }
            if (toStop) {
                break;
            }
        }

    }
}
