package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.237.206.21:8000//api/spartans";



    @Test
    public void test1(){

        Response response = RestAssured.get(url);


        //print the response status code
        System.out.println(response.statusCode());

        //print response body
        String s = response.prettyPrint();

        String[] jsonParse = s.split("},");

        System.out.println(jsonParse.length);

    }

}
