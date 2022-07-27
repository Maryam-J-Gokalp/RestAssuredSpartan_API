package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("spartan");


        String dbURL = "jdbc:oracle:thin:@18.206.123.3:1521:XE";
        String dbUserName= "SP";
        String dbPassword= "SP";
       // DBUtils.createConnection(dbURL,dbUserName,dbPassword);
    }

    @AfterAll
    public static void teardown(){
        //DBUtils.destroy();
    }




}
