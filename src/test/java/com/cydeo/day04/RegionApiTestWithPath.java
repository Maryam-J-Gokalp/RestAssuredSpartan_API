package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class RegionApiTestWithPath extends HRTestBase {


    @DisplayName("GET request to countries with path method")
    @Test
    public void test1(){



    Response response = given().accept(ContentType.JSON)
            .and().queryParam("q", "{\"region_id\":2}")
            .when().get(baseURI + "/countries");

    assertEquals(200,response.statusCode());

    //print limit result

        System.out.println("response.path(\"limit\") = " + response.path("limit"));


        //print hasMore

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print firstCountry id

        System.out.println("response.path(\"items[0].country_id\") = " + response.path("items[0].country_id"));

        //print second Country name

        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));


        //18.206.123.3:1000/ords/hr/countries/CA


        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));

        //get me all country names

        System.out.println("response.path(\"items.country_name\") = " + response.path("items.country_name"));

        List<String> country_names = response.path("items.country_name");

        System.out.println(country_names);

        //assert that all regions ids are equal to 2

        List<Integer> regions = response.path("items.region_id");

        for (Integer each : regions) {

            assertTrue(each == 2);

        }
    }

    @DisplayName("ORDS Test 2")
    @Test
    public void test2(){

        //send a get request to employees and get only employees who works as a IT_PROG

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\": \"IT_PROG\"}")
                .and().get(baseURI + "/employees");

       // response.prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));


        //make sure we have only IT_PROG as a job_id

        List<String> allJobIds = response.path("items.job_id");

        for (String each : allJobIds) {
            assertTrue(each.equals("IT_PROG"));
        }

        //print each name of IT_PROGs


        List<String> allFirstNames = response.path("items.first_name");
        List<String> allLastNames = response.path("items.last_name");

        for (int i = 0; i < allFirstNames.size(); i++) {

            System.out.println(allFirstNames.get(i)+" "+allLastNames.get(i));
        }




    }
}
