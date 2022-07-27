package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class JSONToJAVATest extends SpartanTestBase {


   @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

       Response response = given().accept(ContentType.JSON)
               .and().pathParam("id", 15)
               .when().get("/api/spartans/{id}")
               .then().statusCode(200).extract().response();

       //ge tto json and convert it into the map

       Map<String,Object> jsonMap = response.as(Map.class);

       //deserialization için Jackson databind yada GSON library leri yüklememiz gerekiyor.

       System.out.println(jsonMap.toString());
        //after we got the map, we can use hamcrest matching or junit assertion to do assertion
       String actualName =(String) jsonMap.get("name");
       assertThat(actualName,is("Meta"));
       assertTrue(actualName.equals("Meta"));


   }

   @DisplayName(("my test"))
   @Test
   public void myMapTest(){

       Response response = given().accept(ContentType.JSON)
               .and().pathParam("id", 12)
               .when().get("/api/spartans/{id}");

       Map<String,Object> number12Map = response.as(Map.class);


           assertThat(number12Map.get("id"), is(12));
           assertThat(number12Map.get("name").toString(),is("Sol"));
           assertThat(number12Map.get("gender").toString(),is("Male"));
           assertThat(number12Map.get("phone"),is(7006438852L));





   }


   @DisplayName("GET all spartans to JAVA")
    @Test
    public void getAllSpartans(){


       Response response = given().accept(ContentType.JSON)
               .when().get("/api/spartans/").then()
               .statusCode(200).and().extract().response();

       //we need to convert json to java which is deserialize

       List<Map<String,Object>> jsonList = response.as(List.class);

       //System.out.println("jsonList = " + jsonList);


       System.out.println(jsonList.get(1).get("name"));

       //Display third spartan

       Map<String,Object> spartan3 = jsonList.get(2);
       System.out.println(spartan3);


       //Displayed all names

       for (Map<String, Object> each : jsonList) {
           System.out.println(each.get("name"));
       }










   }


    @DisplayName(("my test2"))
    @Test
    public void myMapTest2(){

        List<Object> names = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().extract().response().jsonPath().getList("name");

        System.out.println(names.toString());


    }


}
