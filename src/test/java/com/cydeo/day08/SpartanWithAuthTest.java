package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class SpartanWithAuthTest extends SpartanAuthTestBase {


    @DisplayName("get /api/spartans as public user (guest) expect 401")
    @Test
    public void test1(){

        get("/api/spartans").then().statusCode(401).log().all();


        given().accept(ContentType.JSON)
                .get("/api/spartans")
                .then().statusCode(401)
                .log().all();

    }

    @DisplayName("get /api/spartans as admin user expect 200")
    @Test
    public void test2(){


        given().auth().basic("admin","admin")

                        .accept(ContentType.JSON)
                .get("/api/spartans")
                .then().statusCode(200)
                .log().all();

    }

    @DisplayName("DELETE /spartans/{id} as editör user expect 403")
    @Test
    public void test3(){


        given().auth().basic("editor","editor")

                .accept(ContentType.JSON)
                .and().pathParam("id",30)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(403)
                .log().all();

    }

    @DisplayName("DELETE /spartans/{id} as admin user expect 204")
    @Test
    public void test4(){


        given().auth().basic("admin","admin")

                .accept(ContentType.JSON)
                .and().pathParam("id",30)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(204)
                .log().all();

    }

    //as a homework
    //write a detailed test for Role Base Access Control (RBAC)
    //in spartan app (7000)
    //Admin should be able to take all CRUD(Create Read Update Delete)
    //Editor should be able to take all CRUD other than DELETE
    //User should be able to only read data not update,delete,create (POST,PUT,PATCH,DELETE)
    //Can guest even read data? 401 for all

    @DisplayName("HOMEWORK Admin")
    @Test
    public void test5(){

        String basicAccessToken =    "Basic YWRtaW46YWRtaW4=";
        String requestBody = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Yasin\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";

        String requestBodyForPUT = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Emel\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";


        Response postResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .when().contentType(ContentType.JSON).body(requestBody).post("/api/spartans")
                .then().statusCode(201).extract().response();

        Response getResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().get("/api/spartans")
                .then().statusCode(200).extract().response();
        int anInt = getResponse.jsonPath().getInt("[0].id");

        Response putResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().pathParam("id", anInt)
                .contentType(ContentType.JSON)
                .body(requestBodyForPUT)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204).extract().response();


        Response deleteResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().pathParam("id", anInt)
                .contentType(ContentType.JSON)
                .delete("api/spartans/{id}")
                .then().statusCode(204).extract().response();





    }

    @DisplayName("HOMEWORK Editor")
    @Test
    public void test6(){

        String basicAccessToken =    "Basic ZWRpdG9yOmVkaXRvcg==";
        String requestBody = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Yasin\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";

        String requestBodyForPUT = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Emel\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";


        Response postResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .when().contentType(ContentType.JSON).body(requestBody).post("/api/spartans")
                .then().statusCode(201).extract().response();

        Response getResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().get("/api/spartans")
                .then().statusCode(200).extract().response();
        int anInt = getResponse.jsonPath().getInt("[0].id");

        Response putResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().pathParam("id", anInt)
                .contentType(ContentType.JSON)
                .body(requestBodyForPUT)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204).extract().response();


        Response deleteResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().pathParam("id", anInt)
                .contentType(ContentType.JSON)
                .delete("api/spartans/{id}")
                .then().statusCode(403).extract().response();




    }

    @DisplayName("HOMEWORK User")
    @Test
    public void test7(){

        String basicAccessToken =    "Basic dXNlcjp1c2Vy";
        String requestBody = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Yasin\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";

        String requestBodyForPUT = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Emel\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";


        Response postResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .when().contentType(ContentType.JSON).body(requestBody).post("/api/spartans")
                .then().statusCode(403).extract().response();

        Response getResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().get("/api/spartans")
                .then().statusCode(200).extract().response();
        int anInt = getResponse.jsonPath().getInt("[0].id");
        getResponse.prettyPrint();

        Response putResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().pathParam("id", anInt)
                .contentType(ContentType.JSON)
                .body(requestBodyForPUT)
                .when().put("/api/spartans/{id}")
                .then().statusCode(403).extract().response();


        Response deleteResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().pathParam("id", anInt)
                .contentType(ContentType.JSON)
                .delete("api/spartans/{id}")
                .then().statusCode(403).extract().response();





    }

    @DisplayName("HOMEWORK Guest")
    @Test
    public void test8(){

        String basicAccessToken =    "";
        String requestBody = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Yasin\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";

        String requestBodyForPUT = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Emel\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";


        Response postResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .when().contentType(ContentType.JSON).body(requestBody).post("/api/spartans")
                .then().statusCode(401).extract().response();

        Response getResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().get("/api/spartans")
                .then().statusCode(401).extract().response();


        Response putResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .contentType(ContentType.JSON)
                .body(requestBodyForPUT)
                .when().put("/api/spartans")
                .then().statusCode(401).extract().response();


        Response deleteResponse = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .contentType(ContentType.JSON)
                .delete("api/spartans")
                .then().statusCode(401).extract().response();





    }

    @DisplayName("HOMEWORK User GETs All the Yasin")
    @Test
    public void test9(){

        String basicAccessToken =    "Basic YWRtaW46YWRtaW4=";  //admin


        Response response = given().accept(ContentType.JSON)
                .header("Authorization", basicAccessToken)
                .and().contentType(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .extract().response();

        List<String> names = response.body().path("name");



    }
}
