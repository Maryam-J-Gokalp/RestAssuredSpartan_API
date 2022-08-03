package com.cydeo.day02;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//Bu kısımları yazarak static import yapmış oluyoruz

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class HRGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init() {
        baseURI = "http://54.237.206.21:1000/ords/hr";
        // MY IP http://54.237.206.21:8000/

    }



    @DisplayName("HR Test 1 Get request to regions")
    @Test
    public void test1(){

       // Response response = RestAssured.get("/regions");

        //System.out.println(response.statusCode());


        //given accept type of json
        //when user sends get request to regions/2
        //Then response status code must be 200
        //and body is json format
        //and response body contains Americas


        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/2");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Americas"));

    }
}
