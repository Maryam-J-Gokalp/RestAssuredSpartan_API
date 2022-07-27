package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.cydeo.utilities.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {

    /* Given accept type and content type is json
    * and request json body is.
    * {
    * "gender":"Male",
    * "name":"Severus",
    * "phone":"8877445596",
    * }
       when user sends POST request to /api/spartans
       * then status code 201
       * and content type should be application/json
       * and json payload/response/body sould contain:
       * "A Spartan is Born!" message
       * and same data what is posted
    * /
     */

    @DisplayName("POST spartan to api")
    @Test
    public void test1(){

        String requestJsonBody = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"Severus\",\n" +
                "     \"phone\":\"8877445596\"\n" +
                "     }";


        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)

                .body(requestJsonBody).

                when()
                .post("/api/spartans");


        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));


        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));
    }

    @DisplayName("POST with Map to JSON")
    @Test
    public void test2(){

      //create map

        Map<String,Object> requestJsonBody = new LinkedHashMap<>();
        requestJsonBody.put("name","Severus");
        requestJsonBody.put("gender","Male");
        requestJsonBody.put("phone",8877445596L);


        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)

                .body(requestJsonBody).log().all(). // convert automaticly

                when()
                .post("/api/spartans");


        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));


        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));
    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void test3(){



        Spartan spartan = new Spartan();
/*        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);*/


        Map<String, Object> spartanss = SpartanUtil.getSpartan();

        for (Map.Entry<String, Object> eachSpartan : spartanss.entrySet()) {

            if(eachSpartan.getKey().equals("name")){
                spartan.setName(eachSpartan.getValue().toString());
            } else if (eachSpartan.getKey().equals("gender")) {
                spartan.setGender(eachSpartan.getValue().toString());

            } else if (eachSpartan.getKey().equals("phone")) {
                spartan.setPhone((Long) eachSpartan.getValue());
            }

        }

        System.out.println("spartan = " + spartan);
        //Bu şekilde gönderirsek id yi default olarak sıfır gönderir her zaman



        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)

                .body(spartan).log().all(). // convert automaticly

                        when()
                .post("/api/spartans");


        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));


        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));

        //assertThat(response.path("data.name"),is("Severus"));
        //assertThat(response.path("data.gender"),is("Male"));
        //assertThat(response.path("data.phone"),is(8877445596L));

    }


    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void test4(){

        //spartan objesi ile body e post gönderdik
        //daha sonra assert ettik
        //daha sonra success mesajını gördük
        //daha sonra create edilen id değerini aldık



        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        //id yi set etmedik spartan sınıfında onu göndermemek için ayar yaptık



        //Bu şekilde gönderirsek id yi default olarak sıfır gönderir her zaman

        String expectedResponseMessage = "A Spartan is Born!";


        int idFromPost = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)

                .body(spartan).log().all(). // convert automaticly

                        when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))

                .extract().jsonPath().getInt("data.id");


        //send a get request to id

        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));


        //Bu kıısmda assertionları da response dan sonraya ekledik

    }

}
