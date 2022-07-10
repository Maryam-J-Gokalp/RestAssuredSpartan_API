package com.cydeo.day03;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanTestWithParameters {

    @BeforeAll
    public static void init(){
        baseURI = "http://18.206.123.3:8000";
    }


    @DisplayName("Spartan test 1")
    @Test
    public void test1(){





    }
}
