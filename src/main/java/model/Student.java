package model;

public class Student implements Comparable<Student>{
    private final String name;
    private final Double average;

    public Student(String name, Double average) {
        this.name = name;
        this.average = average;
    }

    public Double getAverage() {
        return average;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
