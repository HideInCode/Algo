package Searching;

import java.util.concurrent.LinkedBlockingDeque;

public class LookupIndex {


    public void index(String param) {


        //正向索引
        BST<String, LinkedBlockingDeque<String>> st = new BST<>();

        //反向索引
        BST<String, LinkedBlockingDeque<String>> ts = new BST<>();

        String[] strings = param.split("");


        String key = "key";
        for (String val : strings) {
            if (!st.contains(key)) {
                st.put(key, new LinkedBlockingDeque<String>());
            }

            if (!ts.contains(val)) {
                ts.put(val, new LinkedBlockingDeque<String>());
            }

            st.get(key).add(val);
            ts.get(val).add(key);

        }

    }

}
