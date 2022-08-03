package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class SpartanWithXML extends SpartanAuthTestBase {


    @DisplayName(" GET request to /api/spartans and verify xml")
    @Test
    public void test1(){

       given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans")

                .then()
                .contentType(ContentType.XML)
                .statusCode(200)
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"));

        // -- same just auth part is different

        given().accept(ContentType.XML)
                .and().header("Authorization","Basic YWRtaW46YWRtaW4=")
                .when().get("/api/spartans")

                .then()
                .contentType(ContentType.XML)
                .statusCode(200)
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"));


    }


    @DisplayName("GET request /api/spartans with xmlpath")
    @Test
    public void test2(){


        XmlPath xmlPath = given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().extract().xmlPath();


        String name = xmlPath.getString("List.item[0].name");
        System.out.println(name);
        //get 3rd spartan id number
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println(id);


    }


    @DisplayName("GET request /api/spartans with xmlpath get all names")
    @Test
    public void test3(){


        XmlPath xmlPath = given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().extract().xmlPath();


        List<Object> names = xmlPath.getList("List.item.name");

        System.out.println(names);


    }






}
