package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class GetRegions extends HRTestBase {


    @Test
    public void test1(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/regions")
                .then().extract().jsonPath();

        String regionName = jsonPath.getString("items[0].region_name");
        System.out.println(regionName);

        String hrefValue = jsonPath.getString("items[0].links[0].href");
        System.out.println(hrefValue);


    }
}
