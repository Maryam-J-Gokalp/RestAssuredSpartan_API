package com.cydeo.utilities;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {



    //create one spartanutil class
    //create a static method that returns Map<String,Object>
    //use faker library to assign each time different info
    //for name,gender,phone
    //then user our method for creating spartan as a map, dynamically

    public static Map<String,Object> getSpartan(){

        Map<String,Object> list = new LinkedHashMap<>();

        Faker faker = new Faker();

        String name = faker.name().firstName();
        Long phone = Long.parseLong(faker.numerify("98########89"));
        String gender = faker.demographic().sex();
        list.put("name",name);
        list.put("gender",gender);
        list.put("phone",phone);

        return list;
    }
}
