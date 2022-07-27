package com.cydeo.day08;

import com.cydeo.utilities.BookITUtil;
import com.cydeo.utilities.BookitTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BookitTest extends BookitTestBase {

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjAyIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.UQnmL58LBoFW-Opm5OPIv7AgFvupRq4cANOIBQdOlpI";

    @DisplayName("GET all compuses")
    @Test
    public void test1(){


        given().accept(ContentType.JSON)
                .header("Authorization", BookITUtil.getToken("wcanadinea@ihg.com","waverleycanadine"))
                .when().get("/api/campuses")
                .then().statusCode(200).log().all();

    }

    //crate BookITUtil
    //create method, that accepts email and password return token Bearer + your token as a String

}
