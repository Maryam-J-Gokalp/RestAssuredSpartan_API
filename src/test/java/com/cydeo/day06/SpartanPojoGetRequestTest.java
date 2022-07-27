package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo() {


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().extract().response();



        //deserialize  --> JSON TO POJO
        //2 different ways
        /**     1.using as() method    */
        //we convert json response to spartan object with the help of jackson
        //as method uses jackson to the serialize (converting json to pojo)
        Spartan spartan15 = response.as(Spartan.class);

        System.out.println(spartan15);
        System.out.println(spartan15.getId());
        System.out.println(spartan15.getGender());
        System.out.println(spartan15.getPhone());
        System.out.println(spartan15.getName());

        /**     2.using JsonPath to deserialize json to java   */
        //second wat of deserialize json to java

        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("", Spartan.class);
        System.out.println(s15);
        System.out.println(s15.getGender());
        System.out.println(s15.getId());
        System.out.println(s15.getName());
        System.out.println(s15.getPhone());


    }


    @DisplayName("GET one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){

        //spartans/search?nameContains=a&gender=Male
        //send get request to above endpoint and save first object with type SpartanPOJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().extract().jsonPath();

        //contetin içine yazdığımız sayı --> kaçıncı elementi olduğunu yazıyoruz

        Spartan spartan1 = jsonPath.getObject("content[1]", Spartan.class);

        System.out.println(spartan1.getName());


    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .and().extract().response();

        Search search = response.as(Search.class);

        System.out.println(search.getTotalElement());

        System.out.println(search.getContent().get(0).getName());


    }

    @DisplayName("GET spartans/search and save as List<Spartan>")
    @Test
    public void test3(){

        List<Spartan> spartanList = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);


        System.out.println(spartanList);

        //1 elemanın adını almak için direk bu yöntem,i kullanabiliriz      //Sinclair

        System.out.println(spartanList.get(1).getName());

    }

    @Test
    public void test4(){




    }

}
