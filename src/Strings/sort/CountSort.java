package Strings.sort;

import java.util.Arrays;

/**
 * 计数排序 桶排序的一种应用,把数据分发到桶中,保留原始顺序
 * 适用于元排序元素类型确定而且类型较少时,可以拥有线性复杂度
 */
public class CountSort {
    static class Data {
        private int key;
        private String value;
    
        public Data(int key, String value) {
            this.key = key;
            this.value = value;
        }
    
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"key\":")
                    .append(key);
            sb.append(",\"value\":\"")
                    .append(value).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }
    
    /**
     * R为元素种类
     * @param data
     * @param R
     */
    public void sort(Data[] data,int R) {
        int n = data.length;
        Data[] aux = new Data[n];
        //此数组就是桶,R+1是为了最大下标是R
        int[] count = new int[R + 1];
        for (int i = 0; i < n; i++) {
            //方便下一步根据count获取索引
            count[data[i].key + 1]++;
        }
    
        //在辅助数组中的起始索引,保证排序稳定性
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }
        
        //在辅助数组中排序
        for (int i = 0; i < n; i++) {
            //
            aux[count[data[i].key]++] = data[i];
        }
    
        for (int i = 0; i < n; i++) {
            data[i] = aux[i];
        }
    }
    
    public static void main(String[] args) {
        String s = "Anderson 2 Harris 1\n" +
                           "Brown 3 Martin 1\n" +
                           "Davis 3 Moore 1\n" +
                           "Garcia 4 Anderson 2\n" +
                           "Harris 1 Martinez 2\n" +
                           "Jackson 3 Miller 2\n" +
                           "Johnson 4 Robinson 2\n" +
                           "Jones 3 White 2\n" +
                           "Martin 1 Brown 3\n" +
                           "Martinez 2 Davis 3\n" +
                           "Miller 2 Jackson 3\n" +
                           "Moore 1 Jones 3\n" +
                           "Robinson 2 Taylor 3\n" +
                           "Smith 4 Williams 3\n" +
                           "Taylor 3 Garcia 4\n" +
                           "Thomas 4 Johnson 4\n" +
                           "Thompson 4 Smith 4\n" +
                           "White 2 Thomas 4\n" +
                           "Williams 3 Thompson 4\n" +
                           "Wilson 4 Wilson 4";
        String[] strings = s.split("\n");
        int n = strings.length;
        Data[] data = new Data[n];
        int count =0;
        for (String string : strings) {
            String[] split = string.split(" ");
            String val = split[0];
            int key = Integer.parseInt(split[1]);
            Data da = new Data(key, val);
            data[count++] = da;
        }
        CountSort sort = new CountSort();
        sort.sort(data, 5);
        System.out.println(Arrays.toString(data));
    }
    
    
}
