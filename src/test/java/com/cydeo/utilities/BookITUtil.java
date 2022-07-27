package com.cydeo.utilities;

public class BookITUtil {
    
    public static String getToken(String username, String password){
        String response = "";

        if(username.equals("admin") && password.equals("admin")){
            response =  "Basic YWRtaW46YWRtaW4=";
        } else if (username.equals("editor") && password.equals("editor")) {
            response = "Basic ZWRpdG9yOmVkaXRvcg==";
        } else if (username.equals("user") && password.equals("user")) {
            response = "Basic dXNlcjp1c2Vy";
        } else if (username.equals("wcanadinea@ihg.com") && password.equals("waverleycanadine")) {
            response = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjAyIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.UQnmL58LBoFW-Opm5OPIv7AgFvupRq4cANOIBQdOlpI";
        }

    return response;
    }
}
