package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {


    private static Properties prop = new Properties();

    static {


        try {
            FileInputStream file = new FileInputStream("configuration.properties");
            prop.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("Config Reader problem!!");
        }


    }

    public static String getProperty(String property) {
        return prop.getProperty(property);
    }
}
