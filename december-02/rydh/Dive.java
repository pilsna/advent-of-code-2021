package rydh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dive {
    private static final String CSV_DELIMITER = " ";

    public static void main(String args[]) {
        Map<String, Integer> map = loadFromFile("./december-02/rydh/Dive.txt");
        System.out.println((map.get("down") - map.get("up")) * map.get("forward"));

        List<List<String>> dataList = loadFromCsv("./december-02/rydh/Dive.txt");
        int forward = 0;
        int up = 0;
        int down = 0;
        for (List<String> list : dataList) {
            switch (list.get(0)) {
                case "forward":
                    forward += Integer.parseInt(list.get(1));
                    break;
                case "up":
                    up += Integer.parseInt(list.get(1));
                    break;
                case "down":
                    down += Integer.parseInt(list.get(1));
                    break;
                default:
                    break;
            }
        }
        System.out.println(forward * (down - up));
    }

    public static String mapToString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public static List<List<String>> loadFromCsv(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(CSV_DELIMITER);
                records.add(Arrays.asList(values));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static Map<String, Integer> loadFromFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader;
        Map<String, Integer> map = new HashMap<>();
        map.put("forward", 0);
        map.put("up", 0);
        map.put("down", 0);
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(" ");
                int sum = map.get(columns[0]);
                sum = Integer.parseInt(columns[1]) + sum;
                map.put(columns[0], sum);
            }
            reader.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}