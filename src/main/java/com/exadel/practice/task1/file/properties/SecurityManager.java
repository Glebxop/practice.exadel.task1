package com.exadel.practice.task1.file.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SecurityManager {

   public String check(int id){
        FileInputStream fis;
        Properties property = new Properties();
       String host = null;

        try {
            fis = new FileInputStream("src/main/resources/Properties.properties");
            property.load(fis);

            host = property.getProperty(String.valueOf(id));
           

           // System.out.println("HOST: " + host);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        
        return  host;
    }
}
