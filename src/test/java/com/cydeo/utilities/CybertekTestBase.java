package com.cydeo.utilities;


import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class CybertekTestBase {


    @BeforeAll
    public static void init(){

        baseURI = ConfigurationReader.getProperty("cybertek");
    }
}
