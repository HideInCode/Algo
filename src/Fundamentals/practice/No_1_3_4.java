package Fundamentals.practice;

import Fundamentals.imp.FixedCapacityStack;

/**
 * 输入一个字符判断是完整,
 * 想象你在捡贝壳,左半边先放入袋子,遇见右半边就比对,成对就挑出,比对下一个,如果全都比对上,就认为匹配上
 * 只要有一个没有和最近的匹配,就放弃,认为比对不上
 */
public class No_1_3_4 {
    private static final String BIG_LEFT = "{";
    private static final String BIG_RIGHT = "}";
    private static final String MID_LEFT = "[";
    private static final String MID_RIGHT = "]";
    private static final String SMALL_LEFT = "(";
    private static final String SMALL_RIGHT = ")";

    public static boolean isBalanced(String toChecked) {
        String[] strings = toChecked.split("");
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

    public static void main(String[] args) {
        String test = "[(])";
        boolean balanced = isBalanced(test);
        System.out.println(balanced);
    }
}
