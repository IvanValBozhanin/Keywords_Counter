package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/com/company/text.txt");
        if(file.exists()){
            counter(file);
        }
        else {
            System.out.println("File does not exists!");
        }
    }

    public static void counter(File file) throws FileNotFoundException {
        String[] keywordString = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally",
                "float", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while",
                "true", "false", "null" };
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(keywordString));
        Map<String, Integer> map = new HashMap<>();

        for(String s: keywordString){
            map.put(s, 0);
        }
        int c = 0;
        Scanner in = new Scanner(file);
        while(in.hasNext()){
            String s = in.nextLine();
            if(s.length() > 1 && s.startsWith("//")) continue;
            int currPos = 0;
            boolean quote = false;
            while(currPos < s.length()-1) {
                if (!quote) {
                    quote = true;
                    int quotePos = s.substring(currPos).indexOf("\"");
                    String [] arr;
                    if(quotePos == -1){
                        arr = s.substring(currPos).split(" ");
                    }
                    else{
                        arr = s.substring(currPos, quotePos).split(" ");
                    }
                    for(String word: arr){
                        if(hashSet.contains(word)) {
                            ++c;
                            map.put(word, map.get(word) + 1);
                        }
                    }
                    currPos = quotePos + 1;
                    if(quotePos == -1){
                        currPos = s.length() + 1;
                    }
                }
                else {
                    quote = false;
                    currPos = s.substring(currPos).indexOf("\"") + 1;
                }
            }
        }
        System.out.println(c);
        List<Map.Entry> sortedMap = new LinkedList<>(map.entrySet());

        sortedMap.sort((o1, o2) -> {
            int cmp0 = ((Integer) o1.getValue()).compareTo((Integer) o2.getValue());
            if (cmp0 == 0) {
                return ((String) o1.getKey()).compareTo((String) o2.getKey());
            }
            return -cmp0;
        });

        for(Map.Entry entry: sortedMap){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


    }
}
