package Fundamentals.practice;

import Fundamentals.imp.FixedCapacityStack;

/**
 * 将中序表达式改为后序表达式
 * 概念：
 * 中序表达式:((3+4)*5)-6
 * 后缀表达式又称逆波兰表达式,与前缀表达式相似，只是运算符位于操作数之后3 4 + 5 × 6 -
 * 前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前- × + 3 4 5 6
 * 解决方案：还是两个stack,一个放入操作数,一个放入数值,注意输出结果里不需要括号
 * 测试数据：((3+4)*5)-6
 */
public class No_1_3_10 {
    public static String infixToPostfix(String infix) {
        if (!infix.endsWith(")")) {
            infix = "(" + infix + ")";
        }
        FixedCapacityStack<String> ops = new FixedCapacityStack<>(100);
        FixedCapacityStack<String> vals = new FixedCapacityStack<>(100);
        String[] strings = infix.split("");
        //foreach是顺序输出的
        String temp = "";
        for (String string : strings) {
            switch (string) {
                case "+":
                    ops.push(string);
                    continue;
                case "-":
                    ops.push(string);
                    continue;
                case "*":
                    ops.push(string);
                    continue;
                case "/":
                    ops.push(string);
                    continue;
                    //如果是右括号,就输出栈顶的两个数值,外加一个操作数.默认情况都是数值.
                case ")":
                    if (vals.isEmpty() || ops.isEmpty()) {
                        break;
                    }
                    String first = vals.pop();
                    String second = vals.pop();
                    String op = ops.pop();
                    temp = second + first + op;
                    //用之前的数值栈造出新的字符串,并放回去
                    vals.push(temp);
                    continue;
                default:
                    if (!string.equals("(")) {
                        vals.push(string);
                    }
                    continue;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        String s = infixToPostfix("((3+4)*5)-6");
        System.out.println(s);
    }
}
