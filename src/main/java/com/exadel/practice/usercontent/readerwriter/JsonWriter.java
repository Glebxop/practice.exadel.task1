package com.exadel.practice.usercontent.readerwriter;


import com.exadel.practice.usercontent.model.Document;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class JsonWriter {

    public void write(String fileName, List<Document> contentSerializerJsonList) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(fileName));

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


        for (Document document : contentSerializerJsonList) {
            Date dateNow = new Date();
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("id", document.getId());
            jsonObject1.addProperty("title", document.getTitle());
            jsonObject1.addProperty("text", document.getText());
            jsonObject1.addProperty("date", formatForDateNow.format(dateNow));
            fileWriter.write(String.valueOf(jsonObject1)+"\n");

        }
        fileWriter.close();

    }

}
