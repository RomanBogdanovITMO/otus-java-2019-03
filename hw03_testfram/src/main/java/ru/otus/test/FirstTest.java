package ru.otus.test;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class FirstTest {
    @Before
    public void before(){
        System.out.println("method: before");
    }

    @Test
    public void test(){
        System.out.println("method: test" );
    }

    @Test
    public void test2(){
        System.out.println("test2");
        throw new RuntimeException();
    }

    @After
    public void after(){
        System.out.println("method: after");
    }
}
