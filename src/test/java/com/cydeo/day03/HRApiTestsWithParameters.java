package com.cydeo.day03;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiTestsWithParameters extends HRTestBase {





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

    @DisplayName("ORDS Test 2")
    @Test
    public void test2(){

        //send a get request to employees and get only employees who works as a IT_PROG

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\": \"IT_PROG\"}")
                .and().get(baseURI + "/employees");

        response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

    }

    @DisplayName("ORDS Test 3")
    @Test
    public void test3(){

        //send a get request to employees and get only employees who works as a IT_PROG

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"salary\": 10000}")
                .and().get(baseURI + "/employees");

        response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("10000"));

    }





}
