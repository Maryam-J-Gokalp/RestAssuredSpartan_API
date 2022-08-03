package com.cybertek.day02;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String url = "http://54.237.206.21:8000";


    //given accept type application/json
    //when user send GET request to api/spartans end point
    //Then status code must be 200
    //And response Content Type must be application/json
    //And response body should include spartans result



    @Test
    public void test1(){



        Response response =  given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans");


        //printing status code from response object

        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object

        System.out.println("response.contentType() = " + response.contentType());


        //print whole result body
        response.prettyPrint();

        //how to do API testing then?
        Assertions.assertEquals(response.statusCode(),200);


        //verify content type of application/json

        Assertions.assertEquals(response.contentType(),"application/json");


    }

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2(){

        //given accept is application json
        //when users send a get request to /api/spartans/3
        //then status code should be 200
        //and content type should be application/json
        //and json body should contain Fidole


        Response response = given().accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");

        //System.out.println("response.statusCode() = " + response.statusCode());
        //System.out.println("response.contentType() = " + response.contentType());

        //boolean containsFidole = response.prettyPrint().contains("Fidole");

        //System.out.println("containsFidole = " + containsFidole);

        //assertions

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json");

        //verify json body contains fidole
        Assertions.assertEquals(response.prettyPrint().contains("Fidole"),true);
        Assertions.assertEquals(response.body().asString().contains("Fidole"),true);


    }



    @DisplayName("Hello spartan test")
    @Test
    public void test3(){

        //Given no headers provided
        //When users sends GET request to /api/hello
        //Then response status code should be 200
        //And content type header should be "text/plain;charset=UTF-8"
        //And header should contain Date
        //And content-length should be 17
        //And body should be "Hello from Sparta"


        Response response = RestAssured
                .given()
                .accept(ContentType.ANY)
                .get(url + "/api/hello");


        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assertions.assertEquals(17,response.body().asString().length());
        Assertions.assertTrue(response.header("Content-Length").equals("17"));
        Assertions.assertEquals("Hello from Sparta",response.body().asString());

        System.out.println(response.header("Connection"));


    }





}
