package algorithm;
import java.util.*;

class DeletingAlgorithm extends Algorithm {
    public DeletingAlgorithm(Double[] array, int delay) {
        super(array, delay);
    }

    @Override
    public void run() {
        HashMap<Double, Integer> map = new HashMap<>();
        toString = () -> this + "\nCurrent frequency map is\n" + map;
        for (Double aDouble : array) {
            waitForDelay();
            if (map.containsKey(aDouble)) {
                map.put(aDouble, map.get(aDouble) + 1);
            } else {
                map.put(aDouble, 1);
            }
        }
        int maxValue = Integer.MIN_VALUE;
        double maxElement = 0;
        for (Map.Entry<Double, Integer> element : map.entrySet()) {
            if (element.getValue() > maxValue) {
                maxValue = element.getValue();
                maxElement = element.getKey();
            }
        }
        double finalMaxElement = maxElement;
        toString = () -> this + "\nCurrent frequency map is\n" + map + "\nThe most frequent element is " + finalMaxElement + "\nDeleting...";
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {
            }
        }
        ArrayList<Double> arrayList = new ArrayList<>();
        Double[] arrayCopy = array.clone();
        Collections.addAll(arrayList, array);
        arrayList.removeAll(List.of(maxElement));
        array = arrayList.toArray(new Double[0]);
        toString = () -> this + "\tinitial array was " + Arrays.toString(arrayCopy);
    }
}
