package com.cybertek.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id",allowSetters = true) // post ile gönderirken id yi göndermemek için
public class Spartan {


    private int id;
    private String name;
    private String gender;
    private long phone;


}
