package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest extends SpartanTestBase {

    /*
    * given accept type json
    * and path param id is 15
    * when user sends a request to /spartans/{id}
    * then status code 200
    * and content type is json
    * and json data has following
    *   id:15
    *   name:Meta
    *   gender:Female
    *   phone:1938695106
    *
    * */


    @DisplayName("One spartan with Hamcrest and chaining")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get(baseURI+"/api/spartans/{id}")

                .then().statusCode(200)
                .and().contentType("application/json")

                .and().

                body("id",is(equalTo(15)),
                        "name", is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106));


    }


    @DisplayName("CBTraining ")
    @Test
    public void teacherData(){

        String baseURI = "http://api.cybertektraining.com/teacher/{id}";

        given().accept(ContentType.JSON)
                .and().pathParam("id",21887)

                .when().

                get(baseURI)

                .then().

                statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .header("Content-Length",is("275"))
                .and()
                .header("Date",notNullValue())          //If u get some kind of nullValue won't complain
                .and()
                .assertThat()
                .body("teachers[0].firstName",is("Leonel"),
                        "teachers[0].lastName",is("Messi"),
                        "teachers[0].gender",is("Male"));





    }


    @DisplayName("GET request to teacher/all and chaining ")
    @Test
    public void teachersTest() {

        String baseURI = "http://api.cybertektraining.com/teacher/all";

        //verify "Carmine","Karmen","Aleta" inside the all teachers

        given().accept(ContentType.JSON)

                .when().

                get(baseURI)

                .then()
                .statusCode(200)
                .and().body("teachers.firstName",hasItems("Carmine","Karmen","Aleta"));


    }
}
