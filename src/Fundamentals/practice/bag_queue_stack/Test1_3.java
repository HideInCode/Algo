package Fundamentals.practice.bag_queue_stack;

import Fundamentals.api.Stack;
import Fundamentals.imp.FixedCapacityStack;
import Fundamentals.imp.StackByLinkedList;

public class Test1_3 {
    private static final String BIG_LEFT = "{";
    private static final String BIG_RIGHT = "}";
    private static final String MID_LEFT = "[";
    private static final String MID_RIGHT = "]";
    private static final String SMALL_LEFT = "(";
    private static final String SMALL_RIGHT = ")";
    
    
    /**
     * 判断括号是否完整匹配
     * @param s 待判断
     * @return 匹配
     */
    private static boolean isParenthesesMatch(String s) {
        
        String[] strings = s.split("");
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(100);
        
        boolean isBalance = true;
        for (int i = 0; i < strings.length; i++) {
            String temp = strings[i];
            switch (temp) {
                case BIG_LEFT:
                    stack.push(temp);
                    continue;
                case MID_LEFT:
                    stack.push(temp);
                    continue;
                case SMALL_LEFT:
                    stack.push(temp);
                    continue;
                
                case BIG_RIGHT:
                    if (stack.isEmpty()) {
                        isBalance = false;
                    } else if (!BIG_LEFT.equals(stack.pop())) {
                        isBalance = false;
                        break;
                    } else {
                        continue;
                    }
                case MID_RIGHT:
                    if (stack.isEmpty()) {
                        return false;
                    } else if (!MID_LEFT.equals(stack.pop())) {
                        isBalance = false;
                        break;
                    } else {
                        continue;
                    }
                case SMALL_RIGHT:
                    if (stack.isEmpty()) {
                        return false;
                    } else if (!SMALL_LEFT.equals(stack.pop())) {
                        isBalance = false;
                        break;
                    } else {
                        continue;
                    }
            }
        }
        
        return isBalance;
        
    }
    
    //1+2)*3-4)*5-6)))中序计算
    public static String fillLeft(String loseLeft) {
        Stack<String> valStack = new StackByLinkedList<>();
        Stack<String> operStack = new StackByLinkedList<>();
        
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
    
    /**
     * 将中序表达式改为后序表达式 ((3+4)*5)-6
     * 概念：
     * 中序表达式:((3+4)*5)-6
     * 后缀表达式又称逆波兰表达式,与前缀表达式相似，只是运算符位于操作数之后3 4 + 5 × 6 -
     * 前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前- × + 3 4 5 6
     * 解决方案：还是两个stack,一个放入操作数,一个放入数值,注意输出结果里不需要括号
     * 测试数据：((3+4)*5)-6
     */
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
        System.out.println(isParenthesesMatch("[(])"));
    }
}
