package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class CybertekTestBase {
    @BeforeAll
    public static void init(){

        baseURI = ConfigurationReader.getProperty("cybertek");
    }
}


