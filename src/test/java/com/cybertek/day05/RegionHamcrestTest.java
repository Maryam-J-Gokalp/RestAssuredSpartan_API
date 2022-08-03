package com.cybertek.day05;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class RegionHamcrestTest extends HRTestBase {


    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){

        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are ... (find proper method to check list against list)
        //verify emails without checking order(provide emails in different order, just make sure it has some emails)
        List<String> names = Arrays.asList( "Alexander"  ,"Bruce", "David", "Valli", "Diana");

       given().accept(ContentType.JSON)
               .and().queryParams("q","{\"job_id\":\"IT_PROG\"}")
               .when().get(baseURI+"/employees")
               .then().statusCode(200)
               .body("items.job_id",everyItem(equalTo("IT_PROG")))
               .body("items.first_name", containsInAnyOrder("Bruce", "Alexander"  , "David", "Valli", "Diana"))
               .and().body("items.email",containsInRelativeOrder("AHUNOLD","BERNST","DAUSTIN","VPATABAL","DLORENTZ"))
               .body("items.first_name", equalToObject(names));

       /**
       //relative order ile any orderın farkı biri sırayı takip ediyor öteki etmiyor.

        equalToObject() methodu sırayı ve adeti de önemseyerek eşit kontrolü yapıyor
        */


    }


    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest2(){

        //we want to chain and also get response object


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get(baseURI + "/employees")
                .then().statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))  //------>

//------>
                .extract().jsonPath();//responsu yazdırmak için extract methodu kullanılır jsonpath i de aynı şekilde

        //assert that we have only 5 firstnames

        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

        //assert firstnames order

        assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander"  ,"Bruce", "David", "Valli", "Diana"));


    }
}
