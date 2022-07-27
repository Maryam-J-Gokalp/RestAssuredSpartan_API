package com.cydeo.apiReview.reviewClasses;


import com.cydeo.apiReview.reviewPOJO.City;
import com.cydeo.apiReview.reviewPOJO.Place;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class POJOTest extends ZipBase  {

    @DisplayName("City test data")
    @Test
    public void test1(){


        City city = given().accept(ContentType.JSON)
                .and().pathParams("state", "ma", "city", "belmont")
                .when().get("/{state}/{city}")
                .then().extract().as(City.class);

        List<Place> places = city.getPlaces();

        for (Place place : places) {
            System.out.println(place.getPlaceName());
        }


    }

    @DisplayName("City test data")
    @Test
    public void test2(){


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get("/{zip}");

        Map<String,Object> zipMap = response.body().as(Map.class);
        Map<String,Object> zipMap2 = response.as(Map.class);

        System.out.println(zipMap.get("post code"));
        System.out.println(zipMap2.get("post code"));

        assertEquals(zipMap.get("country"),"United States");
        assertEquals(zipMap.get("country"),zipMap2.get("country"));

        List<Map<String,Object>> places = (List<Map<String, Object>>) zipMap.get("places");

        System.out.println(places.get(0).get("state"));
        System.out.println(places.get(0).get("place name"));


    }
}
