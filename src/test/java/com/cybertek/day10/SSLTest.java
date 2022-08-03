package com.cybertek.day10;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class SSLTest {

    @Test
    public void test1() {
        given().

                relaxedHTTPSValidation(). //bu method ile beraber ssl hatası almayacağız.



        when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();

    }

    @Test
    public void keyStore(){

        given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");



    }

}
