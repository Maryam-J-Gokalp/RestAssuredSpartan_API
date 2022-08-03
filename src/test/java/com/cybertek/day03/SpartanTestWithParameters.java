package com.cybertek.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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


    @DisplayName("Spartan query test")
    @Test
    public void test3(){

        //given accept type json
        //and query parameter values are:
        //gender:Female nameContains=e
        //When user send get request to /api/spartans/search
        //then response status code should be 200
        //and response content type = "application/json"
        //and "Female" should be in response payload
        //and "Janette" should be in response payload



        Response response = given().accept(ContentType.JSON).and()
                .queryParams("gender", "Female")
                .and().queryParams("nameContains", "e")
                .get(baseURI + "/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female") && response.body().asString().contains("Janette"));


    }


    @DisplayName("Spartan test 3 inspect with MAP -> get request to /api/spartans/seatch with query params (MAP)")
    @Test
    public void test4(){

        Map<String,Object> queryParams = new HashMap<>();

        queryParams.put("nameContains","e");
        queryParams.put("gender","Female");

        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .and()
                .queryParams(queryParams)
                .get(baseURI + "/api/spartans/search");

        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female") && response.body().asString().contains("Janette"));



    }

}
