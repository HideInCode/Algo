package Searching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 这个没有测试
 */
public class FileIndex {


    private Set<String> getWord(String line) {
        String[] strings = line.split("");
        Set<String> result = new HashSet<>();

        for (String string : strings) {
            if (string.matches("\\w+")) {
                result.add(string);
            }
        }

        return result;
    }

    /**
     * 把一个文件中的每个单词和文件建立对应的索引
     *
     * @param path
     * @throws FileNotFoundException
     */
    @SuppressWarnings("uncheck")
    public void createFileIndex(String path) throws IOException {
        File file = new File(path);
        BufferedReader in = new BufferedReader(new FileReader(file));

        Map<String, Set<File>> map = new HashMap<>();

        String line = "";
        while ((line = in.readLine()) != null) {
            Set<String> wordSet = getWord(line);
            for (String word : wordSet) {
                if (!map.containsKey(word)) {
                    map.put(word, new HashSet<>());
                }
                map.get(word).add(file);
            }

        }

    }
}
