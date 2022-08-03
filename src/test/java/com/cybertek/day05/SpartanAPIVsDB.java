package com.cybertek.day05;

import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanAPIVsDB extends SpartanTestBase {



    @DisplayName("GET one spartan from api and database")
    @Test
    public void test1(){

        //get id,name,genderiphone from database


        String query = "select spartan_id,name,gender,phone from spartans where spartan_id=15";

        //save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //get same information from api

        Response response = given().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType(ContentType.JSON)
                .extract().response();

        Map <String,Object> apiMap = response.as(Map.class);


        System.out.println(apiMap);
        //compare

        assertEquals(dbMap.get("SPARTAN_ID").toString(),apiMap.get("id").toString());
        assertEquals(dbMap.get("NAME").toString(),apiMap.get("name").toString());
        assertEquals(dbMap.get("GENDER").toString(),apiMap.get("gender").toString());
        assertEquals(dbMap.get("PHONE").toString(),apiMap.get("phone").toString());




    }

}
