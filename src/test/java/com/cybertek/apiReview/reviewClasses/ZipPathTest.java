package com.cybertek.apiReview.reviewClasses;


import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class ZipPathTest extends ZipBase {

    @DisplayName("Zippotam first test")
    @Test
    public void test1(){

        Response res = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get("/{zip}")
                .then().statusCode(200).extract().response();

        res.prettyPrint();


    }

    @DisplayName("get all mah")
    @Test
    public void test2(){

        List<Object> list = given().accept(ContentType.JSON)
                .and()
                .pathParams("country", "tr", "zip", 35160)
                .when()
                .get("/{country}/{zip}")
                .then().extract().response().jsonPath().getList("places.\"place name\"");

        System.out.println(list.toString());


    }


    @DisplayName("get all latitude")
    @Test
    public void test3(){

        String latitudeString = given().accept(ContentType.JSON)
                .and()
                .pathParams("country", "tr", "zip", 35160)
                .when()
                .get("/{country}/{zip}")
                .then().extract().response().jsonPath().getString("places.latitude");

        System.out.println(latitudeString);

    }



    @DisplayName("get all latitude")
    @Test
    public void test4(){

         /*
    Exercise with Path Method
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post code is 22031
   country  is United States
   country abbreviation is US
   place name is Fairfax
   state is Virginia
   latitude is 38.8604
    */

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParams("zip", 22031)
                .when()
                .get("/{zip}").then().extract()
                .response();

        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(),is(equalTo("application/json")));
        assertThat(response.header("Server"),is("cloudflare"));
        assertThat(response.header("report-To"),notNullValue());
        assertThat(response.body().path("\"post code\""),is("22031"));
        assertThat(response.body().path("country"),is("United States"));
        assertThat(response.body().path("\"country abbreviation\""),is("US"));
        assertThat(response.body().path("places[0].\"place name\""),is("Fairfax"));
        assertThat(response.body().path("places[0].state"),is("Virginia"));
        assertThat(response.body().path("places[0].latitude"),is("38.8604"));




    }

    @DisplayName("test 4 ün değişik versiyonu")
    @Test
    public void test5(){

         /*
    Exercise with Path Method
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post code is 22031
   country  is United States
   country abbreviation is US
   place name is Fairfax
   state is Virginia
   latitude is 38.8604
    */

        given().accept(ContentType.JSON)
                .and()
                .pathParams("zip", 22031)
                .when()
                .get("/{zip}").then().extract()
                .response()


                .then().assertThat().header("Server",is("cloudflare"))
                        .header("report-To",notNullValue())
                                .statusCode(200)
                                        .contentType(ContentType.JSON)
                                                .and().body("\"post code\"",is("22031"))
                        .body("country",is("United States"))
                                .body("\"country abbreviation\"",is("US"))
                                        .body("places[0].\"place name\"",equalTo("Fairfax"))
                                                .body("places[0].state",is("Virginia"))
                                                        .body("places[0].latitude",is("38.8604"));











    }
}
