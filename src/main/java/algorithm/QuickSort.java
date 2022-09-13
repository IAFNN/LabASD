package algorithm;

import model.InputData;
import model.Student;

import java.util.ArrayList;

public class QuickSort extends Algorithm<Student>{
    private Student[] array;
    public QuickSort(InputData<Student> inputData){
        super(inputData);
        toString = this::toString;
        array = inputData.getArray();
    }

    @Override
    public void run() {
        waitForDelay();
        ArrayList<Student> studentsWithAvg4Plus = new ArrayList<>();
        for (Student student : array) {
            if (student.getAverage() > 4) {
                studentsWithAvg4Plus.add(student);
            }
        }
        toString = () -> this + "\nStudents with average mark 4+ are " + studentsWithAvg4Plus;
        waitForDelay();
        array = studentsWithAvg4Plus.toArray(new Student[0]);
        quickSort(array, 0, array.length - 1);
        toString = this::toString;
        waitForDelay();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Student student : array){
            result.append(student.getName()).append("\t").append(student.getAverage()).append("\n");
        }
        return result.toString();
    }
    public void quickSort(Student[] array, int begin, int end) {
        if (begin < end) {
            toString = () -> this + "\nRunning quick sort from " + begin + " to " + end;
            waitForDelay();
            int partitionIndex = partition(array, begin, end);
            toString = () -> this + "\nPartition element is " + array[partitionIndex] + "\nRunning quick sort from " + begin + " to " + (partitionIndex - 1);
            waitForDelay();
            quickSort(array, begin, partitionIndex-1);
            toString = () -> this + "\nPartition element is " + array[partitionIndex] + "\nRunning quick sort from " + (partitionIndex + 1) + " to " + end;
            waitForDelay();
            quickSort(array, partitionIndex+1, end);
        }
    }
    private int partition(Student[] array, int begin, int end) {
        Student pivot = array[(end + begin) / 2];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                Student swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
                waitForDelay();
            }
        }

        Student swapTemp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = swapTemp;
        waitForDelay();

        return i + 1;
    }
}
