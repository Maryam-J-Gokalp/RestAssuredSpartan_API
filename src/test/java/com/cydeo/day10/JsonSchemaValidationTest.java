package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        RestAssured.given().accept(ContentType.JSON)
                .and(). pathParam("id",10)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)

                //validation işlemini burda yapıyoruz.
                //jsonSchemaValidator u kullanıyoruz.

                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));



    }


    @DisplayName("GET request to verify all spartans against to schema")
    @Test
    public void schemaValidation2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)

                //validation işlemini burda yapıyoruz.
                //jsonSchemaValidator u kullanıyoruz.

                //resources içinde oluşturmayacak json schemamızı bu şekilde de yapabiliriz.
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/SpartanSchemaForArray.json")))
                .extract().response();

        response.prettyPrint();


    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema

}
