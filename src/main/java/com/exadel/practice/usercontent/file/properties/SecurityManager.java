package com.exadel.practice.usercontent.file.properties;

import com.exadel.practice.usercontent.db.PropertyReader;

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


    public boolean check(int id, String permission) {
        PropertyReader propertyReader = new PropertyReader();
        Properties property = propertyReader.getProperties(filePath);
        FileInputStream fis;

        String accept = null;
        List<String> list = null;


        accept = property.getProperty(String.valueOf(id));
        if (accept == null) {
            return false;
        }
        list = Arrays.asList(accept.split(","));

        if (list.contains(permission)) {
            return true;
        }

        return false;
    }
}
