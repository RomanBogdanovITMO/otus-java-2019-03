package ru.otus.test;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class FirstTest {
    @Before
    public void beforeTest(){
        System.out.println("Method: Before");
    }

    @Test
    public void test(){
        System.out.println("Method: Test");
    }

    @Test
    public void anotherTest(){
        System.out.println("Method: Another Test");
    }

    @After
    public void afterTest(){
        System.out.println("Method: After");
    }
}
