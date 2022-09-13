package algorithm;

public class Vector implements Comparable<Vector>{
    private Double[] array;

    public Vector(Double[] array) {
        this.array = array;
    }

    public Double[] getArray() {
        return array;
    }

    public void setArray(Double[] array) {
        this.array = array;
    }

    @Override
    public int compareTo(Vector vector) {
        return (int) ((array[0] - vector.array[0]) * 100);
    }
}
