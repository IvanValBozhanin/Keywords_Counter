package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

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
    }
}
