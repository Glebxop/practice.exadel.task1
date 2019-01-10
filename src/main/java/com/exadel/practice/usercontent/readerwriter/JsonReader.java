package com.exadel.practice.usercontent.readerwriter;


import com.exadel.practice.usercontent.model.Document;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonReader {


    public List<Document> read(String fileName) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(fileName));

        List<String> afterReadJson = new ArrayList<>();

        while (sc.hasNext()) {
            afterReadJson.add(sc.nextLine());
        }

        return deserializeFromJson(afterReadJson);
    }

    private List<Document> deserializeFromJson(List<String> list) {
        List<Document> listDocument = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject;
        for (String string : list) {

            jsonObject = (JsonObject) parser.parse(string);
            JsonElement id = jsonObject.get("id");
            JsonElement text = jsonObject.get("text");
            JsonElement title = jsonObject.get("title");
            JsonElement date=jsonObject.get("date");
            System.out.println(date);
            listDocument.add(new Document(id.getAsInt(), title.getAsString(), text.getAsString()));
        }
        return listDocument;
    }


}
