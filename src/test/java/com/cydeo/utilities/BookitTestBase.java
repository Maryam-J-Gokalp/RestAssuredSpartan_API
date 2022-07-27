package com.cydeo.utilities;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public class BookitTestBase {

    @BeforeAll
    public static void init (){

        baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com";


    }
}
