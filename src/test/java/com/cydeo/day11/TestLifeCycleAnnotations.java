package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    @BeforeAll
    public static void init(){
        System.out.println("beforeall is running");
    }


    @Test
    public void test1(){

        System.out.println("test 1 is running");

    }

    @Test
    public void test2(){

        System.out.println("test 2 is running");

    }


    @AfterAll
    public static void stop(){
        System.out.println("afterall is running");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("         before each method running");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("         after each method running");
    }
}
