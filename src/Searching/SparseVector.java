package Searching;


import java.util.Arrays;
import java.util.HashMap;

/**
 * 稀疏向量
 * 矩阵中非零元素的个数远远小于矩阵元素的总数，并且非零元素的分布没有规律，
 * 通常认为矩阵中非零元素的总数比上矩阵所有元素总数的值小于等于0.05时，则称该矩阵为稀疏矩阵(sparse matrix)，
 * 该比值称为这个矩阵的稠密度；与之相区别的是，如果非零元素的分布存在规律（如上三角矩阵、下三角矩阵、对角矩阵），则称该矩阵为特殊矩阵。
 * 比较基本的定义是矩阵中的大多数元素为零，并且可以利用零元素节约大量存储、运算和程序运行时间。
 */
public class SparseVector {

    private HashMap<Integer, Double> map;

    public SparseVector() {

        map = new HashMap<>();
    }

    public int size() {
        return map.size();
    }

    //只放矩阵中不为0的元素
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
    
    
    public static void main(String[] args) {
        SparseVector vector = new SparseVector();
        int n = 4;
        double[] x = new double[n];
        double[] b = new double[n];
    
        for (int i = 0; i < n; i++) {
            b[i] = vector.dot(x);
        }
        System.out.println(Arrays.toString(b));
    }
}
