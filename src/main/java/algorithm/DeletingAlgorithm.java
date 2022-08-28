package algorithm;
import java.util.*;

class DeletingAlgorithm extends Algorithm {
    public DeletingAlgorithm(Integer[] array, int delay) {
        super(array, delay);
    }

    @Override
    public void run() {
        HashMap<Integer, Integer> map = new HashMap<>();
        toString = () -> this + "\nCurrent frequency map is\n" + map;
        for (Integer integer : array) {
            waitForDelay();
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        int maxValue = Integer.MIN_VALUE;
        int maxElement = 0;
        for (Map.Entry<Integer, Integer> element : map.entrySet()) {
            if (element.getValue() > maxValue) {
                maxValue = element.getValue();
                maxElement = element.getKey();
            }
        }
        int finalMaxElement = maxElement;
        toString = () -> this + "\nCurrent frequency map is\n" + map + "\nThe most frequent element is " + finalMaxElement + "\nDeleting...";
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer[] arrayCopy = array.clone();
        Collections.addAll(arrayList, array);
        arrayList.removeAll(List.of(maxElement));
        array = arrayList.toArray(new Integer[0]);
        toString = () -> this + "\tinitial array was " + Arrays.toString(arrayCopy);
    }
}
