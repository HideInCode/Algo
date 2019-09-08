package Fundamentals.practice.bag_queue_stack;

import Fundamentals.imp.FixedCapacityStack;

/**
 * 计算后缀表达式,两个stack,注意吐值顺序
 * 3 4 + 5 × 6 -  应该是((3+4)*5)-6
 */
public class No_1_3_11 {
    //只允许加减乘除和数字,不可以为空
    private static boolean isValidateExp(String exp) {
        return exp.matches("^[\\+\\-\\*\\/\\d]+$");
    }

    public static int evaluatePostfix(String postfix) throws Exception {
        FixedCapacityStack<Integer> vals = new FixedCapacityStack<>(100);
        FixedCapacityStack<String> ops = new FixedCapacityStack<>(100);
        if (!isValidateExp(postfix)) {
            throw new Exception("注意格式");
        }
        String[] strings = postfix.split("");
        for (String string : strings) {
            if (string.equals("+")) {
                Integer right = vals.pop();
                Integer left = vals.pop();
                vals.push(left + right);
            } else if (string.equals("-")) {
                Integer right = vals.pop();
                Integer left = vals.pop();
                vals.push(left - right);
                ops.push(string);
            } else if (string.equals("*")) {
                Integer right = vals.pop();
                Integer left = vals.pop();
                vals.push(left * right);
                ops.push(string);
            } else if (string.equals("/")) {
                Integer right = vals.pop();
                Integer left = vals.pop();
                vals.push(left / right);
                ops.push(string);
            } else {
                vals.push(Integer.parseInt(string));
            }
        }


        return vals.pop();
    }

    public static void main(String[] args) {
        try {
            int i = evaluatePostfix("34+5*6-");
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
