package com.cydeo.day04;

import com.cydeo.utilities.CybertekTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class CBTrainingApiWithJsonPath extends CybertekTestBase {


    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){

        String path = baseURI+"/student/all";
        System.out.println(path);

        Response response = given().get(path);


        //send a request to student id 23401 as a parameter and accept header application/json
        //verify status code 200 /content type /Content-Encoding =gzip
        //verify Date header exists
        //assert that
        /**
         * firstName Vera
         * batch 14
         * section 12
         * emailadress aaa@gmail.com
         * companyname cybertek
         * state IL
         * zipcode 60606
         */

        //using jsonPath



    }


}
