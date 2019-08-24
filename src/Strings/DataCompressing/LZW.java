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
    private static final int L = 2096;
    
    //编码宽度
    private static final int W = 12;
    
    public static void compress() {
        
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++) {
            st.put("" + ((char) i), i);
        }
        
        //R为文件结束(EOF)的编码
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
        
        //下一个待补全的编码值
        int i;
        
        //初始化字符编译表
        for (i = 0; i < R; i++) {
            st[i] = "" + ((char) i);
        }
        
        //文件结束标记的前瞻字符
        st[i++] = " ";
        
        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        
        while (true) {
            BinaryStdOut.write(val);
            codeword = -BinaryStdIn.readInt(W);
            
            if (codeword == R) {
                break;
            }
            
            String s = st[codeword];
            
            if (i == codeword) {
                s = val + val.charAt(0);
            }
            if (i < L) {
                st[i++] = val + s.charAt(0);
            }
            val = s;
        }
        BinaryStdOut.close();
        
    }
}
