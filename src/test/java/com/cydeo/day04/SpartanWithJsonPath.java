package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class SpartanWithJsonPath extends SpartanTestBase {


    @DisplayName("get one spartan with json path")
    @Test
    public void test1(){

        //given accept type is json
        //and param id is 10
        //When user sends a get request to "/api/spartans/{id}"
        //Then status code is 200
        //and content type is "application/json"
        //And response payload values matches with:
        //id is 10,
        //name is "Lorenza",
        //gender is "Female",
        //phone is 3312820936

        Response res = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get(baseURI + "/api/spartans/{id}");

        //print name with path method

        System.out.println("res.path(\"name\") = " + res.path("name").toString());

        //assinginng response to jsonpath

        JsonPath jsonPath = res.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }

}
