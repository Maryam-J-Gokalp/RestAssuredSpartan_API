package com.cydeo.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {


    @BeforeAll
    public static void  init(){

        baseURI = "http://18.206.123.3:1000/ords/hr";

    }


    @DisplayName("ORDS Test 1")
    @Test
    public void test1(){

        //given accept type is json
        //and parameters q={""region_id:2}
        //when users sends a get request to "/countries"
        //then status code is 200
        //and content type is application/json
        //and payload should contain "United States of America"


        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get(baseURI + "/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

    }
}
