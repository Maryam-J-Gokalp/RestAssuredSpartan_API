package com.cybertek.day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("get spartan/search and chaining together")
    @Test
    public void test1(){

        List<String> list = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j",
                        "gender", "Male")

                .when().get("/api/spartans/search")

                .then().statusCode(200)
                .and().body("totalElement", is(4)).extract().jsonPath().getList("content.name");


        System.out.println("list = " + list);
    }

    @DisplayName("get spartan/search and chaining together")
    @Test
    public void test2(){

        //save status code

       int statusCode = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j",
                        "gender", "Male")

                .when().get("/api/spartans/search")

                .then().statusCode(200)
                .and().body("totalElement", is(4))
                .extract().response().statusCode();


        System.out.println("status code = " + statusCode);
    }



}
