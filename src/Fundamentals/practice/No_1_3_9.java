package Fundamentals.practice;

import java.util.Stack;

/**
 * 字符串缺少左括号，补全左括号
 * 解决方案：搞两个stack,一个放入数值，一个放入操作符，碰到“）”弹出值和下一个操作符，补上“（”
 * 测试数据：1+2)*3-4)*5-6)))
 */
public class No_1_3_9 {
    public static String fillLeft(String loseLeft) {
        Stack<String> valStack = new Stack<>();
        Stack<String> operStack = new Stack<>();

        String[] strings = loseLeft.split("");
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            String curStr = strings[i];
            if (curStr.equals("+")) {
                operStack.push(curStr);
            } else if (curStr.equals("-")) {
                operStack.push(curStr);
            } else if (curStr.equals("*")) {
                operStack.push(curStr);
            } else if (curStr.equals("/")) {
                operStack.push(curStr);
            } else if (curStr.equals(")")) {
                //这里假设输入的字符没有连续两个操作数
                String val1 = valStack.pop();
                String val2 = valStack.pop();
                String operChar = operStack.pop();
                result = "(" + val2 + operChar + val1 + ")";
                //注意取出后要放回，方便下次拼接
                valStack.push(result);
            } else {
                valStack.push(curStr);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "1+2)*3-4)*5-6)))";
        String s = fillLeft(test);
        System.out.println(s);
    }
}
