package ru.otus;

import ru.otus.DIYArrayList;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        DIYArrayList<String> myList = new DIYArrayList<>();
        DIYArrayList<String> myCopyList = new DIYArrayList<>(40);


        System.out.println("Add elements to myList and myCopylist:");
        Collections.addAll(myList, "1a", "exb2", "3c");
        Collections.addAll(myCopyList,"4r","fdf","d4f", "453d");

        print(myList);
        System.out.println("_____________________________");
        print(myCopyList);

        System.out.println("Sort. myList and copy myList in the myCopyList:");
        Collections.sort(myList);
        Collections.copy(myCopyList,myList);

        print(myList);
        System.out.println("_______________________");
        print(myCopyList);

    }

    private static void print(List<?> list){
        for (Object element: list){
            System.out.println(element);
        }
    }
}
