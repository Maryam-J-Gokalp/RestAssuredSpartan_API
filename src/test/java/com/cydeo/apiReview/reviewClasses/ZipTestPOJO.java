package com.cydeo.apiReview.reviewClasses;

import com.cydeo.apiReview.reviewPOJO.Place;
import com.cydeo.apiReview.reviewPOJO.PostCode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class ZipTestPOJO extends ZipBase {


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get("/{zip}");

        PostCode postcode = response.body().as(PostCode.class);

        System.out.println(postcode.getCountry());

        for (Place place : postcode.getPlaces()) {
            System.out.println(postcode.getPlaces().size());
            System.out.println(place.getPlaceName());
        }


    }
}
