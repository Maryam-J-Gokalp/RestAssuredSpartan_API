package com.cydeo.apiReview.reviewPOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbr;
    private String latitude;
}
