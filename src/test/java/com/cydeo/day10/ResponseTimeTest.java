package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {


    @DisplayName("Response Time ı almak ve kıyaslamak için method ")
    @Test
    public void test1(){

        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().time(lessThanOrEqualTo(1100L)).extract().response();

        System.out.println(response.getTime());

        //burada response time ı aldık. Performas testi değil ama response verme zamanını da görebiliriz.
        //time methodu ile lessthan matchers i kullanarak 1100 ms den fazlaysa hata ver dedik
        //yada time(both(greaterThan(500).and(lessThanOrEqualTo(1100L))) olarak da kullanırız.
        //yani belli bir aralığı belirterek bu aralık dışında olan durumlarda hata verdirebilriiz.




    }
}
