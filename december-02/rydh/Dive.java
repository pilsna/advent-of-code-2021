package rydh;

import java.util.HashMap;

public class Dive {
    public static void main(String args[]) {
        System.out.println("This is it!");
    }
    public class DiveMap extends HashMap<String, Integer> {

        @Override
        public Integer put(String key, Integer value) {
            int currentSum = super.get(key);
            return super.put(key, currentSum + value);
        }
        
    }
}