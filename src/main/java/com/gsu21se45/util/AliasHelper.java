package com.gsu21se45.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class AliasHelper {

    private static Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

    public static Map<String, Integer> toMap(String[] aliases) {
        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i], i);
        }
        return aliasToIndexMap;
    }
}
