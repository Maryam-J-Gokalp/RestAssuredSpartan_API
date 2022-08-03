package com.cybertek.apiReview.reviewClasses;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class ZipJsonPathTest extends ZipBase{

    /*
    Exercise with JsonPath Method
Given Accept application/json
And "city" path is ma/belmont
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post codes are existing : "02178","02478"
   country  is United States
   state abbreviation is MA
   place name is Belmont
   state is Massachusetts
   latitudes are 42.4464,42.4128

    */

    @DisplayName("get all mah")
    @Test
    public void test1(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().pathParams("state", "ma", "city", "belmont")
                .when().get("/{state}/{city}")

                .then()
                .assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server", is("cloudflare"))
                .header("report-To", notNullValue())

                .and().body("places.\"post code\"", containsInAnyOrder("02178", "02478"))
                .and().body("country", is("United States"))
                .and().body("\"state abbreviation\"", is("MA"))
                .and().body("\"place name\"", is("Belmont"))
                .and().body("state", is("Massachusetts"))
                .and().body("places.latitude", containsInRelativeOrder("42.4464", "42.4128")).extract().jsonPath();


        //verfiy post code 02178 that latitude 42.4464
        //gpath syntax with it statement use it like a coding algoritm
        String expectedLatitude = "42.4464";
        String actualLatitude = jsonPath.getString("places.findAll {it.\"post code\" == \"02178\"}.latitude[0]");
        assertThat(actualLatitude,is(expectedLatitude));

    }



    @DisplayName("get all mah")
    @Test
    public void test2(){

        given().log().all().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get("/{zip}")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().assertThat().header("Server","cloudflare".toString());




    }

}
