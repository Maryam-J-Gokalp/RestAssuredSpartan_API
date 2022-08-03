package com.cybertek.day06;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class RegionGetRequestPOJO extends HRTestBase {


    @Test
    public void test1(){


        //create pojo classes for this response
        //then test it with sending get request to regions endpoint and
        //only pointing first region and converting your pojo


        List<Region> items = given()
                .and().get("/regions").then()
                .extract().jsonPath().getList("items", Region.class);


        System.out.println(items.get(1).getRegionName());

        System.out.println(items);

        System.out.println(items.get(0).getLinks().get(0).getHref());

        /** İKİNCİ YÖNTEM */

         JsonPath json = given()
                .and().get("/regions").then()
                .extract().jsonPath();

        Region region1 = json.getObject("items[0]", Region.class);

        System.out.println(region1.getRegionId());
        System.out.println(region1.getRegionName());
        System.out.println(region1.getLinks().get(0).getHref());


    }

    @DisplayName(" GET request to employees ")
    @Test
    public void test5(){

    //you have json which has many keys and values but you just need only 4 variable

        //first_name, last_name,job_id,salary

        Employee employee = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee);


    }

    //send a get request to regions
    //verify that region ids 1,2,3,4
    //verfiy that region names Europa, Americas,Asia,Middle East and Africa
    //verify that count is 4
    //try to use pojo as much as possible
    //ignore non used fields

    @DisplayName("GET request to region only some fields test")
    @Test
    public void test6(){


        Regions regions = get("/regions").then().statusCode(200).extract().as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(),equalTo(4));

        //create empty list to store values
        List<String> regionnames = new ArrayList<>();
        List<Integer> regionIDs = new ArrayList<>();
        //loop through each of the region and save their names and id
        for (Region each : regions.getItems()) {
            regionnames.add(each.getRegionName());
            regionIDs.add(each.getRegionId());
        }


        assertThat(regionnames,containsInAnyOrder("Europe", "Americas", "Asia", "Middle East and Africa"));
        assertThat(regionIDs,containsInAnyOrder(1,2,3,4));






    }




}
