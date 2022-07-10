package com.cydeo.day03;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestWithParameters {

    @BeforeAll
    public static void init(){
        baseURI = "http://18.206.123.3:8000";
    }


    @DisplayName("Spartan test 1")
    @Test
    public void test1(){

        //given accept type json
        //and id parameter value is 5
        //When user sends get request to /api/spartans/{id}
        //then response status code should be 200
        //and response content-type: application/json
        //And "Blythe" should be in response payload


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get(baseURI + "/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));


    }

    @DisplayName("Spartan test 2 Negative Test")
    @Test
    public void test2(){

        //given accept type json
        //and id parameter value is 500
        //when user send get request to /api/spartans/{id}
        //Then response status code should be 404
        //And response content-type:application/json
        //And "Not Found" message should be in response payload


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(baseURI + "/api/spartans/{id}");

        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));

    }
}
