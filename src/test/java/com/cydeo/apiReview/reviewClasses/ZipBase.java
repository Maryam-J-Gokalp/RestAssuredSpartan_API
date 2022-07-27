package com.cydeo.apiReview.reviewClasses;


import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public class ZipBase {


    @BeforeAll
    public static void init(){

        baseURI = "http://api.zippopotam.us"; //http yi unutma yoksa çalışmaz
        basePath ="/us";
    }

}
