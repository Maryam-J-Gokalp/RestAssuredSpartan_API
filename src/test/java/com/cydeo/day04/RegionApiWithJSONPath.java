package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RegionApiWithJSONPath extends HRTestBase {

    @DisplayName("GET request the countries")
    @Test
    public void test1(){

        Response response = RestAssured.get("/countries");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        // get the second country name with json path
        //to use jsonpath we assign response to jsonpath
        List<Object> country_name = jsonPath.getList("items.country_name");
        System.out.println(country_name.get(1));
        System.out.println(country_name.size());

        //get all country ids

        List<Object> country_ids = jsonPath.getList("items.country_id");
        System.out.println(country_ids);

        //get all country names where their region id is equal to 2

        List<Object> list = jsonPath.getList("items.findAll  {it.region_id==2}.country_name");

        System.out.println(list);


        //country name i argentina olanın region idsini yazdır

        List<Object> list1 = jsonPath.getList("items.findAll {it.country_name == \"Argentina\"}.region_id");

        System.out.println(list1);


    }

    @DisplayName("GET request the countries")
    @Test
    public void test2(){

        Response response = RestAssured.given().queryParams("limit", 107)
                .when().get("/employees");

        //get me all email of employees who is working as IT_prog

        JsonPath jsonPath = response.jsonPath();

        List<Object> emails = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");

        System.out.println(emails);

        //get me firstname of employees who is making more than 100000

        List<String> personn = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println(personn);

        //get max salary firstname

        String maxSalary = jsonPath.getString("items.max {it.salary}.first_name");

        System.out.println(maxSalary);


    }
}
