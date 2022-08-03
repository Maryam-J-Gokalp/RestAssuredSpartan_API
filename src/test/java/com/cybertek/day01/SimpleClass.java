package com.cybertek.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleClass {
 String url = "http://54.237.206.21:8000//api/spartans";

 @Test
    public  void test1(){

     Response response = RestAssured.get(url);

     System.out.println(response.statusCode());

 }


}
