package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithPath extends SpartanTestBase {



    @DisplayName("get one spartan with path method")
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


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get(baseURI + "/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());


        //verify each json key has specific value

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phoneNumber = response.path("phone");

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phoneNumber);


    }


    @DisplayName("get all spartan and navigate with path method")
    @Test
    public void test2(){



        Response response = given().accept(ContentType.JSON)
                .when().get(baseURI + "/api/spartans");

        //response.prettyPrint();

        int firstID = response.path("id[0]");
        String name = response.path("name[0]");

        System.out.println("firstID = " + firstID);
        System.out.println("name = " + name);

        //last first name

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);


        //BÜTÜN NAMELERİ TEK BİR LİSTTE TOPLAYABİLİRİZ.


        List<String> names = response.path("name");

        for (String s : names) {
            System.out.println(s);
        }



    }
}
