package com.cybertek.day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    //hamcrest.org

    @Test
    public void simpleTest1(){

        //actual 5+5 is
        assertThat(5+5,is(10));
        assertThat(5+5,equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is method accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison

        assertThat(5+5,lessThan(11));
        assertThat(5+5,greaterThan(9));
        assertThat(5+5,greaterThanOrEqualTo(10));
        assertThat(5+5,lessThanOrEqualTo(10));


        String text="B22 is learning hamcrest";

        assertThat(text,startsWith("B22"));
        assertThat(text,endsWith("est"));

        //check contains

        assertThat(text,containsString("learning"));


        List<Integer> list = Arrays.asList(1,2,5,5,6,7,8,90,3,1,2,3);

        //check size of list

        assertThat(list,hasSize(12));

        //check if list has 90

        assertThat(list,hasItem(90));

        assertThat(list,hasItems(1,2,5));

        //every item

        assertThat(list,everyItem(greaterThan(0)));



    }
}
