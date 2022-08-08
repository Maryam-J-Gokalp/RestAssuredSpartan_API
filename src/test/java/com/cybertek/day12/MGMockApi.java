package com.cybertek.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MGMockApi {

    //This link is my base Url Customer server: https://482e0405-cc3a-4637-b68d-638485c4a991.mock.pstmn.io

    @Test
    public void test1(){

        given().baseUri("https://482e0405-cc3a-4637-b68d-638485c4a991.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));

    }

    @Test
    public void test2(){

        given().baseUri("https://482e0405-cc3a-4637-b68d-638485c4a991.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }

}