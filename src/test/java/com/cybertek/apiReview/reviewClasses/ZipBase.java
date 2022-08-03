package com.cybertek.apiReview.reviewClasses;


import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public class ZipBase {


    @BeforeAll
    public static void init(){

        baseURI = "http://api.zippopotam.us"; //Don't forget http or it won't work
        basePath ="/us";
    }

}
