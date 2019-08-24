package Strings.DataCompressing;

import Strings.DataCompressing.BinaryUtils.BinaryStdIn;
import Strings.DataCompressing.BinaryUtils.BinaryStdOut;

/**
 * 游程编码,解决重复长串的问题,广泛用于位图,分辨率越高,压缩率越低.
 * 思想: 把重复串长度编码,如00000变成5,然后确定5用多少位表示
 * 数据结构:二进制输入输出流
 * 算法流程:
 * 1.去一个bit
 * 2.和上一个比较:
 * 不同时,把当前值写入流,计数器归零
 * 相同时,如果计数器达到最大值,写入计数值,再写一个0,然后将计数器归零;
 * 3.最后增加计数器的值
 */
public class RunLength {
    private RunLength() {
    }
    
    public static void expand() {
        boolean flag = false;
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            for (int i = 0; i < c; i++) {
                BinaryStdOut.write(flag);
                
            }
            flag = !flag;
        }
        BinaryStdOut.close();
    }
    
    
    public static void compress() {
        char count = 0;
        boolean flag = false;
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) {
            boolean b = BinaryStdIn.readBoolean();
            if (b != old) {
                BinaryStdOut.write(count);
                count = 0;
                old = !old;
            } else {
                if (count == 255) {
                    BinaryStdOut.write(count);
                    count = 0;
                    BinaryStdOut.write(count);
                }
            }
            
            count++;
        }
        
        BinaryStdOut.write(count);
        BinaryStdOut.close();
    }
}

