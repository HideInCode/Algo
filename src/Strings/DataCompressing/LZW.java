package Strings.DataCompressing;

import Strings.DataCompressing.BinaryUtils.BinaryStdIn;
import Strings.DataCompressing.BinaryUtils.BinaryStdOut;
import Strings.search.TST;

/**
 * lempel+ziv+welch发明
 * 利用变长模式生成定长的编码编译表;
 */
public class LZW {
    //输入的字符数
    private static final int R = 256;
    
    //编码总数=2^12
    private static final int L = 4096;
    
    //编码宽度
    private static final int W = 12;
    
    public static void compress() {
        
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++) {
            st.put("" + ((char) i), i);
        }
        
        //R为文件结束(EOF)的编码,所以从R+1开始记录新的字符
        int code = R + 1;
        
        while (input.length() > 0) {
            //找打匹配的最长前缀
            String s = st.longestPrefixOf(input);
            
            //打印出最长前缀的编码
            BinaryStdOut.write(st.get(s), W);
            
            //将s写入符号表
            int t = s.length();
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);
        }
        
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }
    
    public static void expand() {
        String[] st = new String[L];
        int i;
        //初始化表,并把i挪到字母表最后一位.
        for (i = 0; i < R; i++) {
            st[i] = "" + ((char) i);
        }
        st[i++] = " ";
        //根据长度读第一个字符串编码,对应压缩的字符
        int wordCode = BinaryStdIn.readInt(W);
        String val = st[wordCode];
    
        while (true) {
            BinaryStdOut.write(val);
            wordCode = BinaryStdIn.readInt(W);
            //已经用完...
            if (wordCode == R) {
                break;
            }
            String s = st[wordCode];
            //下个字符不可用,在表中生成新的编码对应字符
            if (i == wordCode) {
                s = val + val.charAt(0);
            }
            if (i < L) {
                st[i++] = val + s.charAt(0);
            }
            val = s;
        }
        BinaryStdOut.close();
    }
    public static void main(String[] args){
        if (args[0].equals("-")) {
            compress();
        }
    
        if (args[0].equals("+")) {
            expand();
        }
    }
}
