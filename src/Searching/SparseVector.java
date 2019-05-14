package Searching;


import java.util.HashMap;

/**
 * 稀疏向量
 */
public class SparseVector {

    private HashMap<Integer, Double> map;

    public SparseVector() {

        map = new HashMap<>();
    }

    public int size() {
        return map.size();
    }

    public void put(int i, double x) {
        map.put(i, x);
    }

    public double get(int i) {
        if (!map.containsKey(i)) {
            return 0;
        } else {
            return map.get(i);
        }


    }


    public double dot(double[] that) {
        double sum = 0.0;
        for (Integer integer : map.keySet()) {
            sum += that[integer] * this.get(integer);
        }
        return sum;
    }
}
