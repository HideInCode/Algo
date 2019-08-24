package Strings.DataCompressing;

import Strings.Alphabet;
import Strings.DataCompressing.BinaryUtils.BinaryStdIn;
import Strings.DataCompressing.BinaryUtils.BinaryStdOut;

/**
 * 以基因为例,基因就是一条碱基序列,碱基由TACG4种字符表示
 * 这些字符都可以用2个bit压缩,故称作双位编码压缩
 */
public class Genome {
    
    public static void compress() {
        Alphabet DNA = new Alphabet("ACTG");
        String string = BinaryStdIn.readString();
        BinaryStdOut.write(string.length());
        for (int i = 0; i < string.length(); i++) {
            //将字符转为双位编码
            int index = DNA.toIndex(string.charAt(i));
            BinaryStdOut.write(index, DNA.lgR());
        }
        BinaryStdOut.close();
    }
    
    public static void expand() {
        Alphabet DNA = new Alphabet("ACTG");
        int lgR = DNA.lgR();
        int N = BinaryStdIn.readInt();
        
        for (int i = 0; i < N; i++) {
            //每两个bit,写入一个字符
            char c = BinaryStdIn.readChar(lgR);
            BinaryStdOut.write(DNA.toChar(c));
        }
        BinaryStdOut.close();
    }
    
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            compress();
        }
        
        if (args[0].equals("+")) {
            expand();
        }
    }
}
