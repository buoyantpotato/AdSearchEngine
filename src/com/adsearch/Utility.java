package com.adsearch;

import java.util.List;

public class Utility {

    public static String joinStrings(List<String> list, String delimiter) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            res.append(list.get(i)).append(delimiter);
        }
        if (list.size() > 0) {
            res.append(list.get(list.size() - 1));
        }
        return res.toString();
    }
}
