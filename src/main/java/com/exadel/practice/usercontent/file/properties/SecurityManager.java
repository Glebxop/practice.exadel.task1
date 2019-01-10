package com.exadel.practice.usercontent.file.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SecurityManager {
   private String filePath;

    public SecurityManager(String filePath) {
        this.filePath = filePath;
    }


    public boolean check(int id,String permission) throws Exception {
        FileInputStream fis;
        Properties property = new Properties();
        String accept = null;
        List<String> list = null;
        try {
            fis = new FileInputStream(filePath);
            property.load(fis);

            accept = property.getProperty(String.valueOf(id));
            if (accept == null) {
                return false;
            }
            list = Arrays.asList(accept.split(","));

            if (list.contains(permission)){
                return true;
            }


        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

      return false;
    }
}
