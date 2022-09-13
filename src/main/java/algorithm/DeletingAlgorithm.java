package algorithm;
import java.util.*;

class DeletingAlgorithm<T> extends Algorithm<T> {
    public DeletingAlgorithm(InputData<? extends Comparable<T>> inputData) {
        super(inputData);
    }

    @Override
    public void run() {
        HashMap<Comparable<T>, Integer> map = new HashMap<>();
        toString = () -> this + "\nCurrent frequency map is\n" + map;
        for (Comparable<T> aDouble : array) {
            waitForDelay();
            if (map.containsKey(aDouble)) {
                map.put(aDouble, map.get(aDouble) + 1);
            } else {
                map.put(aDouble, 1);
            }
        }
        int maxValue = Integer.MIN_VALUE;
        Comparable<T> maxElement = null;
        for (Map.Entry<Comparable<T>, Integer> element : map.entrySet()) {
            if (element.getValue() > maxValue) {
                maxValue = element.getValue();
                maxElement = element.getKey();
            }
        }
        Comparable<T> finalMaxElement = maxElement;
        toString = () -> this + "\nCurrent frequency map is\n" + map + "\nThe most frequent element is " + finalMaxElement + "\nDeleting...";
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {
            }
        }
        ArrayList<Comparable<T>> arrayList = new ArrayList<>();
        Comparable<T>[] arrayCopy = array.clone();
        Collections.addAll(arrayList, array);
        arrayList.removeAll(List.of(maxElement));
        array = arrayList.toArray(arrayCopy);
        toString = () -> this + "\tinitial array was " + Arrays.toString(arrayCopy);
    }
}
