package com.cybertek.apiReview.reviewPOJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    @JsonProperty("country abbreviation")
    private String countryAbr;
    private List<Place> places;
    private String country;
    @JsonProperty("place name")
    private String placeName;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbr;

}
