package com.rawbit.basic.hashmaptest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class HashMapTreefyTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        HashMap<String, String> ssMap = new HashMap<>(3);
//
//        ssMap.put("key-0", "val-0");
//
//        Class mapClazz = ssMap.getClass();
//        Method capacityMethod = mapClazz.getDeclaredMethod("capacity");
//        capacityMethod.setAccessible(true);
//        System.out.println("ssMap capacity: " + capacityMethod.invoke(ssMap));
//
//        Field sizeField = mapClazz.getDeclaredField("size");
//        sizeField.setAccessible(true);
//        System.out.println("ssMap size: " + sizeField.get(ssMap));
//
//        Field loadfactorField = mapClazz.getDeclaredField("loadFactor");
//        loadfactorField.setAccessible(true);
//        System.out.println("ssMap loadfactor: " + loadfactorField.get(ssMap));
//
//        Field thresholdField = mapClazz.getDeclaredField("threshold");
//        thresholdField.setAccessible(true);
//        System.out.println("ssMap threshold: " + thresholdField.get(ssMap));

        for (int i = 0; i < 16; i++) {
            String key = "key-" + i;
            System.out.print(key + " : " + key.hashCode() + "        ");

            String shStr = genSameHashStr(key);
            System.out.println(shStr + " : " + shStr.hashCode());

            ssMap.put(key, "val-" + i);
            ssMap.put(shStr, "val--" + i);
        }

        System.out.println();

    }

    private static String genSameHashStr(String orignStr) {

//        System.out.println("Original: " + orignStr + " hash is " + orignStr.hashCode());

        final char c[] = orignStr.toCharArray();
        int asciiMin = (int) '!';
        int asciiMax = (int) '~';

        String sameHashStr = null;
        for (int delta2 = -3; delta2 <= +3; delta2++) {
            if (delta2 != 0) {
                char orig2 = c[orignStr.length() - 2];
                int mod2 = orig2 + delta2;
                boolean isInRange2 = asciiMin <= mod2 && mod2 <= asciiMax;
                if (isInRange2) {
                    char orig1 = c[orignStr.length() - 1];
                    int delta1 = delta2 * 31;
                    int mod1 = orig1 - delta1;
                    boolean isInRange1 = asciiMin <= mod1 && mod1 <= asciiMax;
                    if (isInRange1) {
                        char[] modc = orignStr.toCharArray();
                        modc[orignStr.length() - 2] = (char) mod2;
                        modc[orignStr.length() - 1] = (char) mod1;
                        sameHashStr = new String(modc);
//                        System.out.println(sameHashStr + " hash is " + sameHashStr.hashCode());
                    }
                }
            }
        }
        return sameHashStr;
    }

}
