package com.cydeo.day02;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanNegativeGetTest {


    @BeforeAll
    public static void init(){
        baseURI="http://18.206.123.3:8000";
    }


    //Given acccept type application/xml
    //when user send get request to api/spartans/10 end point
    //Then status code must be 406
    //and response Content type must be application/xml

    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML).when().get(baseURI + "/api/spartans/10");

        assertEquals(406,response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());

    }
}
