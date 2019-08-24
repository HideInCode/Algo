package Strings.DataCompressing.BinaryUtils;

import Fundamentals.utils.StdOut;

public class BinaryDump {
    
    // Do not instantiate.
    private BinaryDump() {
    }
    
    /**
     * Reads in a sequence of bytes from standard input and writes
     * them to standard output in binary, k bits per line,
     * where k is given as a command-line integer (defaults
     * to 16 if no integer is specified); also writes the number
     * of bits.
     */
    public static void showBinary() {
        int bitsPerLine = 16;
        int count;
        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
            if (count != 0 && count % bitsPerLine == 0) StdOut.println();
            if (BinaryStdIn.readBoolean()) StdOut.print("1");
            else StdOut.print("0");
        }
        StdOut.println();
        StdOut.println(count + " bits");
    }

//    //流的重定向 一次失败的尝试
//    private static InputStream out2in(OutputStream outputStream) {
//
//        ByteArrayOutputStream out = (ByteArrayOutputStream) outputStream;
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//
//    public static void showBinary(OutputStream out) {
//        InputStream inputStream = out2in(out);
//        System.setIn(inputStream);
//        BinaryStdIn.setIn(inputStream);
//        int bitsPerLine = 16;
//        int count;
//        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
//            if (count != 0 && count % bitsPerLine == 0) StdOut.println();
//            if (BinaryStdIn.readBoolean()) StdOut.print("1");
//            else StdOut.print("0");
//        }
//        StdOut.println();
//        StdOut.println(count + " bits");
//    }
    
    public static void main(String[] args) {
        showBinary();
    }
}
