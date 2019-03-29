package ru.otus;

import com.google.common.base.Splitter;

public class HelloOtus {
    public static void main(String[] args) {
        Iterable<String> arr = Splitter.on(',').trimResults().omitEmptyStrings().split("abc, bcd,, cde   ,zsa");
        System.out.println(arr.toString());
    }
}
