package rydh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dive {
    private static final String CSV_DELIMITER = " ";

    public static void main(String args[]) {
        List<List<String>> dataList = loadFromCsv("./december-02/rydh/Dive.txt");
        partOneCalculation(dataList);
        partTwoCalculation(dataList);
    }

    private static void partTwoCalculation(List<List<String>> dataList) {
        int forward = 0;
        int up = 0;
        int down = 0;
        double aim = 0;
        for (List<String> list : dataList) {
            switch (list.get(0)) {
                case "forward":
                    forward += Integer.parseInt(list.get(1));
                    down += aim * Integer.parseInt(list.get(1));
                    break;
                case "up":
                    aim -= Integer.parseInt(list.get(1));
                    break;
                case "down":
                    aim += Integer.parseInt(list.get(1));
                    break;
                default:
                    break;
            }
        }
        System.out.println("partTwoCalculation " + forward * (down - up));
    }

    private static void partOneCalculation(List<List<String>> dataList) {
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
        System.out.println("partOneCalculation " + forward * (down - up));
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
}