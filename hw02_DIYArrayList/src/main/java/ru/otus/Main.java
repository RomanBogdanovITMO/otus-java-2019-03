package ru.otus;

import java.util.*;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {


        DIYArrayList<String> myList = new DIYArrayList<>();
        DIYArrayList<String> myCopyList = new DIYArrayList<>(40);
        logger.info("Add elements to myList and myCopylist:");

        Collections.addAll(myList, "1a", "exb2", "3c");
        Collections.addAll(myCopyList,"4r","fdf","d4f", "453d");

        print(myList);
        logger.info("-----------------------------------");
        print(myCopyList);

        logger.info("Sort. myList and copy myList in the myCopyList:");
        Collections.sort(myList);
        Collections.copy(myCopyList,myList);

        print(myList);
        logger.info("-----------------------------------");
        print(myCopyList);

    }

    private static void print(List<?> list){
        for (Object element: list){
            logger.info(element.toString());
        }
    }
}
