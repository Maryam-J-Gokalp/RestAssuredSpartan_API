package com.cybertek.day10;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FormulaOneXMLTest {

    @BeforeAll
    public static void setup(){

        baseURI = "http://ergast.com";
        basePath ="/api/f1"; //port number may putting this value

    }

    @DisplayName("XML get Attribute example .@attribute")
    @Test
    public void test1(){

        //http://ergast.com/api/f1/drivers/alonso

        Response res = given()
                .and().pathParam("driver", "alonso")
                .when().get("/drivers/{driver}")
                .then().contentType(ContentType.XML).statusCode(200).extract().response();

        //res.prettyPrint();

        String givenName = res.xmlPath().getString("MRData.DriverTable.Driver.GivenName");
        System.out.println(givenName);
        String familyName = res.xmlPath().getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println(familyName);
        String driverID = res.xmlPath().getString("MRData.DriverTable.Driver.@driverId");
        System.out.println(driverID);
        String code = res.xmlPath().getString("MRData.DriverTable.Driver.@code");
        System.out.println(code);
        String URL = res.xmlPath().getString("MRData.DriverTable.Driver.@url");
        System.out.println(URL);
    }


    @DisplayName("Circuit information")
    @Test
    public void test2(){




    }

}