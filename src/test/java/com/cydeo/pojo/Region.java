package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {


    @JsonProperty("region_id") //tam olrak adını yazmadan da jackson annotation ile bu şekilde kullanabilirsin.
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;
    private List<Link> links;



}
