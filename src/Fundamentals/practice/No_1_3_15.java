package Fundamentals.practice;

import Fundamentals.imp.QueueByLinkedList;

/**
 * 对于输入的string,返回倒数第k个字符串,要求用queue
 */
public class No_1_3_15 {
    public static String getReverseIndex(String string, int k) {
        QueueByLinkedList<String> queue = new QueueByLinkedList<>();
        String[] strings = string.split("");
        for (String s : strings) {
            queue.enqueue(s);
        }
        int index = strings.length - k;
        String result = "";
        for (int i = 0; i < index; i++) {
            queue.dequeue();
        }

        return queue.dequeue();
    }

    public static void main(String[] args) {
        String words = "hellaword";
        String reverseIndex = getReverseIndex(words, 3);
        System.out.println(reverseIndex);
    }
}
