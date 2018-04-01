package com.adsearch;

import java.util.List;

import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.util.CharArraySet;

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
