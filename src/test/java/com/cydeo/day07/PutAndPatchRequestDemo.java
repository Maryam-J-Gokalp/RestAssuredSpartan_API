package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class PutAndPatchRequestDemo extends SpartanTestBase {



    @Test
    public void test1(){

        int changeID = 121;

        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","BruceWayne");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",2541965478L);

        //http://18.206.123.3:8000 ip üzerinden çalıştım kendi aws ip im

        given().and().contentType(ContentType.JSON)
                .and().body(putRequestMap)
                .and().pathParam("id",changeID)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


        //daha sonra sadece name kısmını patch ile spesific olarak değiştiriyoruz


        Map<String,Object> putRequestMap2 = new LinkedHashMap<>();
        putRequestMap2.put("name","KralŞakir");

        given().contentType(ContentType.JSON)
                .and().body(putRequestMap2)
                .and().pathParam("id",changeID)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);


        //kontrol ediyoruz


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 121)
                .get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();


        response.prettyPrint();


        //delete ediyoruz  121 olanı


        given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .when().pathParam("id",121)
                .and().delete("/api/spartans/{id}")
                .then().statusCode(204);


    }





}
